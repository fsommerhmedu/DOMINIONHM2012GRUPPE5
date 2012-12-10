package dominion.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dominion.model.cards.Card;
import dominion.model.cards.ActionCard.ActionCard;
import dominion.model.cards.ActionCard.ActionCardCellar;
import dominion.model.cards.ActionCard.ActionCardMarket;
import dominion.model.cards.ActionCard.ActionCardMilitia;
import dominion.model.cards.ActionCard.ActionCardMine;
import dominion.model.cards.ActionCard.ActionCardMoat;
import dominion.model.cards.ActionCard.ActionCardRemodel;
import dominion.model.cards.ActionCard.ActionCardSmithy;
import dominion.model.cards.ActionCard.ActionCardVillage;
import dominion.model.cards.ActionCard.ActionCardWoodcutter;
import dominion.model.cards.ActionCard.ActionCardWorkshop;
import dominion.model.cards.MoneyCard.MoneyCard;
import dominion.model.cards.MoneyCard.MoneyCardCooper;

public class ActionCardBasicTest {
    private ActionCard cellar;
    private ActionCard moat;
    private ActionCard village;
    private ActionCard workshop;
    private ActionCard woodcutter;
    private ActionCard smithy;
    private ActionCard remodel;
    private ActionCard militia;
    private ActionCard market;
    private ActionCard mine;

    Player player;

    @Before
    public void setUp() {
        cellar = new ActionCardCellar();
        moat = new ActionCardMoat();
        village = new ActionCardVillage();
        workshop = new ActionCardWorkshop();
        woodcutter = new ActionCardWoodcutter();
        smithy = new ActionCardSmithy();
        remodel = new ActionCardRemodel();
        militia = new ActionCardMilitia();
        market = new ActionCardMarket();
        mine = new ActionCardMine();
        player = new PlayerImpl("Test");
        player.addCardToCards(new MoneyCardCooper());
        player.addCardToCards(new MoneyCardCooper());
        player.addCardToCards(new MoneyCardCooper());
    }

    @Test
    public void testGetCost() {
        assertEquals(2, cellar.getCost());
        assertEquals(2, moat.getCost());
        assertEquals(3, village.getCost());
        assertEquals(3, workshop.getCost());
        assertEquals(3, woodcutter.getCost());
        assertEquals(4, smithy.getCost());
        assertEquals(4, remodel.getCost());
        assertEquals(4, militia.getCost());
        assertEquals(5, market.getCost());
        assertEquals(5, mine.getCost());
    }

    @Test
    public void testGetName() {
        assertEquals("Keller", cellar.getName());
        assertEquals("Burggraben", moat.getName());
        assertEquals("Dorf", village.getName());
        assertEquals("Werkstatt", workshop.getName());
        assertEquals("Holzfäller", woodcutter.getName());
        assertEquals("Schmiede", smithy.getName());
        assertEquals("Umbau", remodel.getName());
        assertEquals("Miliz", militia.getName());
        assertEquals("Markt", market.getName());
        assertEquals("Mine", mine.getName());
    }

    @Test
    public void TestPlayCellar() {
        player.addCardToHand(new MoneyCardCooper());
        int num = player.getCardsOnHand().size();
        cellar.play(player);
        assertEquals(2, player.getActionCounter());
        assertEquals(num, player.getCardsOnHand().size());
    }

    @Test
    public void TestPlayMoat() {
        moat.play(player);
        assertEquals(7, player.getCardsOnHand().size());
    }

    @Test
    public void TestPlayVillage() {
        village.play(player);
        assertEquals(6, player.getCardsOnHand().size());
        assertEquals(3, player.getActionCounter());
    }

    @Test
    public void TestPlayWoodcutter() {
        int tmp = 0;
        player.resetPlayer();
        for (Card c : player.getCardsOnHand()) {
            if (c instanceof MoneyCard)
                tmp += 1;
        }
        woodcutter.play(player);
        assertEquals(2, player.getPurchaseCounter());
        assertEquals(tmp + 2, player.getMoney());
    }

    @Test
    public void TestPlaySmithy() {
        smithy.play(player);
        assertEquals(8, player.getCardsOnHand().size());
    }

    @Test
    public void TestPlayMilitia() {
        int tmp = 0;
        player.resetPlayer();
        for (Card c : player.getCardsOnHand()) {
            if (c instanceof MoneyCard)
                tmp += 1;
        }
        militia.play(player);
        assertEquals(tmp + 2, player.getMoney());
    }

    @Test
    public void TestPlayMarket() {
        int tmp = 0;
        player.resetPlayer();
        for(Card c: player.getCardsOnHand())
        {
            if(c instanceof MoneyCard)
                tmp += 1;
        }
        market.play(player);
        assertEquals(6, player.getCardsOnHand().size());
        assertEquals(2, player.getActionCounter());
        assertEquals(2, player.getPurchaseCounter());
        assertEquals(tmp + 1, player.getMoney());
    }

    @Test
    public void TestPlayMine() {
        int tmp = 0;
        player.resetPlayer();
        player.addCardToHand(new MoneyCardCooper());
        int num = player.getCardsOnHand().size();
        mine.play(player);
        for(Card c: player.getCardsOnHand())
        {
            if(c instanceof MoneyCard)
                tmp += 1;
        }
        assertEquals("Karten auf der Hand",num - 1, player.getCardsOnHand().size());
        assertEquals("Verfügbares Geld", tmp + 3, player.getMoney());
    }

    @Test
    public void TestPlayRemodel() {
        player.addCardToHand(new MoneyCardCooper());
        player.addCardToHand(new MoneyCardCooper());
        player.addCardToHand(new MoneyCardCooper());
        int num = player.getCardsOnHand().size();
        remodel.play(player);
        assertEquals(num - 1, player.getCardsOnHand().size());
        assertTrue("Geld muss größer 0 sein", player.getMoney() > 0);
    }

    @Test
    public void TestPlayWorkshop() {
        int tmp = 0;
        player.resetPlayer();
        for(Card c: player.getCardsOnHand())
        {
            if(c instanceof MoneyCard)
                tmp += 1;
        }
        workshop.play(player);
        assertEquals(tmp + 4, player.getMoney());
    }

    @Test
    public void getResourceNameSmall() {
        assertEquals("/dominion/view/Images/small/ActionCardWorkshop.jpg",
                workshop.getResourceNameSmall());
        assertEquals("/dominion/view/Images/small/ActionCardCellar.jpg", cellar
                .getResourceNameSmall());
    }

    @Test
    public void getResourceNameBig() {
        assertEquals("/dominion/view/Images/big/ActionCardWorkshop.jpg",
                workshop.getResourceNameBig());
        assertEquals("/dominion/view/Images/big/ActionCardCellar.jpg", cellar
                .getResourceNameBig());
    }
}
