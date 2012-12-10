package dominion.control;

import dominion.common.GameState;
import dominion.model.GameImpl;
import dominion.model.Player;
import dominion.model.PlayerImpl;
import dominion.model.cards.Card;
import dominion.common.GamePhase;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Martin
 */
public class ControlImpl extends Thread implements Control {

    /**
     * Model.
     */
    private final GameImpl model;

    /**
     * Konstruktor erwartet eine Instanz des Models.
     *
     * @param newModel
     *            Model des Spiels.
     */
    public ControlImpl(final GameImpl newModel) {
        this.model = newModel;
    }

    @Override
    public final Player createPlayer(final String name) {
        return new PlayerImpl(name);
    }

    @Override
    public final void startGame() {
        synchronized (model) {
            model.setGameState(GameState.SetPlayers);
        }
        model.notifyObservers();
    }

    @Override
    public final void startNewGame() {
        synchronized (model) {
            model.setGameState(GameState.SetPlayers);
            model.clear();
        }

        model.notifyObservers();
    }

    @Override
    public final void createPlayers(final List<String> names) {
        synchronized (model) {
            for (String name : names) {
                model.addPlayer(createPlayer(name));
            }

            model.setGameState(GameState.SelectCards);
        }
        model.notifyObservers();
    }

    @Override
    public final void addCards(final Card... cards) {
        synchronized (model) {
            for (Card card : cards) {
                model.addCard(card);
            }

            model.setGameState(GameState.GameScreen);
        }
        model.notifyObservers();
    }

    @Override
    public final void exitGame() {
        synchronized (model) {
            model.setGameState(GameState.End);
        }
        model.notifyObservers();
    }

    @Override
    public final void nextPlayer(final boolean resetPlayer) {
        synchronized (model) {
            if (resetPlayer) {
                model.getCurrentPlayer().resetPlayer();
            }
            model.setCurrentPlayer((model.getCurrentPlayerIndex() + 1) % model.getPlayerCount());
            model.setPhase(GamePhase.Action);
            model.notifyObservers();
        }
    }

    @Override
    public final void nextPhase() {
        synchronized (model) {
            // Kaufphase, Aktionsphase, Aufraeumphase
            switch (model.getPhase()) {
                case Action:
                    model.setPhase(GamePhase.Buy);
                    model.notifyObservers();
                    break;
                case Buy:
                    model.setPhase(GamePhase.CleanUp);
                    model.getCurrentPlayer().resetPlayer();
                    model.notifyObservers();
                    break;
                case CleanUp:
                    nextPlayer(false);
                    break;
                default:
                    assert false : "Illegal phase";
            }
        }
    }

    @Override
    public final void startPlaying() {
        synchronized (model) {
            model.setCurrentPlayer(
                    new Random().nextInt(model.getPlayerCount()));
            model.setPhase(GamePhase.Action);
            model.notifyObservers();
        }
    }

    @Override
    public final boolean buyCard(final Card c) {

        Player currentPlayer = model.getCurrentPlayer();

        if (currentPlayer.getPurchaseCounter() > 0) {
            if (currentPlayer.getMoney() >= c.getCost()) {
                currentPlayer.addCardToPlayedCards(c);
                currentPlayer.addMoney((-1) * c.getCost());
                currentPlayer.addPurchase(-1);
                return true;
            }
        }
        return false;
    }
}
