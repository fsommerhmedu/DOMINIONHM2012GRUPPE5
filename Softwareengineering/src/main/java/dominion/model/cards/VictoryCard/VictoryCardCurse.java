package dominion.model.cards.VictoryCard;

import dominion.model.Player;

/**
 * Curse Karte.
 *
 * Bekommt man, wenn ein Gegenspieler eine Hexe spielt.
 *
 * @author Martin
 */
public class VictoryCardCurse extends VictoryCardBase implements VictoryCard {

    /**
     * Konstruktor.
     */
    public VictoryCardCurse() {
        super(-1, 0, "Fluch");
    }

    @Override
    public final void play(final Player player) {
    }
}
