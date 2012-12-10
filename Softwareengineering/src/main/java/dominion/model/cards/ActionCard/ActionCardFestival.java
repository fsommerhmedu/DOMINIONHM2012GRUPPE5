package dominion.model.cards.ActionCard;

import dominion.control.cards.FestivalControl;
import dominion.model.Player;

/**
 * Jahrmarktkarte.
 *
 * @author Martin
 */
public class ActionCardFestival extends ActionCardBase {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 5;

    /**
     * Konstruktor.
     */
    public ActionCardFestival() {
        super(COST, "Jahrmarkt");
    }

    @Override
    public final void play(final Player player) {
        new FestivalControl(player).play();
    }
}
