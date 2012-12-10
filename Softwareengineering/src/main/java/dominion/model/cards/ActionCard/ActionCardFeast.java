 package dominion.model.cards.ActionCard;

import dominion.control.cards.FeastControl;
import dominion.model.Player;

/**
 * Festmahlkarte.
 *
 * @author Martin
 */
public class ActionCardFeast extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardFeast() {
        super(COST, "Festmahl");
    }

    @Override
    public final void play(final Player player) {
        new FeastControl(player).play();
    }
}
