package dominion.control.cards;

import dominion.model.Player;

/**
 * Karte Dorf.
 *
 * @author Florian
 */
public class VillageControl extends ControlBase {

    /**
     * @param player Spieler der die Karte spielt
     */
    public VillageControl(final Player player) {
        super(player);
    }

    /**
     * Plus eine Karte.
     * Plus zwei Aktionen.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();
        player.addCardToHand(player.getUppestCard());
        player.addActions(2);
    }
}
