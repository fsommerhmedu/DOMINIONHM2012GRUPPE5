package dominion.model.cards.ActionCard;

import dominion.control.cards.VillageControl;
import dominion.model.Player;

/**
 * Dorfkarte.
 *
 * @author Martin
 */
public class ActionCardVillage extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 3;

    /**
     * Konstruktor.
     */
    public ActionCardVillage() {
        super(COST, "Dorf");
    }

    @Override
    public final void play(final Player player) {
        new VillageControl(player).play();
    }
}
