package dominion.model.cards.ActionCard;

import dominion.control.cards.WitchControl;
import dominion.model.Player;

// TODO: Jeder Spieler muss eine Fluchkarte nehmen!
/**
 * Hexenkarte.
 *
 * @author Martin
 */
public class ActionCardWitch extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 5;

    /**
     * Konstruktor.
     */
    public ActionCardWitch() {
        super(COST, "Hexe");
    }

    @Override
    public final void play(final Player player) {
        new WitchControl(player).play();
    }
}
