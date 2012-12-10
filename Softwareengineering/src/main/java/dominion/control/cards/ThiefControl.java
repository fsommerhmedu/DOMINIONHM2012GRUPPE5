package dominion.control.cards;

import dominion.model.Player;

/**
 * Karte Dieb.
 *
 * @author Florian
 */
public class ThiefControl extends ControlBase {

    /**
     * @param player Spieler der die Karte ausspielt
     */
    public ThiefControl(final Player player) {
        super(player);
    }

    /**
     * Jeder Mitspieler deckt die obersten beiden Karten
     * seines Nachziehstapels auf. Haben die Mitspieler eine
     * oder mehrere Geldkarten aufgedeckt, muss jeder eine davon
     * (nach deiner Wahl) entsorgen. Du kannst eine beliebige Zahl
     * der entsorgten Karten bei dir ablegen. Die übrigen aufgedeckten
     * Karten legen die Spieler bei sich ab.
     */
    @Override
    public final void play() {
        //TODO Siehe Javadoc
    }
}
