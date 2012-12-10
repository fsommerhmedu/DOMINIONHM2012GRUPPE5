package dominion.model;

import dominion.common.GamePhase;
import dominion.common.GameState;
import dominion.model.cards.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Standardimplementierung des Game-Interfaces.
 *
 * @author Moe / Michi
 */
public class GameImpl extends Observable implements Game {

    /**
     * Momentaner Status des Spiels.
     */
    private GameState gameState = GameState.Start;

    /**
     * Aktuelle Phase.
     */
    private GamePhase gamePhase = GamePhase.None;

    /**
     * Aktuelle Spieler.
     */
    private final List<Player> players;

    /**
     * Aktuelles Kartendeck.
     */
    private final List<Card> cards;

    /**
     * Aktueller Spieler.
     */
    private int currentPlayer;

    /**
     * Erzeugt eine ArrayListe mit Spielern eine mit Karten.
     */
    public GameImpl() {
        players = new ArrayList<Player>();
        cards = new ArrayList<Card>();
    }

    /**
     * Gibt eine unmodifizierbare Liste der Karten zurück.
     *
     * @return Unmodifizierbare Liste der Karten.
     */
    @Override
    public final List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * Gibt eine unmodifizierbare Liste der Spieler zurück.
     *
     * @return unmodifizierbare Liste aller Spieler.
     */
    public final List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public final int getPlayerCount() {
        assert (players != null);
        return players.size();
    }

    @Override
    public final Player getPlayer(final int index) {
        if (index < 0 || index > getPlayerCount() - 1) {
            throw new IndexOutOfBoundsException();
        }

        assert (players != null);
        Player player = players.get(index);

        assert (player != null);
        return player;
    }

    @Override
    public final void addPlayer(final Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player must not be null");
        }

        assert (players != null);

        if (players.contains(player)) {
            throw new IllegalStateException(
                    "player already participates in the game");
        }

        players.add(player);
        setChanged();
    }

    @Override
    public final int getIndex(final Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player must not be null");
        }

        assert (players != null);
        int index = players.indexOf(player);

        return index;
    }

    /**
     * Gibt den index des aktuellen Spielers zurück.
     *
     * @return aktueller Spieler-Index.
     */
    @Override
    public final int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    /**
     * Gibt den aktuellen Spieler zurück.
     *
     * @return aktueller Spieler.
     */
    @Override
    public final Player getCurrentPlayer() {
        return getPlayer(currentPlayer);
    }

    @Override
    public final void setCurrentPlayer(final int index) {
        currentPlayer = index;
    }

    @Override
    public final void addCard(final Card card) {
        if (card == null) {
            throw new IllegalArgumentException("card must not be null");
        }

        assert (card != null);

        if (cards.contains(card)) {
            throw new IllegalStateException("card already exists in deck");
        }

        cards.add(card);
        setChanged();
    }

    @Override
    public final GameState getGameState() {
        return gameState;
    }

    @Override
    public final void setGameState(final GameState state) {
        this.gameState = state;
        setChanged();
    }

    @Override
    public final void clear() {
        players.clear();
        cards.clear();
        setChanged();
    }

    @Override
    public final GamePhase getPhase() {
        return gamePhase;
    }

    @Override
    public final void setPhase(final GamePhase phase) {
        gamePhase = phase;
    }
}
