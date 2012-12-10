package dominion.model.cards.MoneyCard;

import dominion.model.cards.CardBase;

/**
 * Abstrakte Basisklasse für alle Geldkarten.
 *
 * @author moe
 */
public abstract class MoneyCardBase extends CardBase implements MoneyCard {

    /**
     * Wert der Karte.
     */
    private final int value;

    /**
     * @param newValue Wert der Karte.
     * @param cost Kosten der Karte.
     * @param name Name der Karte.
     */
    public MoneyCardBase(final int newValue, final int cost,
            final String name) {
        super(cost, name);
        this.value = newValue;
    }

    @Override
    public int getValue() {
        return value;
    }
}
