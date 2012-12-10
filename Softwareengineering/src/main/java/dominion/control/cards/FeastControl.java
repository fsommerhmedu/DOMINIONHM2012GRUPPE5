package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller Festmahl.
 *
 * @author Florian
 */
public class FeastControl extends ControlBase {

    /**
     * Anzahl Geld die der Spieler bekommt.
     */
    public static final int MONEY_TO_ADD = 5;

    /**
     * Konstruktor für die Karte Festmahl.
     *
     * @param player Spieler der die Karte auspielt
     */
    public FeastControl(final Player player) {
        super(player);
    }

    /**
     * Entsorge die Karte<br>
     * Nimm eine Karte die bis zu 5 mehr kostet.
     *
     * Implementierung bis jetzt: Spieler bekommt 5 Geldstücke
     */
    @Override
    public final void play() {
        getPlayer().addMoney(MONEY_TO_ADD);
    }
}
