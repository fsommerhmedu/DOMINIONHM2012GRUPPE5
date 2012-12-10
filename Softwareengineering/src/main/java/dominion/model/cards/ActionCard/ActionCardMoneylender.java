package dominion.model.cards.ActionCard;

import dominion.control.cards.MoneylenderControl;
import dominion.model.Player;

/**
 * Geldverleiherkarte.
 *
 * @author Martin
 */
public class ActionCardMoneylender extends ActionCardBase
    implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardMoneylender() {
        super(COST, "Geldverleiher");
    }

    @Override
    public final void play(final Player player) {
        new MoneylenderControl(player).play();
    }
}
