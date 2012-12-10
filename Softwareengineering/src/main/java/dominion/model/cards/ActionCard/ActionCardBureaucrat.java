package dominion.model.cards.ActionCard;

import dominion.control.cards.BureaucratControl;
import dominion.model.Player;

/**
 * Bürokratkarte.
 *
 * @author Martin
 */
public class ActionCardBureaucrat extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardBureaucrat() {
        super(COST, "Bürokrat");
    }

    @Override
    public final void play(final Player player) {
        new BureaucratControl(player).play();
    }
}
