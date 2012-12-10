package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller Hexe.
 *
 * @author Florian
 */
public class WitchControl extends ControlBase {

    /**
     * @param player Spieler der die Karte spielt
     */
    public WitchControl(final Player player) {
        super(player);
    }

    /**
     * Plus zwei Karten. Jeder Mitspieler muss sich eine Fluchkarte nehmen.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();

        player.addCardToHand(player.getUppestCard());
        player.addCardToHand(player.getUppestCard());
        //TODO Jeder Mitspieler eine Fluchkarte
    }
}
