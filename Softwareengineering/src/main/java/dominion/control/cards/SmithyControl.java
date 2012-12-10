package dominion.control.cards;

import dominion.model.Player;

/**
 * Karte Schmiede.
 *
 * @author Florian
 */
public class SmithyControl extends ControlBase {

    /**
     * Gibt die Anzahl der zu ziehenden Karten an.
     */
    private static final int CARDS_TO_PURCHASE = 3;

    /**
     * @param player Spieler der die Karte ausspielt
     */
    public SmithyControl(final Player player) {
        super(player);
    }

    /**
     * Plus drei Karten.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();

        for (int i = 0; i < CARDS_TO_PURCHASE; i++) {
            player.addCardToHand(player.getUppestCard());
        }
    }
}
