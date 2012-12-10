package dominion.control.cards;

import dominion.model.Player;

/**
 * Holzfäller.
 *
 * @author Florian
 */
public class WoodcutterControl extends ControlBase {

    /**
     * Gibt die Anzahl der zu ziehenden Karten an.
     */
    private static final int MONEY_TO_ADD = 2;

    /**
     * @param player Spieler der die Karte spielt
     */
    public WoodcutterControl(final Player player) {
        super(player);
    }

    /**
     * Plus ein Kauf. Plus zwei Geld.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();

        player.addMoney(MONEY_TO_ADD);
        player.addPurchase(1);
    }
}
