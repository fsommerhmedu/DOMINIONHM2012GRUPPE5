package dominion.view;

import dominion.control.Control;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import dominion.model.Game;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;

/**
 * Klasse für die Anzahl der Spieler.
 *
 * @author Florian
 */
public class SelectPlayers extends DominionShell {

    /**
     * Anzahl der maximal erlaubten Spieler.
     */
    private static final int MAX_PLAYERS = 4;

    /**
     * Anzahl der minimal benötigten Spieler.
     */
    private static final int MIN_PLAYERS = 2;

    /**
     * Anzahl Spalten für Grid Layout.
     */
    private static final int NUM_COLUMNS = 2;

    /**
     * Vertikaler Freiraum.
     */
    private static final int VERTICAL_SPACING = 10;

    /**
     * Abstandshöhe.
     */
    private static final int MARGIN_HEIGHT = 10;

    /**
     * Abstandsbreite.
     */
    private static final int MARGIN_WIDTH = 10;

    /**
     * Label der Spieler.
     */
    private Label labelPlayers;

    /**
     * Combobox der Spieler.
     */
    private Combo comboPlayers;

    /**
     * Button.
     */
    private Button buttonPlayers;

    /**
     * Spieler gruppen.
     */
    private Group groupPlayers;

    /**
     * Enthält die Namen der Spieler.
     */
    private final List<Text> str = new ArrayList<Text>();

    /**
     * Konstruktor.
     *
     * @param model Model des Spiels.
     * @param control Controller des Spiels.
     * @param display Displayinstanz.
     */
    public SelectPlayers(final Game model, final Control control,
            final Display display) {
        super(model, control, display);
    }

    /**
     * GUI erstellen.
     */
    private void createGui() {
        getShell().setLayout(new GridLayout());
    }

    /**
     * Anzahl der Spieler abfragen.
     */
    private void askNumberOfPlayers() {
        final Shell shell = getShell();
        groupPlayers = new Group(shell, SWT.SHADOW_NONE);
        setLayout(groupPlayers);
        labelPlayers = new Label(groupPlayers, SWT.NONE);
        labelPlayers.setText("Bitte wählen Sie die Anzahl der Spieler");

        comboPlayers = new Combo(groupPlayers, SWT.DROP_DOWN | SWT.READ_ONLY);
        comboPlayers.setData(DATA_KEY_ID, "comboPlayers");
        for (int i = MIN_PLAYERS; i <= MAX_PLAYERS; i++) {
            comboPlayers.add(i + " Spieler");
        }
        comboPlayers.select(0);

        buttonPlayers = new Button(groupPlayers, SWT.CENTER);
        buttonPlayers.setData(DATA_KEY_ID, "buttonNext");
        buttonPlayers.setText("Weiter");
        buttonPlayers.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event e) {
                if (!groupPlayers.isDisposed()) {
                    switch (e.type) {
                        case SWT.Selection:
                            //Index des Auswahlfeldes
                            // (+2 (MIN_PLAYER), da Index 0 = 2 Spieler)
                            int number = comboPlayers.indexOf(
                                    comboPlayers.getText()) + MIN_PLAYERS;
                            groupPlayers.dispose();
                            inputPlayerNames(number);

                            break;

                        default:
                            break;
                    }
                }
            }
        });
    }

    /**
     * Listet die Eingabefelder für die Spielernamen auf.
     *
     * @param countPlayer Anzahl der (menschlichen) Spieler
     */
    public final void inputPlayerNames(final int countPlayer) {
        // Shell-Fenster Einstellen
        final Shell shell = getShell();
        shell.setData(DATA_KEY_ID, "playerNames");
        shell.setText("Spielernamen wählen");

        // Bild für das Fenster-Icon
        Image image = new Image(getDisplay(),
                DominionShell.class.getResourceAsStream("Images/Dominion.jpg"));
        shell.setImage(image);
        final Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setData(DATA_KEY_ID, "errorLabel");
        errorLabel.setText("Wählen Sie die Spielernamen");


        Composite playerNames = new Group(shell, SWT.BORDER);
        Composite playerButtons = new Composite(shell, SWT.NONE);
        playerButtons.setLayout(new GridLayout(1, true));
        playerNames.setLayout(new GridLayout(2, false));

        Label label; // Beschriftungen
        Text text; // Textfelder
        for (int i = 0; i < countPlayer; i++) {
            label = new Label(playerNames, SWT.NONE);
            label.setText("Spieler " + (i + 1) + ": ");
            text = new Text(playerNames, SWT.BORDER);
            text.setLayoutData(new GridData(200, 20));
            text.setData(DATA_KEY_ID, "playerName" + i);
            str.add(text);
        }

        // Button "Weiter" und Klickaktion
        final Button next = new Button(playerButtons, SWT.PUSH);
        next.setText("weiter");
        next.setData(DATA_KEY_ID, "buttonNext");
        next.setLayoutData(new GridData());
        next.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (e.doit) {
                    List<String> names = new ArrayList<String>();
                    int i = 0;
                    for (Text t : str) {
                        String name = t.getText();
                        if (name.trim().equals("")) {
                            errorLabel.setText("Bitte alle Felder ausfüllen.");
                            errorLabel.setForeground(getDisplay().
                                    getSystemColor(SWT.COLOR_RED));
                            shell.layout();
                            return;
                        }
                        if (names.contains(name)) {
                            errorLabel.setText(
                                    "Ein Name darf nur einmal vorkommen.");
                            errorLabel.setForeground(getDisplay().
                                    getSystemColor(SWT.COLOR_RED));
                            shell.layout();
                            return;
                        }
                        names.add(name);
                        i++;
                    }
                    shell.dispose();
                    getControl().createPlayers(names);
                }
            }
        });

        shell.layout();
    }

    /**
     * Layout setzen.
     *
     * @param widget composite auf das das layout gesetzt werden soll.
     */
    private void setLayout(final Composite widget) {
        GridLayout layout = new GridLayout();
        layout.verticalSpacing = VERTICAL_SPACING;
        layout.marginHeight = MARGIN_HEIGHT;
        layout.marginWidth = MARGIN_WIDTH;
        layout.numColumns = NUM_COLUMNS;
        layout.makeColumnsEqualWidth = true;

        widget.setLayout(layout);
    }

    @Override
    public final void display() {
        createGui();
        askNumberOfPlayers();

        final Shell shell = getShell();
        shell.setData(DATA_KEY_ID, "selectPlayer");
        shell.setText("Spieleranzahl wählen");
        Image image = new Image(getDisplay(),
                DominionShell.class.getResourceAsStream("Images/Dominion.jpg"));
        shell.setImage(image);
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!getDisplay().readAndDispatch()) {
                getDisplay().sleep();
            }
        }
    }
}
