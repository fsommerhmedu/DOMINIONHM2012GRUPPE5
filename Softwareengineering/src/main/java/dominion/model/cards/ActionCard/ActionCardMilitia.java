package dominion.model.cards.ActionCard;

import dominion.control.cards.MilitiaControl;
import dominion.model.Player;

/**
 * Milizkarte.
 *
 * @author Martin
 */
public class ActionCardMilitia extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardMilitia() {
        super(COST, "Miliz");
    }

    @Override
    public final void play(final Player player) {
        new MilitiaControl(player).play();
    }
}
