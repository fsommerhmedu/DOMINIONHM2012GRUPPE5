package dominion.control.cards;

import dominion.model.Player;

/**
 * Karte Geldverleiher.
 *
 * @author Florian
 */
public class MoneylenderControl extends ControlBase {

    /**
     * Wieviel Geld soll hinzugefügt werden?
     */
    private static final int MONEY_TO_ADD = 3;

    /**
     * @param player Spieler der die Karte ausspielt
     */
    public MoneylenderControl(final Player player) {
        super(player);
    }

    /**
     * Entsorge ein Kupfer aus deiner Hand. Wenn du das machst, plus drei Geld.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();

        for (int i = 0; i < player.getCardsOnHand().size(); i++) {
            if (player.getCardsOnHand().get(i).getName().
                    equalsIgnoreCase("Kupfer")) {
                player.addCardToPlayedCards(player.getCardsOnHand().get(i));
                player.deleteCardFromHand(i);
                player.addMoney(MONEY_TO_ADD);
                break;
            }
        }
    }
}
