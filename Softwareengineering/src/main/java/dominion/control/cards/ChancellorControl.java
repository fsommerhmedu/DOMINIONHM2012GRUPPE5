package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller für eine Kanzlerkarte.
 *
 * @author moe
 */
public class ChancellorControl extends ControlBase {

    /**
     * Konstruktor für Kanzlerkarte.
     *
     * @param player Spieler der die Karte auspielt
     */
    public ChancellorControl(final Player player) {
        super(player);
    }

    @Override
    public final void play() {
        final Player player = getPlayer();
        player.addMoney(2);
        player.throwCardsAway();
    }
}
