package dominion.model.cards.ActionCard;

import dominion.model.Player;
import dominion.model.cards.CardBase;

/**
 * Abstrakte Klasse für Aktionskarten.
 *
 * @author moe
 */
public abstract class ActionCardBase extends CardBase implements ActionCard {

    /**
     * @param cost Kosten der Karte.
     * @param name Name der Karte.
     */
    protected ActionCardBase(final int cost, final String name) {
        super(cost, name);
    }

    @Override
    public abstract void play(Player player);
}
