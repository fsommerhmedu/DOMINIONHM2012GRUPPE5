package dominion.control.cards;

import dominion.model.Player;
import dominion.model.cards.ActionCard.ActionCard;

/**
 * Karte Thronsaal.
 *
 * @author Florian
 */
public class ThroneRoomControl extends ControlBase {

    /**
     * @param player Spieler der die Karte ausspielt
     */
    public ThroneRoomControl(final Player player) {
        super(player);
    }

    /**
     * Wähle eine Aktionskarte aus deiner Hand.
     * Spiele diese Aktionskarte zweimal aus.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();

        for (int i = 0; i < player.getCardsOnHand().size(); i++) {
            if (player.getCardsOnHand().get(i).getClass().getSimpleName().
                    substring(0, 6).equalsIgnoreCase("Action")) {
                ActionCard card = (ActionCard) player.getCardsOnHand().get(i);
                card.play(player);
                card.play(player);
                break;
            }
        }

    }
}
