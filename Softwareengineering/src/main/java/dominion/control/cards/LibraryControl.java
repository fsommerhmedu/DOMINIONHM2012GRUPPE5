package dominion.control.cards;

import dominion.model.Player;
import dominion.model.cards.Card;

/**
 * Controller Bibliothek.
 *
 * @author Florian
 */
public class LibraryControl extends ControlBase {
    /**
     * Karten die der Spieler auf der Hand haben darf.
     */
    public static final int MAX_CARDS_ON_HAND = 7;

    /**
     * @param player Spieler der die Karte auspielt
     */
    public LibraryControl(final Player player) {
        super(player);
    }

    @Override
    public final void play() {
        Card card;
        final Player player = getPlayer();
        while (player.getCardsOnHand().size() < MAX_CARDS_ON_HAND) {
            card = player.getUppestCard();

            if (card.getClass().getSimpleName().substring(0, 6).
                    equalsIgnoreCase("Action")) {
                player.addCardToPlayedCards(card);
            } else {
                player.addCardToHand(card);
            }
        }
    }
}
