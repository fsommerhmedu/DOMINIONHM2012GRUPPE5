package dominion.model.cards.ActionCard;

import dominion.control.cards.ChancellorControl;
import dominion.model.Player;

/**
 * Kanzlerkarte.
 *
 * @author Martin
 */
public class ActionCardChancellor extends ActionCardBase {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 3;

    /**
     * Konstruktor.
     */
    public ActionCardChancellor() {
        super(COST, "Kanzler");
    }

    @Override
    public final void play(final Player player) {
        new ChancellorControl(player).play();
    }
}
