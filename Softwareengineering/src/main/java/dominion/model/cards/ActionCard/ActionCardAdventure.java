package dominion.model.cards.ActionCard;

import dominion.control.cards.AdventureControl;
import dominion.model.Player;

/**
 * Abenteuerkarte.
 *
 * @author Martin
 */
public class ActionCardAdventure extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 6;

    /**
     * Konstruktor.
     */
    public ActionCardAdventure() {
        super(COST, "Abenteuer");
    }

    @Override
    public final void play(final Player player) {
        new AdventureControl(player).play();
    }
}
