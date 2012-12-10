package dominion.model.cards.MoneyCard;

import dominion.model.Player;

/**
 * Silberkarte.
 *
 * @author Martin
 */
public class MoneyCardSilver extends MoneyCardBase implements MoneyCard {

    /**
     * Kosten der Karte.
     */
    private static final int COST = 3;

    /**
     * Wert der Karte.
     */
    private static final int VALUE = 2;

    /**
     * Konstruktor.
     */
    public MoneyCardSilver() {
        super(VALUE, COST, "Silber");
    }

    @Override
    public final void play(final Player player) {
    }
}
