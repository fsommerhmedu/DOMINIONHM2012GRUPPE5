package dominion.model.cards.ActionCard;

import dominion.control.cards.SmithyControl;
import dominion.model.Player;

/**
 * Schmiedekarte.
 *
 * @author Martin
 */
public class ActionCardSmithy extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardSmithy() {
        super(COST, "Schmiede");
    }

    @Override
    public final void play(final Player player) {
        new SmithyControl(player).play();
    }
}
