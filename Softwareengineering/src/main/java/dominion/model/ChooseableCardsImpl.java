package dominion.model;

import java.util.HashMap;

import dominion.model.cards.Card;
import dominion.model.cards.ActionCard.ActionCardAdventure;
import dominion.model.cards.ActionCard.ActionCardBureaucrat;
import dominion.model.cards.ActionCard.ActionCardCellar;
import dominion.model.cards.ActionCard.ActionCardChancellor;
import dominion.model.cards.ActionCard.ActionCardChapel;
import dominion.model.cards.ActionCard.ActionCardCouncilRoom;
import dominion.model.cards.ActionCard.ActionCardFeast;
import dominion.model.cards.ActionCard.ActionCardFestival;
import dominion.model.cards.ActionCard.ActionCardLaboratory;
import dominion.model.cards.ActionCard.ActionCardLibrary;
import dominion.model.cards.ActionCard.ActionCardMarket;
import dominion.model.cards.ActionCard.ActionCardMilitia;
import dominion.model.cards.ActionCard.ActionCardMine;
import dominion.model.cards.ActionCard.ActionCardMoat;
import dominion.model.cards.ActionCard.ActionCardMoneylender;
import dominion.model.cards.ActionCard.ActionCardRemodel;
import dominion.model.cards.ActionCard.ActionCardSmithy;
import dominion.model.cards.ActionCard.ActionCardSpy;
import dominion.model.cards.ActionCard.ActionCardThief;
import dominion.model.cards.ActionCard.ActionCardThroneRoom;
import dominion.model.cards.ActionCard.ActionCardVillage;
import dominion.model.cards.ActionCard.ActionCardWitch;
import dominion.model.cards.ActionCard.ActionCardWoodcutter;
import dominion.model.cards.ActionCard.ActionCardWorkshop;
import dominion.model.cards.VictoryCard.VictoryCardGarden;

// TODO: Alle diskreten Kartenimplementierungen in ein package verschieben
//und die HashMap availableCards per Schleife aus diesem Package füllen
/**
 * Implementierung der Auswählbare/Verfügbare Karten.
 *
 * @author Michi
 */
public class ChooseableCardsImpl implements ChooseableCards {

    /**
     * Verfügbare Karten, aus denen der Spieler ausw�hlen darf.
     */
    private final HashMap<String, Card> chooseableCards;

    /**
     * Erzeugt die Hashmap für die auszuwählenden Karten.
     */
    public ChooseableCardsImpl() {
        chooseableCards = new HashMap<String, Card>();
        addActionCards();
        addMoneyCards();
        addVictorycards();
    }

    /**
     * Gibt die Auswählbare/Verfügbare Karten als HasMap zurück.
     * @return auswählbare/verfügbare Karten.
     */
    @Override
    public final HashMap<String, Card> getCards() {
        return chooseableCards;
    }

    /**
     * Auswählbare Actions-Karten hinzufügen.
     */
    private void addActionCards() {
        Card card = new ActionCardAdventure();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardBureaucrat();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardCellar();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardChancellor();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardChapel();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardCouncilRoom();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardFeast();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardFestival();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardLaboratory();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardLibrary();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardMarket();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardMilitia();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardMine();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardMoat();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardMoneylender();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardRemodel();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardSmithy();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardSpy();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardThief();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardThroneRoom();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardVillage();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardWitch();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardWoodcutter();
        chooseableCards.put(card.getName(), card);

        card = new ActionCardWorkshop();
        chooseableCards.put(card.getName(), card);

    }

    /**
     * Auswählbare Geld-Karten hinzufügen.
     */
    private void addMoneyCards() {
    }

    /**
     * Auswählbare Punkte-Karten hinzufügen.
     */
    private void addVictorycards() {
        Card card = new VictoryCardGarden();
        chooseableCards.put(card.getName(), card);
    }
}
