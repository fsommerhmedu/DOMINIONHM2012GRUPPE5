package dominion.control.cards;

import dominion.model.Player;
import dominion.model.cards.Card;

/**
 * Controller für eine Abenteuerkarte.
 *
 * @author Florian
 */
public class AdventureControl extends ControlBase {

    /**
     * Konstruktor für eine Abenteuer Karten.
     *
     * @param player Spieler der die Karte spielt
     */
    public AdventureControl(final Player player) {
        super(player);
    }

    /**
     * Decke solange Karten vom Nachziehstapel auf,
     * bis 2 Geldkarten offen liegen.
     * Nimm die Geldkarten auf die Hand, lege die übrigen
     * aufgedeckten Karten ab.
     */
    @Override
    public final void play() {
        Card card;
        int counter = 0;
        while (counter < 2) {
            card = getPlayer().getUppestCard();
            if (card.getName().equalsIgnoreCase("Kupfer")
                    || card.getName().equalsIgnoreCase("Silber")
                    || card.getName().equalsIgnoreCase("Gold")) {
                getPlayer().addCardToHand(card);
                counter++;
            } else {
                getPlayer().addCardToPlayedCards(card);
            }
        }
    }
}
