package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller für Laboratorium.
 *
 * @author moe
 */
public class LaboratoryControl extends ControlBase {

    /**
     * @param player Spieler der die Karte auspielt
     */
    public LaboratoryControl(final Player player) {
        super(player);
    }

    @Override
    public final void play() {
        getPlayer().addActions(1);
        getPlayer().addCardToHand(getPlayer().getUppestCard());
        getPlayer().addCardToHand(getPlayer().getUppestCard());
    }

}
