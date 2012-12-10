/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominion.model;

import dominion.common.GamePhase;
import org.junit.Test;

import dominion.model.cards.Card;
import dominion.model.cards.MoneyCard.MoneyCardCooper;
import static org.junit.Assert.*;

/**
 * 
 * @author Moe
 */
public class GameImplTest
{
	@Test
	public void testAddAndGetPlayerCount()
	{
		GameImpl instance = new GameImpl();
		instance.addPlayer(new PlayerImpl("foo"));

		assertEquals(1, instance.getPlayerCount());
	}

	@Test(expected = IllegalStateException.class)
	public void testAddSamePlayerTwice()
	{
		GameImpl instance = new GameImpl();
		Player player = new PlayerImpl("foo");

		instance.addPlayer(player);
		instance.addPlayer(player);
	}

	@Test
	public void testAddAndGetPlayer()
	{
		Game game = new GameImpl();
		Player player = new PlayerImpl("foo");

		game.addPlayer(player);

		assertSame(player, game.getPlayer(0));
	}

	@Test
	public void testGetPlayerId()
	{
		Game game = new GameImpl();
		Player player = new PlayerImpl("foo");

		game.addPlayer(player);

		assertEquals(0, game.getIndex(player));
	}

	@Test
	public void testGetPlayerById()
	{
		Game game = new GameImpl();
		Player player = new PlayerImpl("foo");

		game.addPlayer(player);

		int id = game.getIndex(player);

		assertSame(player, game.getPlayer(id));
	}
	
	@Test
	public void testGetPlayers()
	{
		GameImpl game = new GameImpl();
		game.addPlayer(new PlayerImpl("Test"));
		assertEquals(1, game.getPlayers().size());
		assertEquals("Test", game.getPlayer(0).getName());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetPlayer()
	{
		Game game = new GameImpl();
		game.getPlayer(3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddPlayer()
	{
		Game game = new GameImpl();
		game.addPlayer(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetIndex()
	{
		Game game = new GameImpl();
		game.getIndex(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddCard()
	{
		Game game = new GameImpl();
		game.addCard(null);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAddCard2()
	{
		Card card = new MoneyCardCooper();
		Game game = new GameImpl();
		game.addCard(card);
		game.addCard(card);
	}

        @Test
        public void testGetSetPhase()
        {
            Game game = new GameImpl();
            game.setPhase(GamePhase.Buy);
            assertSame(GamePhase.Buy, game.getPhase());
        }
}
