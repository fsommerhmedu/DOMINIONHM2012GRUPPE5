package dominion.model.cards.ActionCard;

import dominion.control.cards.ThiefControl;
import dominion.model.Player;

/**
 * Diebkarte.
 *
 * @author Martin
 */
public class ActionCardThief extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardThief() {
        super(COST, "Dieb");
    }

    @Override
    public final void play(final Player player) {
        new ThiefControl(player).play();
    }
}
