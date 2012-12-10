package dominion.model.cards.VictoryCard;

import dominion.model.Player;

/**
 * Herzogtumkarte.
 *
 * @author Martin
 */
public class VictoryCardDuchy extends VictoryCardBase implements VictoryCard {

    /**
     * Kosten der Karte.
     */
    private static final int COST = 5;

    /**
     * Wert der Karte.
     */
    private static final int VALUE = 3;

    /**
     * Konstruktor.
     */
    public VictoryCardDuchy() {
        super(VALUE, COST, "Herzogtum");
    }

    @Override
    public final void play(final Player player) {
        // TODO Auto-generated method stub
    }
}
