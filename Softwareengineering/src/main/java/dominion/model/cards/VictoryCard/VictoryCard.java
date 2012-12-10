package dominion.model.cards.VictoryCard;

import dominion.model.Player;
import dominion.model.cards.Card;

/**
 * Interface für Punktekarten.
 *
 * @author Florian
 */
public interface VictoryCard extends Card {

    /**
     * Wert der Karte.
     *
     * @param player Spieler, welcher die Karte besitzt.
     * @return Wert der Karte.
     */
    int getValue(Player player);
}
