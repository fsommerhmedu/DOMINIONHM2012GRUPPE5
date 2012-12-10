package dominion.model.cards.ActionCard;

import dominion.control.cards.RemodelControl;
import dominion.model.Player;

/**
 * Umbaukarte.
 *
 * @author Martin
 */
public class ActionCardRemodel extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardRemodel() {
        super(COST, "Umbau");
    }

    @Override
    public final void play(final Player player) {
        new RemodelControl(player).play();
    }
}
