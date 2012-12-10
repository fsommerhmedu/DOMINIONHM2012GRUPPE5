package dominion.model.cards.ActionCard;

import dominion.control.cards.MarketControl;
import dominion.model.Player;

/**
 * Marktkarte.
 *
 * @author Martin
 */
public class ActionCardMarket extends ActionCardBase {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 5;

    /**
     * Konstruktor.
     */
    public ActionCardMarket() {
        super(COST, "Markt");
    }

    @Override
    public final void play(final Player player) {
        new MarketControl(player).play();
    }
}
