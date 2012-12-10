package dominion.model.cards.MoneyCard;

import dominion.model.Player;

/**
 * Goldkarte.
 *
 * @author Martin
 */
public class MoneyCardGold extends MoneyCardBase implements MoneyCard {

    /**
     * Kosten der Karte.
     */
    private static final int COST = 6;

    /**
     * Wert der Karte.
     */
    private static final int VALUE = 3;

    /**
     * Konstruktor.
     */
    public MoneyCardGold() {
        super(VALUE, COST, "Gold");
    }

    @Override
    public final void play(final Player player) {
    }
}
