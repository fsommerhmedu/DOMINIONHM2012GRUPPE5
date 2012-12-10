package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller für die Keller-Karte.
 *
 * @author moe
 */
public class CellarControl extends ControlBase {

    /**
     * @param player Spieler, welcher die Karte ausspielt.
     */
    public CellarControl(final Player player) {
        super(player);
    }

    /**
     * Karte wird von einem Spieler gespielt.
     *
     * Der Spieler erhält eine zusätzliche Aktion, legt alle seine Karten ab
     * und darf neue ziehen.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();
        player.addActions(1);
        int num = player.getCardsOnHand().size();
        player.throwCardsOnHandAway();
        for (int i = 0; i < num; i++) {
            player.addCardToHand(player.getUppestCard());
        }

    }
}
