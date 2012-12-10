package dominion.model.cards.ActionCard;

import dominion.control.cards.SpyControl;
import dominion.model.Player;

/**
 * Spionkarte.
 *
 * @author Martin
 */
public class ActionCardSpy extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public ActionCardSpy() {
        super(COST, "Spion");
    }

    @Override
    public final void play(final Player player) {
        new SpyControl(player).play();
    }
}
