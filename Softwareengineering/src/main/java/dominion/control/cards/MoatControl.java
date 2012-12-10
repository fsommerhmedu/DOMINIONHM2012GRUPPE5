package dominion.control.cards;

import dominion.model.Player;

/**
 * Kontroller für Burggraben.
 *
 * @author Florian
 */
public class MoatControl extends ControlBase {

    /**
     * @param player Spieler der die Karte ausspielt
     */
    public MoatControl(final Player player) {
        super(player);
    }

    /**
     * Plus zwei Karten.
     * Wenn ein Mitspieler eine Angriffskarte ausspielt,
     * kannst du diese Karte aus deiner Hand vorzeigen.
     * Der Angriff hat dann keine Wirkung auf dich.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();

        player.addCardToHand(player.getUppestCard());
        player.addCardToHand(player.getUppestCard());
        //TODO Angriff hat keine Wirkung
    }
}
