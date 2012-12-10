package dominion.model.cards.ActionCard;

import dominion.control.cards.ThroneRoomControl;
import dominion.model.Player;

/**
 * Thronsaalkarte.
 *
 * @author Martin
 */
public class ActionCardThroneRoom extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardThroneRoom() {
        super(COST, "Thronsaal");
    }

    @Override
    public final void play(final Player player) {
        new ThroneRoomControl(player).play();
    }
}
