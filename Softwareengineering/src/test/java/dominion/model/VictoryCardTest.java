package dominion.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dominion.model.cards.VictoryCard.VictoryCard;
import dominion.model.cards.VictoryCard.VictoryCardCurse;
import dominion.model.cards.VictoryCard.VictoryCardDuchy;
import dominion.model.cards.VictoryCard.VictoryCardEstate;
import dominion.model.cards.VictoryCard.VictoryCardGarden;
import dominion.model.cards.VictoryCard.VictoryCardProvince;


public class VictoryCardTest
{
	VictoryCard curse;
	VictoryCard estate;
	VictoryCard garden;
	VictoryCard duchy;
	VictoryCard province;
	
	@Before
	public void setUp()
	{
		curse = new VictoryCardCurse();
		estate = new VictoryCardEstate();
		duchy = new VictoryCardDuchy();
		garden = new VictoryCardGarden();
		province = new VictoryCardProvince();
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Fluch", curse.getName());
		assertEquals("Anwesen", estate.getName());
		assertEquals("Herzogtum", duchy.getName());
		assertEquals("Gärten", garden.getName());
		assertEquals("Provinz", province.getName());
	}
	
	@Test
	public void testGetValue()
	{
            // Wir benötigen einen Spieler, um an den Wert der Karten zu kommen.
            Player player = new PlayerImpl("foo");

            assertEquals(-1, curse.getValue(player));
            assertEquals(1, estate.getValue(player));
            assertEquals(3, duchy.getValue(player));
            assertEquals(0, garden.getValue(player));
            assertEquals(6, province.getValue(player));
	}
	
	@Test
	public void testGetCost()
	{
		assertEquals(0, curse.getCost());
		assertEquals(2, estate.getCost());
		assertEquals(5, duchy.getCost());
		assertEquals(4, garden.getCost());
		assertEquals(8, province.getCost());
	}
	
	@Test
	public void testPlay()
	{
	    Player player = new PlayerImpl("foo");
	    curse.play(player);
	    estate.play(player);
	    duchy.play(player);
	    garden.play(player);
	    province.play(player);
	}
	
	
	
	
	
	
}
