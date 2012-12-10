package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller Werkstatt.
 *
 * @author Florian
 */
public class WorkshopControl extends ControlBase {

    /**
     * @param player Spieler der die Karte auspielt
     */
    public WorkshopControl(final Player player) {
        super(player);
    }

    /**
     * Nimm dir eine Karte, die bis zu vier kostet.
     * <br><br>
     * Spieler bekommt 4 Geld.
     */
    @Override
    public final void play() {
        final int cost = 4;
        getPlayer().addMoney(cost);
    }
}
