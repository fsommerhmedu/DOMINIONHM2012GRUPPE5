package dominion.control;

import dominion.model.Player;
import dominion.model.cards.Card;
import java.util.List;

/**
 * Control interface.
 *
 * @author Martin
 */
public interface Control {

    /**
     * Erzeugt mehrere Spieler.
     *
     * @param names Erstellt mehrere Spieler anhand einer Liste aus Strings.
     */
    void createPlayers(List<String> names);

    /**
     * Erzeugt einen einzelnen Spieler und gibt diesen zurück.
     *
     * @param name Name des Spielers.
     * @return Instanz des Spielers
     */
    Player createPlayer(String name);

    /**
     * Spiel starten.
     */
    void startGame();

    /**
     * Startet ein neues Spiel.
     */
    void startNewGame();

    /**
     * Fügt die Startkarten zu dem Spiel hinzu.
     *
     * @param cards Ein ode mehrere Karten die hinzugefügt werden sollen.
     */
    void addCards(Card... cards);

    /**
     * Gibt die Kontrolle an den nächsten Spieler weiter.
     *
     * @param resetPlayer true, wenn Karten neu gegeben werden sollen
     */
    void nextPlayer(boolean resetPlayer);

    /**
     * Beendet die aktuelle Phase und geht in die nächste Phase über. Falls die
     * aktuelle Phase die letzte Phase ist, ist automatisch der nächste Spieler
     * an der Reihe.
     */
    void nextPhase();

    /**
     * Spiel beenden.
     */
    void exitGame();

    /**
     * Startet das Spielen, wenn die Karten und die Spieler festgelegt wurden.
     */
    void startPlaying();

    /**
     * Aktueller Spieler kauft die Karte c. Bei Erfolg wird true zurückgegeben.
     *
     * @param c Karte die gekauft wird
     * @return Wert, ob Einkauf erfolgreich
     */
    boolean buyCard(Card c);
}
