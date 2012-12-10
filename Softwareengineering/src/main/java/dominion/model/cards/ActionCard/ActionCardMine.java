package dominion.model.cards.ActionCard;

import dominion.control.cards.MineControl;
import dominion.model.Player;

// TODO: Entsorge eine Geldkarte. Nimm eine Geldkarte die bis zu 3 mehr kostet.
// Nimm die Geldkarte sofort auf die Hand!
/**
 * Minenkarte.
 *
 * @author Martin
 */
public class ActionCardMine extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 5;

    /**
     * Konstruktor.
     */
    public ActionCardMine() {
        super(COST, "Mine");
    }

    @Override
    public final void play(final Player player) {
        new MineControl(player).play();
    }
}
