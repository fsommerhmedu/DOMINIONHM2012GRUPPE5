package dominion.model.cards.ActionCard;

import dominion.control.cards.CellarControl;
import dominion.model.Player;

/**
 * Keller-Aktionskarte.
 *
 * Spieler erhält eine zusätzliche Aktion, legt alle seine Karten ab und zieht
 * Karten vom Nachziehstapel.
 *
 * @author moe
 */
public class ActionCardCellar extends ActionCardBase {

    /**
     * Variable für die Kosten.
     */
    private static final int COST = 2;

    /**
     * Konstruktor.
     */
    public ActionCardCellar() {
        super(COST, "Keller");
    }

    @Override
    public final void play(final Player player) {
        new CellarControl(player).play();
    }
}
