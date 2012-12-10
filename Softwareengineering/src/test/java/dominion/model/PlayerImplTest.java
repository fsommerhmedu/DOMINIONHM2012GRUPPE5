package dominion.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dominion.model.cards.Card;
import dominion.model.cards.ActionCard.ActionCardAdventure;
import dominion.model.cards.MoneyCard.MoneyCard;
import dominion.model.cards.MoneyCard.MoneyCardCooper;
import dominion.model.cards.MoneyCard.MoneyCardGold;
import dominion.model.cards.MoneyCard.MoneyCardSilver;

public class PlayerImplTest {
    Player player;

    @Before
    public void setUp() {
        player = new PlayerImpl("Test");
    }

    @Test
    public void testConstructor() {
        // Karten auf der Hand
        assertEquals(5, player.getCardsOnHand().size());
        // Karten auf Nachziehstapel
        assertEquals(5, player.getCards().size());
    }

    @Test
    public void testGetName() {
        assertEquals("Test", player.getName());
    }

    @Test
    public void testMoneyCounter() {
        int tmp = 0;
        player.resetPlayer();
        for(Card c: player.getCardsOnHand())
        {
            if(c instanceof MoneyCard)
                tmp += 1;
        }
        player.addMoney(5);
        assertEquals(tmp + 5, player.getMoney());
    }

    @Test
    public void testActionCounter() {
        player.addActions(5);
        assertEquals(6, player.getActionCounter());
    }

    @Test
    public void testPurchases() {
        player.addPurchase(10);
        assertEquals(11, player.getPurchaseCounter());
    }

    @Test
    public void testCardOnHand() {
        player.addCardToHand(new MoneyCardSilver());
        assertEquals(6, player.getCardsOnHand().size());
    }

    @Test
    public void testThrowCardsOnHandAway() {
        player.throwCardsOnHandAway();
        assertEquals(0, player.getCardsOnHand().size());
    }

    @Test
    public void testGetUppestCard() {
        player.addCardToHand(player.getUppestCard());
        assertEquals(4, player.getCards().size());
        player.addCardToHand(player.getUppestCard());
        assertEquals(3, player.getCards().size());
        player.addCardToHand(player.getUppestCard());
        assertEquals(2, player.getCards().size());
        player.addCardToHand(player.getUppestCard());
        assertEquals(1, player.getCards().size());
        player.addCardToHand(player.getUppestCard());
        assertEquals(0, player.getCards().size());
        player.throwCardsOnHandAway();
        player.addCardToHand(player.getUppestCard());
        assertEquals(9, player.getCards().size());
        assertEquals(1, player.getCardsOnHand().size());
    }

    @Test
    public void testAddToCards() {
        player.addCardToCards(new MoneyCardCooper());
        assertEquals(6, player.getCards().size());
    }

    @Test
    public void testPlayedCardsToCards() {
        player.throwCardsOnHandAway();
        player.movePlayedCardsToCards();
        assertEquals(10, player.getCards().size());
    }

    @Test
    public void testEquals() {
        assertFalse(player.equals(null));
        assertFalse(player.equals("Hallo"));
        assertTrue(player.equals(new PlayerImpl("Test")));
        assertFalse(player.equals(new PlayerImpl("")));
        assertFalse(player.equals(new PlayerImpl(null)));
    }

    @Test
    public void testHashCode() {
        assertEquals(2603341, player.hashCode());
    }

    @Test
    public void testGetUppestUsedCard() {
        assertNull("Oberste Karte sollte Null sein", player.getUppestPlayedCard());
        player.addCardToPlayedCards(new MoneyCardCooper());
        assertEquals("Kupfer", player.getUppestPlayedCard().getName());
        player.addCardToPlayedCards(new MoneyCardGold());
        assertEquals("Gold", player.getUppestPlayedCard().getName());
    }
    
    @Test
    public void testAddUsedActionCard() {
        ActionCardAdventure c = new ActionCardAdventure();
        player.addUsedActionCard(c);
        assertTrue("ActionCard soll vorhanden sein",player.compareUsedActionCard(c));
    }
}
