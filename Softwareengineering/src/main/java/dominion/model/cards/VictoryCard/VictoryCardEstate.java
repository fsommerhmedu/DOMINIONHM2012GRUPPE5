package dominion.model.cards.VictoryCard;

import dominion.model.Player;

/**
 * Anwesenkarte.
 *
 * @author Martin
 */
public class VictoryCardEstate extends VictoryCardBase implements VictoryCard {

    /**
     * Kosten der Karte.
     */
    private static final int COST = 2;

    /**
     * Wert der Karte.
     */
    private static final int VALUE = 1;

    /**
     * Konstruktor.
     */
    public VictoryCardEstate() {
        super(VALUE, COST, "Anwesen");
    }

    @Override
    public final void play(final Player player) {
        // TODO Auto-generated method stub
    }
}
