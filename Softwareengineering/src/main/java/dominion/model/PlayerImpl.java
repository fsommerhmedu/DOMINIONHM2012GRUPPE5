package dominion.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dominion.model.cards.Card;
import dominion.model.cards.CardBase;
import dominion.model.cards.MoneyCard.MoneyCard;
import dominion.model.cards.MoneyCard.MoneyCardCooper;
import dominion.model.cards.VictoryCard.VictoryCardEstate;

/**
 * Implementierung eines Spielers.
 *
 * @author Moe
 */
public class PlayerImpl implements Player {

    /**
     * Name des Spielers.
     */
    private final String name;
    /**
     * Spielgeld, welches der Spieler zur Verfügung hat.
     */
    private int money = 0;
    /**
     * Counter für die Aktionen die der Spieler noch hat.
     */
    private int actionCounter = BEGIN_ACTION; // Standardmäßig 1 zu Beginn.
    /**
     * Counter für die Käufe, welche der Spieler noch zur Verfügung hat.
     */
    private int purchaseCounter = BEGIN_PURCHASE; // Standardmäßig 1 zu Beginn.
    /**
     * Karten auf der Hand.
     */
    private final List<Card> cardsOnHand;
    /**
     * Karten auf dem Nachziehstapel.
     */
    private final List<Card> cards;
    /**
     * Gespielte Karten.
     */
    private final List<Card> cardsPlayed;
    /**
     * Liste aller ActionCard-Ids die schon ausgewählt wurden.
     */
    private List<Card> actionIds = new ArrayList<Card>();

    /**
     * Konstruktoer des Spielers.
     *
     * Setzt den Namen und erzeugt alle notwendigen Listen. Die gezogenen Karten
     * werden anschließend nochmal gemischt.
     *
     * @param newName
     *            Name des Spielers.
     */
    public PlayerImpl(final String newName) {
        this.name = newName;
        cardsOnHand = new ArrayList<Card>();
        cards = new ArrayList<Card>(); // TODO: Gescheite Datenstruktur
        cardsPlayed = new ArrayList<Card>();

        // Spieler mit Startkarten initialisieren
        for (int i = 0; i < COOPER_TO_ADD; i++) {
            cards.add(new MoneyCardCooper());
        }
        for (int i = 0; i < VICTORY_CARDS_TO_ADD; i++) {
            cards.add(new VictoryCardEstate());
        }
        // Karten mischen
        Collections.shuffle(cards);

        // Starthand ausgeben
        for (int i = 0; i < START_CARD_COUNT; i++) {
            cardsOnHand.add(this.getUppestCard());
        }

        // Spielgeld berechnen.
        calcMoney();
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int getMoney() {
        return money;
    }

    // TODO: wird die Methode wirklich gebraucht??
    @Override
    public final List<Card> getCards() {
        return cards;
    }

    @Override
    public final int getNumberAllCards() {
        return cardsOnHand.size() + cardsPlayed.size() + cards.size();
    }

    @Override
    public final List<Card> getCardsOnHand() {
        // Karten nach Kartenart sortieren
        Collections.sort(cardsOnHand, new CardBase.CardTypeComparator());
        return Collections.unmodifiableList(cardsOnHand);
    }

    @Override
    public final Card getUppestCard() {
        // Falls keine Karten mehr auf dem Nachziehstapel.
        if (cards.size() == 0) {
            // Karten von Ablagestapel nach Nachziehstapel.
            movePlayedCardsToCards();
            // Nachziehstapel mischen.
            Collections.shuffle(cards);
        }
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    @Override
    public final Card getUppestPlayedCard() {
        if (cardsPlayed.size() > 0) {
            return cardsPlayed.get(cardsPlayed.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public final int getPurchaseCounter() {
        return purchaseCounter;
    }

    @Override
    public final int getActionCounter() {
        return actionCounter;
    }

    @Override
    public final void addMoney(final int m) {
        money += m;
    }

    @Override
    public final void addPurchase(final int numberPurchase) {
        purchaseCounter += numberPurchase;

    }

    @Override
    public final void addActions(final int actionPoints) {
        actionCounter += actionPoints;
    }

    @Override
    public final void addCardToCards(final Card card) {
        cards.add(card);
    }

    @Override
    public final void addCardToHand(final Card card) {
        cardsOnHand.add(card);
    }

    @Override
    public final void addCardToPlayedCards(final Card card) {
        cardsPlayed.add(card);
    }

    @Override
    public final void addUsedActionCard(final Card card) {
        actionIds.add(card);
    }

    /**
     * Berechnet wieviel Spielgeld mit der aktuellen Hand verfügbar ist.
     */
    private void calcMoney() {

        int help = 0;
        MoneyCard m;
        // Durchlaufe alle Karten auf der Hand
        for (Card c : cardsOnHand) {
            if (c instanceof MoneyCard) {
                m = (MoneyCard) c;
                help += m.getValue();
            }
        }
        money = help;
    }

    @Override
    public final void movePlayedCardsToCards() {
        for (Card c : cardsPlayed) {
            addCardToCards(c);
        }
        cardsPlayed.clear();
    }

    @Override
    public final void throwCardsAway() {
        for (Card c : cards) {
            cardsPlayed.add(c);
        }
        cards.clear();
    }

    @Override
    public final void throwCardsOnHandAway() {
        // Alle Karten von der Hand auf den Gespielt-Stapel
        for (Card card : cardsOnHand) {
            cardsPlayed.add(card);
        }
        // Karten auf der Hand wegwerfen
        cardsOnHand.clear();
    }

    @Override
    public final void throwCardFromHandAway(final int i) {
        cardsPlayed.add(cardsOnHand.get(i));
        cardsOnHand.remove(i);
    }

    @Override
    public final void deleteCardFromHand(final int i) {
        cardsOnHand.remove(i);
    }

    @Override
    public final void resetPlayer() {
        // Handkarten wegwerfen
        throwCardsOnHandAway();
        // Kartenhand ausgeben
        for (int i = 0; i < START_CARD_COUNT; i++) {
            cardsOnHand.add(getUppestCard());
        }
        // Spielgeld auf der Hand neu berechnen
        calcMoney();
        // Gespielte Aktionen zurücksetzen
        actionIds.clear();
        // Käufe-Counter zurücksetzen
        purchaseCounter = BEGIN_PURCHASE;
        // Aktionen-Counter zurücksetzen
        actionCounter = BEGIN_ACTION;
    }

    @Override
    public final boolean compareUsedActionCard(final Card card) {
        return actionIds.contains(card);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerImpl other = (PlayerImpl) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
}
