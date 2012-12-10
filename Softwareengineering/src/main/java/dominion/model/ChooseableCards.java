package dominion.model;

import java.util.HashMap;
import dominion.model.cards.Card;

/**
 * Auswählbare/Verfügbare Karten.
 *
 * @author Michi
 */
public interface ChooseableCards {

    /**
     * Gibt die Auswählbare/Verfügbare Karten als HasMap zurück.
     *
     * @return Hashmap<String, Card>
     */
    HashMap<String, Card> getCards();
}
