package dominion.model.cards.ActionCard;

import dominion.control.cards.ChapelControl;
import dominion.model.Player;

/**
 * Kappellenkarte.
 *
 * @author Martin
 */
public class ActionCardChapel extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 2;

    /**
     * Konstruktor.
     */
    public ActionCardChapel() {
        super(COST, "Kapelle");
    }

    @Override
    public final void play(final Player player) {
        new ChapelControl(player).play();
    }
}
