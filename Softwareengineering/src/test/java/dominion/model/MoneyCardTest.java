package dominion.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dominion.model.cards.MoneyCard.MoneyCard;
import dominion.model.cards.MoneyCard.MoneyCardCooper;
import dominion.model.cards.MoneyCard.MoneyCardGold;
import dominion.model.cards.MoneyCard.MoneyCardSilver;


public class MoneyCardTest
{
	MoneyCard cooper;
	MoneyCard silver;
	MoneyCard gold;
	Player player;
	@Before
	public void setUp()
	{
		cooper = new MoneyCardCooper();
		silver = new MoneyCardSilver();
		gold = new MoneyCardGold();
		player = new PlayerImpl("Test");
	}
	
	@Test
	public void testGetValue()
	{
		assertEquals(1, cooper.getValue());
		assertEquals(2, silver.getValue());
		assertEquals(3, gold.getValue());
		
	}
	
	@Test
	public void testGetCost()
	{
		assertEquals(0, cooper.getCost());
		assertEquals(3, silver.getCost());
		assertEquals(6, gold.getCost());
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Kupfer", cooper.getName());
		assertEquals("Silber", silver.getName());
		assertEquals("Gold", gold.getName());
	}
	
	@Test
	public void testPlay()
	{
	    cooper.play(player);
	    silver.play(player);
	    gold.play(player);
	}
}
