package dominion.control.cards;

import dominion.model.Player;

/**
 * Controller für Mine.
 *
 * @author Florian
 */
public class MineControl extends ControlBase {

    /**
     * Hinzuzufügendes Geld.
     */
    private static final int MONEY_TO_ADD = 3;

    /**
     * @param player Spieler der die Mine auspielt
     */
    public MineControl(final Player player) {
        super(player);
    }

    /**
     * Entsorge eine Geldkarte aus deiner Hand. Nimm dir eine Geldkarte,
     * die bis zu drei mehr kostet.
     * Nimm diese Geldkarte sofort auf die Hand.
     *
     * Bis jetzt noch: Erste Geldkarte wird entsorgt.
     * Spieler bekommt 3 Geld addiert.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();
        for (int i = 0; i < player.getCardsOnHand().size(); i++) {
            if (player.getCardsOnHand().get(i).getClass().getSimpleName().
                    substring(0, 5).equalsIgnoreCase("Money")) {
                player.addCardToPlayedCards(player.getCardsOnHand().get(i));
                player.deleteCardFromHand(i);
                player.addMoney(MONEY_TO_ADD);
                break;
            }
        }
    }
}
