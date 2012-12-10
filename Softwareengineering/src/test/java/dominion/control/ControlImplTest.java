/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package dominion.control;

import dominion.common.GamePhase;
import dominion.common.GameState;
import dominion.model.GameImpl;
import dominion.model.Player;
import dominion.model.PlayerImpl;
import dominion.model.cards.ActionCard.ActionCardAdventure;
import dominion.model.cards.ActionCard.ActionCardCellar;
import dominion.model.cards.ActionCard.ActionCardFeast;
import dominion.model.cards.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author spea
 */
public class ControlImplTest {

    private Control control;

    private GameImpl model;

    public ControlImplTest() {}

    @BeforeClass
    public static void setUpClass() throws Exception {}

    @AfterClass
    public static void tearDownClass() throws Exception {}

    @Before
    public void setUp() {
        model = new GameImpl();
        control = new ControlImpl(model);
    }

    @After
    public void tearDown() {}

    /**
     * Test of createPlayer method, of class ControlImpl.
     */
    @Test
    public void testCreatePlayer() {
        String name = "Hans";
        Player expResult = new PlayerImpl("Hans");
        Player result = control.createPlayer(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of startGame method, of class ControlImpl.
     */
    @Test
    public void testStartGame() {
        control.startGame();
        assertEquals(model.getGameState(), GameState.SetPlayers);
    }

    /**
     * Test of startNewGame method, of class ControlImpl.
     */
    @Test
    public void testStartNewGame() {
        //spieler und karten hinzufügen
        model.addPlayer(new PlayerImpl("Testing"));
        model.addCard(new ActionCardAdventure());
        model.setGameState(GameState.GameScreen);

        control.startNewGame();

        assertEquals(0, model.getPlayers().size());
        assertEquals(0, model.getCards().size());
        assertEquals(GameState.SetPlayers, model.getGameState());
    }

    /**
     * Test of createPlayers method, of class ControlImpl.
     */
    @Test
    public void testCountPlayers_3() {
        List<String> names = new ArrayList<String>();
        names.add("test1");
        names.add("test2");
        names.add("test3");
        control.createPlayers(names);
        assertEquals(model.getPlayerCount(), 3);
    }

    /**
     * Test of createPlayers method, of class ControlImpl.
     */
    @Test
    public void testCountPlayers_2() {
        List<String> names = new ArrayList<String>();
        names.add("test1");
        names.add("test3");
        control.createPlayers(names);
        assertEquals(model.getPlayerCount(), 2);
    }

    @Test
    public void testCreatePlayers() {
        List<String> names = new ArrayList<String>();
        names.add("Hans");
        names.add("Jörg");
        control.createPlayers(names);

        Player player1 = new PlayerImpl("Hans");
        Player player2 = new PlayerImpl("Jörg");

        assertEquals(player1, model.getPlayer(0));
        assertEquals(player2, model.getPlayer(1));
    }

    /**
     * Test of addCards method, of class ControlImpl.
     */
    @Test
    public void testAddCards() {
        Card[] cards = { new ActionCardAdventure(), new ActionCardCellar(),
                new ActionCardFeast() };

        control.addCards(cards);
        List<Card> cardList = model.getCards();
        List<Card> expectedList = Arrays.asList(cards);

        assertEquals(expectedList, cardList);
    }

    @Test
    public void testexitGame() {
        control.exitGame();
        assertEquals("End", model.getGameState().toString());
    }

    @Test
    public void testNextPhase() {
        assertEquals("Keine",model.getPhase().toString());
        model.setPhase(GamePhase.Action);
        control.nextPhase();
        assertEquals(GamePhase.Buy, model.getPhase());
        assertEquals("Kaufphase",model.getPhase().toString());
    }

    @Test
    public void testNextPhase2() {
        model.addPlayer(new PlayerImpl("i"));
        model.addPlayer(new PlayerImpl("b"));
        model.setPhase(GamePhase.Buy);
        control.nextPhase();
        assertEquals(GamePhase.CleanUp, model.getPhase());
    }
    
    @Test
    public void testNextPhase3() {
        model.addPlayer(new PlayerImpl("i"));
        model.addPlayer(new PlayerImpl("b"));
        model.setPhase(GamePhase.CleanUp);
        control.nextPhase();
        assertEquals(GamePhase.Action, model.getPhase());
    }

    @Test
    public void testNextPlayer() {
        model.addPlayer(new PlayerImpl("i"));
        model.addPlayer(new PlayerImpl("b"));
        model.setCurrentPlayer(0);
        control.nextPlayer(true);
        assertEquals("b", model.getCurrentPlayer().getName());
        control.nextPlayer(true);
        assertEquals("i", model.getCurrentPlayer().getName());
    }

}