package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller für Ratsversammlung.
 *
 * @author moe
 */
public class CouncilRoomControl extends ControlBase {

    /**
     * Gibt die Anzahl der zu ziehenden Karten an.
     */
    private static final int CARDS_TO_PURCHASE = 4;

    /**
     * @param player Spieler der die Karte auspielt
     */
    public CouncilRoomControl(final Player player) {
        super(player);
    }

    @Override
    public final void play() {
        final Player player = getPlayer();

        for (int i = 0; i < CARDS_TO_PURCHASE; i++) {
            player.addCardToHand(player.getUppestCard());
        }
        player.addPurchase(1);
        //TODO: Jeder Mitspieler zieht sofort eine Karte nach
    }
}
