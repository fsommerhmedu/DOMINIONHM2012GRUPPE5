package dominion.model.cards.VictoryCard;

import dominion.model.Player;

/**
 * Provinzkarte.
 *
 * @author Martin
 */
public class VictoryCardProvince extends VictoryCardBase
        implements VictoryCard {

    /**
     * Kosten der Karte.
     */
    private static final int COST = 8;

    /**
     * Wert der Karte.
     */
    private static final int VALUE = 6;

    /**
     * Konstruktor.
     */
    public VictoryCardProvince() {
        super(VALUE, COST, "Provinz");
    }

    @Override
    public final void play(final Player player) {
        // TODO Auto-generated method stub
    }
}
