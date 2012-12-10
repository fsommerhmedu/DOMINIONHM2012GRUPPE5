/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.view;

import dominion.common.GameState;
import dominion.control.ControlImpl;
import dominion.model.GameImpl;
import java.util.Observable;
import java.util.Observer;
import org.eclipse.swt.widgets.Display;

/**
 *
 * @author Martin
 */
public class Main implements Observer {

    /**
     * Controllinstanz.
     */
    private final ControlImpl control;

    /**
     * Modelinstanz.
     */
    private final GameImpl model;

    /**
     * Displayinstanz.
     */
    private final Display display;

    /**
     * Erzeugt ein neues Main mit einem neuen Display.
     *
     * @param newControl Controller des Spiels.
     * @param newModel Model des Spiels.
     */
    public Main(final ControlImpl newControl, final GameImpl newModel) {
        this(newControl, newModel, new Display());
    }

    /**
     * Instanzvariablen setzen, Observer hinzufügen, doAction() ausführen.
     *
     * @param newControl Controller des Spiels.
     * @param newModel Model des Spiels
     * @param newDisplay Display des Spiels.
     */
    public Main(final ControlImpl newControl, final GameImpl newModel,
            final Display newDisplay) {
        this.model = newModel;
        newModel.addObserver(this);
        this.control = newControl;
        this.display = newDisplay;

        doAction();
    }

    /**
     * Anzahl und Namen der Spieler werden abgefragt.
     */
    public final void askPlayers() {
        SelectPlayers selectPlayer = new SelectPlayers(model, control, display);
        selectPlayer.display();
    }

    /**
     * Zu wählende Aktionskarten werden angezeigt.
     */
    private void selectCards() {
        SelectCards cards = new SelectCards(model, control, display);
        cards.display();
    }

    @Override
    public final void update(final Observable o, final Object o1) {
        doAction();
    }

    /**
     * Spiel wird gestartet.
     */
    private void startGame() {
        StartScreen start = new StartScreen(model, control, display);
        start.display();
    }

    /**
     * Hauptbildschirm wird angezeigt.
     */
    private void gameScreen() {
        GameScreen gsc = new GameScreen(model, control, display);
        gsc.display();
    }

    /**
     * Es wird eine Aktion anhand des Spielstatuses ausgwählt und gestartet.
     */
    public final void doAction() {
        if (model.getGameState() == GameState.Start) {
            startGame();
        } else if (model.getGameState() == GameState.SetPlayers) {
            askPlayers();
        } else if (model.getGameState() == GameState.SelectCards) {
            selectCards();
        } else if (model.getGameState() == GameState.GameScreen) {
            gameScreen();
        } else if (model.getGameState() == GameState.End) {
            display.dispose();
        }
    }
}
