package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller für Militär.
 *
 * @author Florian
 */
public class MilitiaControl extends ControlBase {

    /**
     * @param player Spieler der die Karte spielt
     */
    public MilitiaControl(final Player player) {
        super(player);
    }

    /**
     * Plus zwei Geld. Jeder Mitspieler legt Karten ab,
     * bis er nur noch drei Karten auf der Hand hat.
     */
    @Override
    public final void play() {
        getPlayer().addMoney(2);
        //TODO Jeder Mitspieler darf nur max. 3 Karten haben!
    }
}
