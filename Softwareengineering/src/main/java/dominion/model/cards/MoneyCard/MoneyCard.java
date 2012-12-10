package dominion.model.cards.MoneyCard;

import dominion.model.cards.Card;

/**
 * Eine Geldkarte.
 *
 * @author Moe
 */
public interface MoneyCard extends Card {

    @Override
    String getName();

    /**
     * Getter für den Wert der Karte.
     *
     * @return Wert der Karte.
     */
    int getValue();
}
