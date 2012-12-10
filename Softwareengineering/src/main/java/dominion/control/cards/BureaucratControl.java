package dominion.control.cards;

import dominion.model.Player;
import dominion.model.cards.MoneyCard.MoneyCardSilver;

/**
 * Controller Bürokrat.
 *
 * @author Florian
 */
public class BureaucratControl extends ControlBase {

    /**
     * Konstruktor für einen Bürokrat.
     *
     * @param player Spieler der die Karte auspielt
     */
    public BureaucratControl(final Player player) {
        super(player);
    }

    /**
     * Nimm dir ein Silber und lege es verdeckt auf deinen Nachziehstapel.
     * Jeder Mitspieler muss eine Punktekarte aus seiner Hand aufdecken
     * und sie verdeckt auf seinen Nachziehstapel legen. Hat ein Spieler
     * keine Punktekarte auf der Hand, muss er seine Kartenhand aufzeigen.
     */
    @Override
    public final void play() {
        getPlayer().addCardToCards(new MoneyCardSilver());
        //TODO Andere Spieler noch implementieren (siehe oben)
    }
}
