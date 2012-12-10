package dominion.model.cards.ActionCard;

import dominion.control.cards.LibraryControl;
import dominion.model.Player;

/**
 * Bibliothekkarte.
 *
 * @author Martin
 */
public class ActionCardLibrary extends ActionCardBase implements ActionCard {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 5;

    /**
     * Konstruktor.
     */
    public ActionCardLibrary() {
        super(COST, "Bibliothek");
    }

    @Override
    public final void play(final Player player) {
        new LibraryControl(player).play();
    }
}
