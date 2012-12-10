package dominion.view.utils;

import dominion.control.ControlImpl;
import dominion.model.GameImpl;
import dominion.model.cards.Card;
import dominion.view.DominionShell;
import dominion.view.MessageDialog;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.utils.StringUtils;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Basisklasse für SWTBot JUnit 4 Tests, welche einen UI-Thread erzeugt
 * und diesen nach den Tests wieder beendet.
 *
 * @author Tobi
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class SWTBotBase {

    /**
     * Zuletzt gestarteter UI-Thread.
     */
    private static Thread guithread = null;
    /**
     * Wenn true, beenden sich alle evtl. laufenden UI-Threads.
     */
    private static boolean shutdownUIThread = false;
    /**
     * Display-Objekt für die übergabe an die Shell-Klassen.
     */
    private static Display display;
    /**
     * Der SWTBot für die Tests.
     */
    private final SWTBot bot;
    /**
     * Model
     */
    private GameImpl game;
    /**
     * Control
     */
    private ControlImpl control;
    /**
     * Schlüssel der ID in den Widget-Daten (für Aufrufe über ...WithId(...)).
     */
    public static final String SWTBOT_DATA_ID = DominionShell.DATA_KEY_ID;

    /**
     * Konstruktor.
     */
    public SWTBotBase() {
        bot = new SWTBot();
    }

    /**
     * Führt toExec asynchron aus.
     *
     * @param toExec Auszuführen
     */
    protected final void asyncExec(final VoidResult toExec) {
        UIThreadRunnable.asyncExec(toExec);
    }

    /**
     * Führt toExec synchron aus.
     *
     * @param toExec Auszuführen
     */
    protected final void syncExec(final VoidResult toExec) {
        UIThreadRunnable.syncExec(toExec);
    }

    /**
     * Schließt die aktive Shell.
     */
    protected final void closeShell() {
        UIThreadRunnable.syncExec(new VoidResult() {

            @Override
            public void run() {
                Shell shell = display.getActiveShell();
                if(shell != null){
                    final String id = shell.getData(SWTBOT_DATA_ID).toString();
                    new SWTBotShell(shell).close();
                    bot.waitUntil(new ShellClosedCondition(id));
                }
            }
        });
        
    }

    /**
     * Wartet bis die jeweilige Shell aktiv ist.
     *
     * @param id ID der Shell
     */
    protected final void waitForShell(final String id) {
        bot.waitUntil(new ShellActiveCondition(id));
    }

    /**
     * Liefert die ID des jeweiligen Widgets zurück.
     *
     * @param widget Widget
     * @return ID
     */
    protected final String getWidgetId(
            final AbstractSWTBot<? extends Widget> widget) {
        Object data = getWidgetData(SWTBOT_DATA_ID, widget);
        if (data != null) {
            return data.toString();
        } else {
            return "";
        }
    }

    /**
     * Liefert den jeweiligen Datensatz des Widgets zurück.
     *
     * @param key Schlüssel
     * @param widget Widget
     * @return Wert
     */
    protected final Object getWidgetData(final String key,
            final Widget widget) {
        return UIThreadRunnable.syncExec(new Result<Object>() {

            @Override
            public Object run() {
                return widget.getData(key);
            }
        });
    }

    /**
     * Liefert den jeweiligen Datensatz des Widgets zurück.
     *
     * @param key Schlüssel
     * @param widget Widget
     * @return Wert
     */
    protected final Object getWidgetData(final String key,
            final AbstractSWTBot<? extends Widget> widget) {
        return UIThreadRunnable.syncExec(new Result<Object>() {

            @Override
            public Object run() {
                return widget.widget.getData(key);
            }
        });
    }

    /**
     * Liefert alle Checkboxen.
     *
     * @return Checkboxen
     */
    protected final List<SWTBotCheckBox> getCheckboxes() {        
        List<? extends Widget> widgets = bot.widgets(allOf(
                widgetOfType(Button.class), withStyle(SWT.CHECK, "")));
        List<SWTBotCheckBox> checkboxes = new LinkedList<SWTBotCheckBox>();
        for (Widget w : widgets) {
            checkboxes.add(new SWTBotCheckBox((Button) w));
        }
        return checkboxes;
    }

    /**
     * Liefert alle Textfelder.
     *
     * @return Textfelder
     */
    protected final List<SWTBotText> getTextboxes() {
        List<? extends Widget> widgets = bot.widgets(
                allOf(widgetOfType(Text.class)));
        List<SWTBotText> texts = new LinkedList<SWTBotText>();
        for (Widget w : widgets) {
            texts.add(new SWTBotText((Text) w));
        }
        return texts;
    }

    /**
     * Liefert die verknüpfte Karte.
     *
     * @param widget Widget
     * @return Karte
     */
    protected final Card getLinkedCard(final Widget widget) {
        Object data = getWidgetData(DominionShell.DATA_KEY_CARD, widget);
        Assert.assertTrue("no card reference (DominionShell.DATA_KEY_CARD) "
                + "set in widget data", data != null && data instanceof Card);
        return (Card) data;
    }

    /**
     * Liefert die verknüpfte Karte.
     *
     * @param widget Widget
     * @return Karte
     */
    protected final Card getLinkedCard(
            final AbstractSWTBot<? extends Widget> widget) {
        Object data = getWidgetData(DominionShell.DATA_KEY_CARD, widget);
        Assert.assertTrue("no card reference (DominionShell.DATA_KEY_CARD) "
                + "set in widget data", data != null && data instanceof Card);
        return (Card) data;
    }

    /**
     * Deselektiert alle Checkboxen in checkboxes.
     *
     * @param checkboxes Checkboxen
     * @return Deselektierte Checkboxen
     */
    protected final List<SWTBotCheckBox> deselectCheckboxes(
            final List<SWTBotCheckBox> checkboxes) {
        for (SWTBotCheckBox check : checkboxes) {
            check.deselect();
        }
        return checkboxes;
    }

    /**
     * Selektiert alle Checkboxen in checkboxes.
     *
     * @param checkboxes Checkboxen
     * @return Selektierte Checkboxen
     */
    protected final List<SWTBotCheckBox> selectCheckboxes(
            final List<SWTBotCheckBox> checkboxes) {
        for (SWTBotCheckBox check : checkboxes) {
            check.select();
        }
        return checkboxes;
    }

    /**
     * Deselektiert count Checkboxen in checkboxes.
     *
     * @param newCheckboxes Checkboxen
     * @param count Anzahl
     * @return Deselektierte Checkboxen
     */
    protected final List<SWTBotCheckBox> deselectCheckboxes(
            final List<SWTBotCheckBox> newCheckboxes, final int count) {
        List<SWTBotCheckBox> checkboxes =
                new ArrayList<SWTBotCheckBox>(newCheckboxes);
        shuffleList(checkboxes);
        List<SWTBotCheckBox> tmp = new LinkedList<SWTBotCheckBox>(checkboxes);
        List<SWTBotCheckBox> unchecked = new LinkedList<SWTBotCheckBox>();
        Collections.shuffle(tmp);

        for (int index = 0; index < count; index++) {
            checkboxes.get(index).deselect();
            unchecked.add(checkboxes.get(index));
        }
        return unchecked;
    }

    /**
     * Selektiert count Checkboxen in checkboxes.
     *
     * @param newCheckboxes Checkboxen
     * @param count Anzahl
     * @return Selektierte Checkboxen
     */
    protected final List<SWTBotCheckBox> selectCheckboxes(
            final List<SWTBotCheckBox> newCheckboxes, final int count) {
        List<SWTBotCheckBox> checkboxes =
                new ArrayList<SWTBotCheckBox>(newCheckboxes);
        shuffleList(checkboxes);
        List<SWTBotCheckBox> tmp = new LinkedList<SWTBotCheckBox>(checkboxes);
        List<SWTBotCheckBox> checked = new LinkedList<SWTBotCheckBox>();
        Collections.shuffle(tmp);

        for (int index = 0; index < count; index++) {
            checkboxes.get(index).select();
            checked.add(checkboxes.get(index));
        }
        return checked;
    }
    
    /**
     * Prüft ob im Widget der jeweliige Data-Eintrag auf den angegebenen
     * Wert gesetzt ist.
     * @param expected Erwarteter Wert
     * @param key Data-Schlüssel
     * @param widget Widget
     */
    protected final void assertData(final Object expected, final String key, final AbstractSWTBot<? extends Widget> widget){
        Assert.assertEquals(expected, getWidgetData(key, widget));
    }

    /**
     * Prüft, ob sich der MessageDialog mit dem jeweiligen Text öffnet und
     * schließt diesen danach.
     * @param expectedMessage Zu erwartende Nachricht
     */
    protected final void assertAndCloseMessageDialog(String expectedMessage){
        waitForShell(MessageDialog.DIALOG_ID);

        SWTBotShell shell = getBot().activeShell();
        assertData(expectedMessage, MessageDialog.DATA_KEY_MESSAGE, shell);

        closeShell();
    }

    /**
     * Prüft ob die Checkboxen deaktiviert sind.
     *
     * @param checkboxes Checkboxen
     * @return true wenn checkboxen selektiert, false sonst.
     */
    protected final void assertCheckboxesDisabled(
            final List<SWTBotCheckBox> checkboxes) {
        Assert.assertEquals(0, getCheckboxesEnabledCount(checkboxes));
    }

    /**
     * Prüft ob die Checkboxen aktiviert sind.
     *
     * @param checkboxes Checkboxen
     */
    protected final void assertCheckboxesEnabled(
            final List<SWTBotCheckBox> checkboxes) {
        Assert.assertEquals(checkboxes.size(),
                getCheckboxesEnabledCount(checkboxes));
    }

    /**
     * Prüft ob der Button aktiviert ist.
     *
     * @param id ID des Buttons
     */
    protected final void assertButtonEnabled(final String id) {
        Assert.assertEquals(true, bot.buttonWithId(id).isEnabled());
    }

    /**
     * Prüft ob der Button deaktiviert ist.
     *
     * @param id ID des Buttons
     */
    protected final void assertButtonDisabled(final String id) {
        Assert.assertEquals(false, bot.buttonWithId(id).isEnabled());
    }


    /**
     * Prüft ob im jeweiligen Label das übergebene Bild angezeigt wird.
     *
     * @param pathOfExpectedImage Bildpfad
     * @param label Zu prüfendes Label
     */
    protected final void assertImageEquals(final String pathOfExpectedImage,
            final SWTBotLabel label) {
        assertImageEquals(null, pathOfExpectedImage, label);
    }

    /**
     * Prüft auf Gleicheit der Bilder.
     *
     * @param newMessage Ausgabenachricht.
     * @param pathOfExpectedImage Erwarteter Pfad
     * @param label swt bot label
     */
    protected final void assertImageEquals(final String newMessage,
            final String pathOfExpectedImage, final SWTBotLabel label) {

        String message = newMessage;
        if (StringUtils.isEmpty(message)) {
            message = "Expacted Image '" + pathOfExpectedImage
                    + "' was not found in label '" + getWidgetId(label) + "'";
        }

        Assert.assertArrayEquals(
                message,
                label.image().getImageData().data,
                new ImageData(getRessourceAsStream(pathOfExpectedImage)).data);
    }

    /**
     * Liefert die Anzahl der in checkboxes selektiert Checkboxen.
     *
     * @param checkboxes Checkboxen
     * @return Anzahl selektiert
     */
    protected final int getCheckboxesEnabledCount(
            final List<SWTBotCheckBox> checkboxes) {
        int enabled = 0;
        for (SWTBotCheckBox checkbox : checkboxes) {
            if (checkbox.isEnabled()) {
                enabled++;
            }
        }
        return enabled;
    }

    /**
     * Liefert einen Stream zur jeweiligen Ressource zurück.
     *
     * @param path Pfad (relativ von DominionShell aus)
     * @return Stream
     */
    private static InputStream getRessourceAsStream(final String path) {
        return DominionShell.class.getResourceAsStream(path);
    }

    /**
     * Mischt die Liste immer nach dem gleichen Muster.
     *
     * @param <T> Beliebiger Typ
     * @param list Zu mischende Liste
     */
    protected static final <T> void shuffleList(final List<T> list) {
        Collections.rotate(list, list.size() / 3);
        for (int index = 0; index < list.size(); index = 2 * index + 1) {
            Collections.swap(list, index, list.size() - 1 - index);
        }
        for (int index = 0; index < list.size() - 2; index = index + 2) {
            Collections.swap(list, index, index + 2);
        }
        for (int index = 0; index < list.size() - 3; index = index + 2) {
            Collections.swap(list, index, index + 3);
        }
    }

    /**
     * Entfernt alle Elemente aus toSubstrct aus list.
     *
     * @param <T> Typ
     * @param list Liste
     * @param toSubstract Liste mit den zu entfernenden Elemente
     * @return Ergebnis
     */
    protected static final <T> List<T> subtractList(final List<T> list,
            final List<T> toSubstract) {
        List<T> res = new ArrayList<T>();
        for (T item : list) {
            if (!toSubstract.contains(item)) {
                res.add(item);
            }
        }
        return res;
    }

    /**
     * Initialisiert das Model und den Controller.
     */
    protected final void setUpSWTBotTest() {
        game = new GameImpl();
        control = new ControlImpl(game);
    }

    /**
     * Erzeugt den UI-Thread (wird automatisch von JUnit 4 aufgerufen).
     *
     * @throws IOException io
     */
    @BeforeClass
    public static final void initSWTBotTest() throws IOException {
        initLog4j();
        System.setProperty("dominion.testmode", "1");
        SWTBotPreferences.DEFAULT_KEY = SWTBOT_DATA_ID;
        if (guithread == null) {
            guithread = new Thread(new Runnable() {
                @Override
                public void run() {
                    display = new Display();

                    // SWTBot-Thread benachrichtigen,
                    // dass das Display bereit ist
                    synchronized (guithread) {
                        guithread.notify();
                    }

                    while (!shutdownUIThread && display != null
                            && !display.isDisposed()) {
                        if (!display.readAndDispatch()) {
                            display.sleep();
                        }
                    }
                }
            });
            guithread.start();

            // Warten bis das Display erstellt wurde
            // Wenn ihr kein Buisywait mögt, dann halt so...
            synchronized (guithread) {
                if (Display.findDisplay(guithread) == null) {
                    try {
                        guithread.wait();
                    } catch (InterruptedException ex) {
                        ex.getMessage();
                    }
                }
            }
        } else {
            display = Display.getDefault();
        }
    }

    /**
     * Beendet den UI-Thread (wird automatisch von JUnit 4 aufgerufen).
     */
    @AfterClass
    public static final void finishSWTBotTest() {
        // Deaktiviert, da sich sonst nicht meherere UI-Tests hintereinander
        // in Test-Suits ausführen lassen
        // shutdownUIThread = true;
    }

    /**
     * @return display
     */
    protected final Display getDisplay() {
        return display;
    }

    /**
     * @return bot
     */
    protected final SWTBot getBot() {
        return bot;
    }

    /**
     *
     * @return game
     */
    protected final GameImpl getModel() {
        return game;
    }

    /**
     *
     * @return control
     */
    protected final ControlImpl getControl() {
        return control;
    }

    /**
     * Log4j initialisieren.
     */
    private static void initLog4j() {
        Logger logger = Logger.getRootLogger();
        logger.setLevel(Level.INFO);
        SimpleLayout layout = new SimpleLayout();
        ConsoleAppender appender = new ConsoleAppender(layout);
        logger.addAppender(appender);
    }

    /**
     * Wahr, wenn Shell aktiv ist
     */
    protected class ShellActiveCondition extends DefaultCondition {

            private String widgetId;

            /**
             * Ctor
             * @param id ID der Shell
             */
            public ShellActiveCondition(final String id)
            {
                Assert.assertNotNull(id, "The shell ID was null");
                Assert.assertTrue("The shell text was empty",
                        !StringUtils.isEmpty(id));
                this.widgetId = id;
            }

            @Override
            public boolean test() throws Exception {
                try {
                    return UIThreadRunnable.syncExec(new BoolResult() {

                        @Override
                        public Boolean run() {
                            Shell shell = display.getActiveShell();
                            if(shell == null || !widgetId.equals(shell.getData(SWTBOT_DATA_ID))){
                                return false;
                            }else{
                                return shell.isVisible()
                                    || shell.isFocusControl();
                            }
                        }
                    });
                } catch (WidgetNotFoundException e) {
                    e.getMessage();
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "The shell with ID '" + widgetId + "' did not activate";
            }
        }

        /**
         * Wahr, wenn die Shell geschlossen wurde
         */
    protected class ShellClosedCondition extends DefaultCondition {

            private String widgetId;

            /**
             * Ctor
             * @param id ID der Shell
             */
            public ShellClosedCondition(final String id)
            {
                Assert.assertNotNull(id, "The shell ID was null");
                Assert.assertTrue("The shell text was empty",
                        !StringUtils.isEmpty(id));
                this.widgetId = id;
            }

            @Override
            public boolean test() throws Exception {
                try {
                    return UIThreadRunnable.syncExec(new BoolResult() {

                        @Override
                        public Boolean run() {

                            Shell shell = display.getActiveShell();
                            if(shell == null || !widgetId.equals(shell.getData(SWTBOT_DATA_ID))){
                                return true;
                            }else{
                                return !shell.isVisible()
                                    && !shell.isFocusControl();
                            }

                        }
                    });
                } catch (WidgetNotFoundException e) {
                    e.getMessage();
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "The shell with ID '" + widgetId + "' did not close";
            }
        }
}
