package dominion.control.cards;

import dominion.model.Player;

/**
 * Karte Umbau.
 *
 * @author Florian
 */
public class RemodelControl extends ControlBase {

    /**
     * @param player Spieler der die Karte ausspielt
     */
    public RemodelControl(final Player player) {
        super(player);
    }

    /**
     * Entsorge eine Karte aus deiner Hand.
     * Nimm dir eine Karte, die bis zu zwei mehr
     * kostet als die entsorgte Karte.
     *
     * Spieler wird die Karte mit Index 0 entfernt.
     * Spieler bekommt bis jetzt 2 Geld mehr.
     */
    public final void play() {
        final Player player = getPlayer();
        player.addMoney(player.getCardsOnHand().get(0).getCost() + 2);
        player.deleteCardFromHand(0);

    }
}
