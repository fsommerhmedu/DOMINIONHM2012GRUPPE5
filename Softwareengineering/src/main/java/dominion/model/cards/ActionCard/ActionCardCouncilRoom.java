package dominion.model.cards.ActionCard;

import dominion.control.cards.CouncilRoomControl;
import dominion.model.Player;

/**
 * Ratsverasmmlungkarte.
 *
 * @author Martin
 */
public class ActionCardCouncilRoom extends ActionCardBase {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 5;

    /**
     * Konstruktor.
     */
    public ActionCardCouncilRoom() {
        super(COST, "Ratsversammlung");
    }

    @Override
    public final void play(final Player player) {
        new CouncilRoomControl(player).play();
    }
}
