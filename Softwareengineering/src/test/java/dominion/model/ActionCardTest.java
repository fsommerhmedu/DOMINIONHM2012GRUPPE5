package dominion.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dominion.model.cards.Card;
import dominion.model.cards.Trash;
import dominion.model.cards.ActionCard.ActionCard;
import dominion.model.cards.ActionCard.ActionCardAdventure;
import dominion.model.cards.ActionCard.ActionCardBureaucrat;
import dominion.model.cards.ActionCard.ActionCardChancellor;
import dominion.model.cards.ActionCard.ActionCardChapel;
import dominion.model.cards.ActionCard.ActionCardCouncilRoom;
import dominion.model.cards.ActionCard.ActionCardFeast;
import dominion.model.cards.ActionCard.ActionCardFestival;
import dominion.model.cards.ActionCard.ActionCardLaboratory;
import dominion.model.cards.ActionCard.ActionCardLibrary;
import dominion.model.cards.ActionCard.ActionCardMoneylender;
import dominion.model.cards.ActionCard.ActionCardSpy;
import dominion.model.cards.ActionCard.ActionCardThief;
import dominion.model.cards.ActionCard.ActionCardThroneRoom;
import dominion.model.cards.ActionCard.ActionCardVillage;
import dominion.model.cards.ActionCard.ActionCardWitch;
import dominion.model.cards.MoneyCard.MoneyCard;
import dominion.model.cards.MoneyCard.MoneyCardCooper;
import dominion.model.cards.MoneyCard.MoneyCardGold;
import dominion.model.cards.MoneyCard.MoneyCardSilver;

public class ActionCardTest
{
	private ActionCard adventures;
	private ActionCard bureaucrat;
	private ActionCard chancellor;
	private ActionCard chapel;
	private ActionCard councilRoom;
	private ActionCard feast;
	private ActionCard festival;
	private ActionCard laboratory;
	private ActionCard library;
	private ActionCard moneylender;
	private ActionCard spy;
	private ActionCard thief;
	private ActionCard throneRoom;
	private ActionCard witch;
	private Trash trash;

	Player player;

	@Before
	public void setUp()
	{
		adventures = new ActionCardAdventure();
		bureaucrat = new ActionCardBureaucrat();
		chancellor = new ActionCardChancellor();
		chapel = new ActionCardChapel();
		councilRoom = new ActionCardCouncilRoom();
		feast = new ActionCardFeast();
		festival = new ActionCardFestival();
		laboratory = new ActionCardLaboratory();
		library = new ActionCardLibrary();
		moneylender = new ActionCardMoneylender();
		spy = new ActionCardSpy();
		thief = new ActionCardThief();
		throneRoom = new ActionCardThroneRoom();
		witch = new ActionCardWitch();

		player = new PlayerImpl("Test");
		player.addCardToCards(new ActionCardChapel());
		player.addCardToCards(new MoneyCardCooper());
		player.addCardToCards(new MoneyCardCooper());
		player.addCardToCards(new MoneyCardCooper());
		player.addCardToCards(new MoneyCardCooper());
	}

	@Test
	public void testAdventuresPlay()
	{
		adventures.play(player);
		assertEquals(7, player.getCardsOnHand().size());
	}
	
	@Test
	public void testAdventuresPlay2()
	{
		player.throwCardsOnHandAway();
		player.addCardToCards(new MoneyCardSilver());
		player.addCardToCards(new MoneyCardSilver());
		player.addCardToCards(new MoneyCardSilver());
		player.addCardToCards(new MoneyCardSilver());
		adventures.play(player);
		assertEquals(2, player.getCardsOnHand().size());
	}
	
	@Test
	public void testAdventuresPlay3()
	{
		player.throwCardsOnHandAway();
		player.addCardToCards(new MoneyCardGold());
		player.addCardToCards(new MoneyCardGold());
		player.addCardToCards(new MoneyCardGold());
		player.addCardToCards(new MoneyCardGold());
		adventures.play(player);
		assertEquals(2, player.getCardsOnHand().size());
	}

	@Test
	public void testBureaucratPlay()
	{
		player.throwCardsAway();
		bureaucrat.play(player);
		assertEquals("Silber", player.getUppestCard().getName());
		// TODO Jeder Mitspieler muss eine Punktekarte aus der Hand aufdecken
		// TODO und auf den Nachziehstapel legen
		// TODO Bei keiner Punktekarte muss er die ganze Hand zeigen
	}

	@Test
	public void testChancellorPlay()
	{
	    int tmp = 0;
        player.resetPlayer();
        for(Card c: player.getCardsOnHand())
        {
            if(c instanceof MoneyCard)
                tmp += 1;
        }
		chancellor.play(player);
		assertEquals(tmp + 2, player.getMoney());
		assertEquals(0, player.getCards().size());
	}

	@Test
	public void testChapelPlay()
	{
		for (int i = 0; i < 5; i++)
		{
			player.addCardToHand(new MoneyCardCooper());
		}
		chapel.play(player);
		assertEquals(6, player.getCardsOnHand().size());
	}

	@Test
	public void testCouncilRoomPlay()
	{
		councilRoom.play(player);
		assertEquals(9, player.getCardsOnHand().size());
		assertEquals(2, player.getPurchaseCounter());
		// TODO Jeder Spieler zieht sofort eine Karte nach
	}

