package dominion.model;

import dominion.common.GamePhase;
import java.util.List;
import dominion.common.GameState;
import dominion.model.cards.Card;

/**
 * Model Interface.
 *
 * @author Moe
 */
public interface Game {

    /**
     * Fügt einen Spieler zu den aktuellen Spieler hinzu.
     *
     * @param player Spieler, der hinzugefügt werden soll.
     */
    void addPlayer(Player player);

    /**
     * Gibt die Anzahl der aktuellen Spieler zurück.
     *
     * @return Anzahl aktueller Spieler.
     */
    int getPlayerCount();

    /**
     * Gibt den Spieler anhand eines Index zurück.
     *
     * @param index Index des Spielers.
     * @return Spieler
     */
    Player getPlayer(int index);

    /**
     * Liefert den Index eines Spielers.
     *
     * @param player Spieler.
     * @return Index des Spielers oder -1, falls dieser Spieler nicht zu
     * den aktuellen Spielern gehört.
     */
    int getIndex(Player player);

    /**
     * Gibt den index des aktuellen Spielers zurück.
     *
     * @return aktueller Spieler-Index.
     */
    int getCurrentPlayerIndex();

     /**
     * Gibt den aktuellen Spieler zurück.
     *
     * @return aktueller Spieler.
     */
     Player getCurrentPlayer();

     /**
     * Setzt den aktuellen Spieler auf den übergebenen Index-Wert.
     *
     * @param index Spieler-Index
     */
    void setCurrentPlayer(final int index);

    /**
     * Fügt eine Karte zu dem Aktuellen Kartendeck hinzu.
     *
     * @param card Karte die hinzugefügt werden soll.
     */
    void addCard(Card card);

    /**
     * Gibt eine unmodifizierbare Liste der Karten zurück.
     *
     * @return alle Karten.
     */
    List<Card> getCards();

    /**
     * Fragt den momentanen Zustand des Spiels ab.
     *
     * @return Momentaner Spielstatus.
     */
    GameState getGameState();

    /**
     * Setzt den aktuellen Spielstatus.
     *
     * @param state Status des Spiels
     */
    void setGameState(GameState state);

    /**
     * Gibt die aktuelle Phase zurück.
     *
     * @return Phase.
     */
    GamePhase getPhase();

    /**
     * Setzt die aktuelle Phase.
     *
     * @param phase Aktuelle Phase.
     */
    void setPhase(GamePhase phase);

    /**
     * Clears the model so a new game can be started with the same model.
     */
    void clear();
}
