package dominion.model.cards.ActionCard;

import dominion.control.cards.LaboratoryControl;
import dominion.model.Player;

/**
 * Laboratoriumkate.
 *
 * @author Martin
 */
public class ActionCardLaboratory extends ActionCardBase {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 5;

    /**
     * Konstruktor.
     */
    public ActionCardLaboratory() {
        super(COST, "Laboratorium");
    }

    @Override
    public final void play(final Player player) {
        new LaboratoryControl(player).play();
    }
}