	@Test
	public void testFeastPlay()
	{
	    int tmp = 0;
        player.resetPlayer();
        for(Card c: player.getCardsOnHand())
        {
            if(c instanceof MoneyCard)
                tmp += 1;
        }
		feast.play(player);
		assertEquals(tmp + 5, player.getMoney());
	}

	@Test
	public void testFestivalPlay()
	{
	    int tmp = 0;
        player.resetPlayer();
        for(Card c: player.getCardsOnHand())
        {
            if(c instanceof MoneyCard)
                tmp += 1;
        }
		festival.play(player);
		assertEquals(3, player.getActionCounter());
		assertEquals(2, player.getPurchaseCounter());
		assertEquals(tmp + 2, player.getMoney());
	}

	@Test
	public void testLaboratoryPlay()
	{
		laboratory.play(player);
		assertEquals(7, player.getCardsOnHand().size());
		assertEquals(2, player.getActionCounter());
	}

	@Test
	public void testLibraryPlay()
	{
		library.play(player);
		assertEquals(7, player.getCardsOnHand().size());

	}

	@Test
	public void testMoneylenderPlay()
	{
	    int tmp = 0;
        player.resetPlayer();
        for(Card c: player.getCardsOnHand())
        {
            if(c instanceof MoneyCard)
                tmp += 1;
        }
		moneylender.play(player);
		assertEquals("Anzahl Karten auf der Hand stimmt nicht", 4, player
				.getCardsOnHand().size());
		assertEquals("Anzahl Geldstücke stimmt nicht", tmp + 3, player
				.getMoney());
	}

	@Test
	public void testSpyPlay()
	{
		spy.play(player);
		assertEquals(6, player.getCardsOnHand().size());
		assertEquals(2, player.getActionCounter());
		// TODO Jeder Spieler deckt die erste Karte des Nachziehstapels auf
		// TODO Entweder Ablegen oder wieder zudecken
	}

	@Test
	public void testThiefPlay()
	{
	    thief.play(player);
	// TODO ersten zwei Karten des Nachziehstapel aufdecken
	// TODO Wenn mind. eine Geldkarte diese Entsorgen
	// TODO Bel. Entsorgten Karten bei Player ablegen
	}

	@Test
	public void testThroneRoomPlay()
	{
	    player.throwCardsOnHandAway();
	    player.addCardToHand(new ActionCardVillage());
	    throneRoom.play(player);
	    assertEquals(3, player.getCardsOnHand().size());
	    assertEquals(5, player.getActionCounter());
	// TODO Aktionskarte auswählen und doppelt spielen
	}

	@Test
	public void testWitchPlay()
	{
		witch.play(player);
		assertEquals(7, player.getCardsOnHand().size());
		// TODO Jeder Mitspieler eine Fluchkarte
	}

	@Test
	public void testAdventuresData()
	{
		assertEquals(6, adventures.getCost());
		assertEquals("Abenteuer", adventures.getName());
	}

	@Test
	public void testBureaucratData()
	{
		assertEquals(4, bureaucrat.getCost());
		assertEquals("Bürokrat", bureaucrat.getName());
	}

	@Test
	public void testChancellorData()
	{
		assertEquals(3, chancellor.getCost());
		assertEquals("Kanzler", chancellor.getName());
	}

	@Test
	public void testChapelData()
	{
		assertEquals(2, chapel.getCost());
		assertEquals("Kapelle", chapel.getName());
	}

	@Test
	public void testCouncilRoomData()
	{
		assertEquals(5, councilRoom.getCost());
		assertEquals("Ratsversammlung", councilRoom.getName());
	}

	@Test
	public void testFeastData()
	{
		assertEquals(4, feast.getCost());
		assertEquals("Festmahl", feast.getName());
	}

	@Test
	public void testFestivalData()
	{
		assertEquals(5, festival.getCost());
		assertEquals("Jahrmarkt", festival.getName());
	}

	@Test
	public void testLaboratoryData()
	{
		assertEquals(5, laboratory.getCost());
		assertEquals("Laboratorium", laboratory.getName());
	}

	@Test
	public void testLibraryData()
	{
		assertEquals(5, library.getCost());
		assertEquals("Bibliothek", library.getName());
	}

	@Test
	public void testMoneylenderData()
	{
		assertEquals(4, moneylender.getCost());
		assertEquals("Geldverleiher", moneylender.getName());
	}

	@Test
	public void testSpyData()
	{
		assertEquals(4, spy.getCost());
		assertEquals("Spion", spy.getName());
	}

	@Test
	public void testThiefData()
	{
		assertEquals(4, thief.getCost());
		assertEquals("Dieb", thief.getName());
	}

	@Test
	public void testThroneRoomData()
	{
		assertEquals(4, throneRoom.getCost());
		assertEquals("Thronsaal", throneRoom.getName());
	}

	@Test
	public void testWitchData()
	{
		assertEquals(5, witch.getCost());
		assertEquals("Hexe", witch.getName());
	}
	
	@Test
	public void testTrash()
	{
		trash = new Trash();
		assertEquals("Müll", trash.getName());
		assertEquals(0, trash.getCost());
		trash.play(player);
		
	}
}
