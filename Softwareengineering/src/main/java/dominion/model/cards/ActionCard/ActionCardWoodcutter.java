package dominion.model.cards.ActionCard;

import dominion.control.cards.WoodcutterControl;
import dominion.model.Player;

/**
 * Holzfällerkarte.
 *
 * @author Martin
 */
public class ActionCardWoodcutter extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 3;

    /**
     * Konstruktor.
     */
    public ActionCardWoodcutter() {
        super(COST, "Holzfäller");
    }

    @Override
    public final void play(final Player player) {
        new WoodcutterControl(player).play();
    }
}
