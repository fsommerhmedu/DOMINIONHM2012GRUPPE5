package dominion.model.cards.MoneyCard;

import dominion.model.Player;

/**
 * Kupferkarte.
 *
 * @author Martin
 */
public class MoneyCardCooper extends MoneyCardBase implements MoneyCard {

    /**
     * Konstruktor.
     */
    public MoneyCardCooper() {
        super(1, 0, "Kupfer");
    }

    @Override
    public final void play(final Player player) {
    }
}
