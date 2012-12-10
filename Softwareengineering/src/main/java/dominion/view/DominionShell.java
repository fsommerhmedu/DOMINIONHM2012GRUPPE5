package dominion.view;

import dominion.control.Control;
import dominion.model.Game;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * Basisklasse für alle Views.
 *
 * @author Tobi
 */
public abstract class DominionShell {

    /**
     * Data key id.
     */
    public static final String DATA_KEY_ID = "dominion.id";

    /**
     * Data key card.
     */
    public static final String DATA_KEY_CARD = "dominion.card";

    /**
     * Model des Spiels.
     */
    private final Game model;

    /**
     * Controller des Spiels.
     */
    private final Control control;

    /**
     * Display des Spiels.
     */
    private final Display display;

    /**
     * Shellinstanz.
     */
    private final Shell shell;

    /**
     * Default style.
     */
    private static final int DEFAULT_STYLE = -1;

    /**
     * Höhe des Fensters.
     */
    public static final int HEIGHT = 768;

    /**
     * Breite des Fensters.
     */
    public static final int WIDTH = 1024;

    /**
     * Default x location of the window.
     */
    public static final int DEFAULT_LOCATION_X = 0;

    /**
     * Default y location of the window.
     */
    public static final int DEFAULT_LOCATION_Y = 0;

    /**
     * Startet die View.
     */
    public abstract void display();

    /**
     * @param newModel Model des Spiels
     * @param newControl Controller des Spiels
     * @param newDisplay Displayinstanz
     */
    public DominionShell(final Game newModel, final Control newControl,
            final Display newDisplay) {
        this(newModel, newControl, newDisplay, DEFAULT_STYLE, true);
    }

    /**
     * @param newModel Model des Spiels.
     * @param newControl Controller des Spiels.
     * @param newDisplay Displayinstanz.
     * @param newStyle Style der Shell.
     * @param withMenu Gibt an, ob das Menü angezeigt werden soll oder nicht.
     */
    public DominionShell(final Game newModel, final Control newControl,
            final Display newDisplay, final int newStyle,
            final boolean withMenu) {
        model = newModel;
        control = newControl;
        display = newDisplay;
        if (newStyle == DEFAULT_STYLE) {
            shell = new Shell(newDisplay, SWT.SHELL_TRIM);
        } else {
            shell = new Shell(newDisplay, SWT.SHELL_TRIM | newStyle);
        }


        //set default size for shell.
        shell.setSize(WIDTH, HEIGHT);
        shell.setLocation(DEFAULT_LOCATION_X, DEFAULT_LOCATION_Y);
        if (withMenu) {
            createMenu();
        }
    }

    /**
     * Erstellt das Menü für die Shell.
     */
    private void createMenu() {

        final Menu menuBar = new Menu(shell, SWT.BAR);
        final MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuHeader.setText("&File");

        final Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        fileMenuHeader.setMenu(fileMenu);

        final MenuItem fileNewGameItem = new MenuItem(fileMenu, SWT.PUSH);
        fileNewGameItem.setText("&New Game");

        final MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
        fileExitItem.setText("E&xit");

        fileExitItem.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent se) {
                control.exitGame();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent se) {
                control.exitGame();
            }
        });

        fileNewGameItem.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent se) {
                dispose();
                control.startNewGame();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent se) {
                dispose();
                control.startNewGame();
            }
        });

        shell.setMenuBar(menuBar);
    }

    /**
     * Gibt die Controlinstanz zurück.
     *
     * @return Controlinstanz.
     */
    public final Control getControl() {
        return control;
    }

    /**
     * Gibt die Displayinstanz zurück.
     *
     * @return Displayinstanz.
     */
    public final Display getDisplay() {
        return display;
    }

    /**
     * Gib die Modelinstanz zurück.
     *
     * @return Modelinstanz.
     */
    public final Game getModel() {
        return model;
    }

    /**
     * Gibt die Shellinstanz zurück.
     *
     * @return Shellinstanz.
     */
    public final Shell getShell() {
        return shell;
    }

    /**
     * Schaltet das Display "aus".
     */
    public final void dispose() {
        shell.dispose();
    }
}
