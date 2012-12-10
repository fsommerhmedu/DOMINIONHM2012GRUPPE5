package dominion.model.cards.VictoryCard;

import dominion.model.Player;
import dominion.model.cards.CardBase;

/**
 * Abstrakte Basisklasse für alle Punktekarten.
 *
 * @author moe
 */
public abstract class VictoryCardBase extends CardBase implements VictoryCard {

    /**
     * Wert der Siegeskarte.
     */
    private final int value;

    /**
     * @param newValue Wert der Siegeskarte.
     * @param cost Kosten der Siegeskarte.
     * @param name Name der Siegeskarte.
     */
    public VictoryCardBase(final int newValue, final int cost,
            final String name) {
        super(cost, name);
        this.value = newValue;
    }

    @Override
    public int getValue(final Player player) {
        return value;
    }
}
