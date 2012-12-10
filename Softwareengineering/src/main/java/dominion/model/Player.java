package dominion.model;

import java.util.List;
import dominion.model.cards.Card;

/**
 * Ein Spieler.
 *
 * @author Moe
 */
public interface Player {

    /**
     * Käufe zu Beginn.
     */
    int BEGIN_PURCHASE = 1;
    /**
     * Anzahl der Kupferkarten die ein Spieler zu Beginn bekommt.
     */
    int COOPER_TO_ADD = 7;
    /**
     * Anzahl der Startkarten eines Spielers.
     */
    int START_CARD_COUNT = 5;
    /**
     * Anzahl der Siegeskarten die ein Spieler zu Beginn bekommt.
     */
    int VICTORY_CARDS_TO_ADD = 3;
    /**
     * Anzahl der Aktionen zu Begin eines Spielzuges.
     */
    int BEGIN_ACTION = 1;

    /**
     * Gibt den Namen des Spieler zurück.
     *
     * @return Name des Spielers.
     */
    String getName();

    /**
     * Anzahl an Actionen die dem Spieler hinzugefügt werden sollen.
     *
     * @param actionPoints
     *            Actionen die hinzugefügt werden sollen
     */
    void addActions(int actionPoints);

    /**
     * Getter für die Aktionen die der Spieler noch zu Verfügung hat.
     *
     * @return Aktionen die der Spieler im aktuellen Zug hat
     */
    int getActionCounter();

    /**
     * Getter für die noch zur Verfügung stehenden Käufe.
     *
     * @return Anzahl an Käufe
     */
    int getPurchaseCounter();

    /**
     * Hinzufügen von Käufen.
     *
     * @param numberPurchase
     *            Anzahl an Käufen
     */
    void addPurchase(int numberPurchase);

    /**
     * Liste der Karten, die der Spieler gerade auf der Hand hat.
     *
     * @return Liste der Karten, die der Spieler gerade auf der Hand hat.
     */
    List<Card> getCardsOnHand();

    /**
     * Legt alle Karten, die der Spieler auf der Hand hat, oben auf den
     * Ablagestapel.
     */
    void throwCardsOnHandAway();

    /**
     * Gibt die nächste Karte vom Nachziehstapel zurück.
     *
     * Wenn der Nachziehstapel leer ist, dann wird der Ablagestapel wieder zum
     * Nachziehstapel.
     *
     * Die Karte vom Nachziehstapel wird gelöscht.
     *
     * @return Nächste Karte
     */
    Card getUppestCard();

    /**
     * Fügt die Karte an das Ende des Nachziehstapels.
     *
     * @param card
     *            Karte die hinzugefügt werden soll.
     */
    void addCardToCards(Card card);

    /**
     * Verschiebt alle Karten vom Ablagestapel zum Nachziehstapel.
     */
    void movePlayedCardsToCards();

    /**
     * Fügt eine Karte zur Hand hinzu.
     *
     * @param card
     *            Karte die zur Hand hinzugefügt werden soll
     */
    void addCardToHand(Card card);

    /**
     * Fügt die Karte zum Ablagestapel.
     *
     * @param card
     *            Karte zum Ablagestapel.
     */
    void addCardToPlayedCards(Card card);

    /**
     * Alle Karten vom Nachziehstapel zum Ablagestapel.
     */
    void throwCardsAway();

    /**
     * Legt die Karte mit dem Index i von der Hand auf den Ablagestapel.
     *
     * @param index
     *            Index der Karte.
     */
    void throwCardFromHandAway(int index);

    /**
     * Entfernt eine Karte von der Hand.
     *
     * @param index
     *            Index der Karte.
     */
    void deleteCardFromHand(int index);

    /**
     * Getter für den Nachziehstapel.
     *
     * @return List der Karten.
     */
    List<Card> getCards();

    /**
     * Getter für das aktuelle verfügbare Spielgeld.
     *
     * @return int aktuelles Geld.
     */
    int getMoney();

    /**
     * Addiert m Geld auf das verfügbare Spielgeld.
     *
     * @param m Geld das hinzugefügt wird.
     */
    void addMoney(int m);

    /**
     * Getter für die Gesamtanzahl aller Karten.
     *
     * @return Gesamtanzahl aller Karten
     */
    int getNumberAllCards();

    /**
     * Setzt die Werte des Spielers auf die Standards zurück.
     */
    void resetPlayer();

    /**
     * Gibt die oben liegende Karte vom Ablagestapel zurück.
     * @return oberte Karte oder null, wenn Stapel leer
     */
    Card getUppestPlayedCard();

    /**
     * Benutzte Actionkarte hinzufügen.
     * @param card Karte
     */
    void addUsedActionCard(Card card);

    /**
     * Vergleichen, ob ActionCard schon hinzugefügt wurde.
     * @param card Karte, die verglichen werden soll
     * @return true, wenn Karte schon vorhanden
     */
    boolean compareUsedActionCard(final Card card);
}
