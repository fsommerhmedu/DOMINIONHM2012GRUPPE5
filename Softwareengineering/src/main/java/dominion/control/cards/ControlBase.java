package dominion.control.cards;

import dominion.model.Player;

/**
 * Basisklasse für alle Kartencontroller.
 *
 * @author Martin
 */
public abstract class ControlBase {

    /**
     * Instanz des Spielers.
     */
    private final Player player;

    /**
     * Konstruktor.
     *
     * @param newPlayer Spieler der die Karte auspielt
     */
    public ControlBase(final Player newPlayer) {
        this.player = newPlayer;
    }

    /**
     * Wird aufgerufen wenn die Karte gespielt wird.
     */
    public abstract void play();

    /**
     * Gibt den gesetzten Spieler zurück.
     *
     * @return gesetzter Spieler.
     */
    public final Player getPlayer() {
        return player;
    }
}
