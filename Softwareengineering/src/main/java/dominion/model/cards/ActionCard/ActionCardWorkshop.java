package dominion.model.cards.ActionCard;

import dominion.control.cards.WorkshopControl;
import dominion.model.Player;

/**
 * Werkstattkarte.
 *
 * @author Martin
 */
public class ActionCardWorkshop extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 3;

    /**
     * Konstruktor.
     */
    public ActionCardWorkshop() {
        super(COST, "Werkstatt");
    }

    @Override
    public final void play(final Player player) {
        new WorkshopControl(player).play();
    }
}
