package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller Markt.
 *
 * @author moe
 */
public class MarketControl extends ControlBase {

    /**
     * @param player Spieler der die Karte auspielt
     */
    public MarketControl(final Player player) {
        super(player);
    }

    @Override
    public final void play() {
        final Player player = getPlayer();

        player.addCardToHand(player.getUppestCard());
        player.addActions(1);
        player.addPurchase(1);
        player.addMoney(1);
    }
}
