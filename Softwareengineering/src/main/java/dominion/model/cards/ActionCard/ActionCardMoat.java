package dominion.model.cards.ActionCard;

import dominion.control.cards.MoatControl;
import dominion.model.Player;

/**
 * Burggrabenkarte.
 *
 * @author Martin
 */
public class ActionCardMoat extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 2;

    /**
     * Konstruktor.
     */
    public ActionCardMoat() {
        super(COST, "Burggraben");
    }

    @Override
    public final void play(final Player player) {
        new MoatControl(player).play();
    }
}
