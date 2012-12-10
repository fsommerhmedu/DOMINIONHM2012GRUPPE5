/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller für Jahrmarkt.
 *
 * @author moe
 */
public class FestivalControl extends ControlBase {

    /**
     * @param player Spieler der die Karte auspielt
     */
    public FestivalControl(final Player player) {
        super(player);
    }

    @Override
    public final void play() {
        final Player player = getPlayer();

        player.addActions(2);
        player.addPurchase(1);
        player.addMoney(2);
    }
}
