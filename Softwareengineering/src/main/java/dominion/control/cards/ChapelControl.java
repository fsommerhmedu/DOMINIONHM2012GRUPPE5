package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller Kapelle.
 *
 * @author Florian
 */
public class ChapelControl extends ControlBase {

    /**
     * Anzahl der zu entsorgenden Karten.
     */
    private static final int CARDS_TO_THROW_AWAY = 4;

    /**
     * @param player Spieler der die Karte ausspielt
     */
    public ChapelControl(final Player player) {
        super(player);
    }

    /**
     * Entsorge bis zu vier Karten aus deiner Hand.
     * Bis jetzt noch die ersten 4 Karten.
     */
    @Override
    public final void play() {
        for (int i = 0; i < CARDS_TO_THROW_AWAY; i++) {
            getPlayer().throwCardFromHandAway(0);
        }
    }
}
