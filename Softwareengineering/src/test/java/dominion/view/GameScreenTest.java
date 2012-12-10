package dominion.view;

import dominion.view.utils.SWTBotBase;
import dominion.model.*;
import dominion.model.cards.Card;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.*;
import static org.eclipse.swtbot.swt.finder.SWTBotAssert.*;
import org.junit.*;

/**
 * Klasse zum Testen des Hauptbildschirms.
 *
 * @author Tobi
 */
public class GameScreenTest extends SWTBotBase {

    /**
     * Auswählbare Karten
     */
    private ChooseableCards availableCards;
    /**
     * Anzahl der Karten die zwingend ausgewählt werden müssen
     */
    private final static int CARDS_TO_SELECT = 10;
    /**
     * Anzahl der Karten die pro Zug gezogen werden
     */
    private final static int CARDS_TO_DRAW = 5;
    /**
     * Ausgewählte Karten
     */
    private List<Card> chosenCards;
    // Spielernamen
    private final static String PLAYER1_NAME = "player1";
    private final static String PLAYER2_NAME = "player2";

    //private final static String PLAYER3_NAME = "player3";
    //private final static String PLAYER4_NAME = "player4";
    @Before
    public void setUp() {
        super.setUpSWTBotTest();

        // Spieler einrichten
        getControl().createPlayers(Arrays.asList(PLAYER1_NAME, PLAYER2_NAME));

        // Karten einstellen
        availableCards = new ChooseableCardsImpl();
        chosenCards = new ArrayList<Card>();

        int counter = 0;
        for (Map.Entry<String, Card> cardPair : availableCards.getCards().entrySet()) {
            chosenCards.add(cardPair.getValue());
            counter++;
            if (counter == CARDS_TO_SELECT) {
                break;
            }
        }
        shuffleList(chosenCards);
        getControl().addCards(chosenCards.toArray(new Card[0]));

        // Shell öffnen
        asyncExec(new VoidResult() {

            @Override
            public void run() {
                GameScreen screen = new GameScreen(getModel(), getControl(), getDisplay());
                screen.display();
            }
        });

        waitForShell("gameScreen");
    }

    // Sind alle gewählten ActionCards vorhanden?
    @Test
    public void testActionCards() throws InterruptedException {
        List<? extends Widget> widgets = getBot().widgets(
                allOf(
                inGroup(withId(SWTBOT_DATA_ID, "actionGroup")), widgetOfType(Button.class)));
        Assert.assertEquals("too many action cards displayed",
                CARDS_TO_SELECT, widgets.size());

        List<String> cardNames = new ArrayList<String>();
        for (Widget widget : widgets) {
            cardNames.add(getLinkedCard((Button) widget).getName());
        }



        for (Card card : chosenCards) {
            Assert.assertTrue("card '" + card.getName() + "' isn't displayed",
                    cardNames.contains(card.getName()));
        }
    }

    // Werden alle Standard-Handkarten angezeigt?
    @Test
    public void testHandCards() {
        List<Card> myHandCards = getModel().getCurrentPlayer().getCardsOnHand();

        // Angezeigte Karten suchen
        List<? extends Widget> widgets = getBot().widgets(
                allOf(
                inGroup(withId(SWTBOT_DATA_ID, "handGroup")), widgetOfType(Button.class)));

        Assert.assertEquals("too many hand cards displayed",
                CARDS_TO_DRAW, widgets.size());
        List<Card> displayedCards = new ArrayList<Card>();
        for (Widget widget : widgets) {
            displayedCards.add(getLinkedCard((Button) widget));
        }

        for (Card c : myHandCards) {
            Assert.assertTrue("one hand card '" + c.getName() + "' is not displayed", displayedCards.contains(c));
            displayedCards.remove(c);
        }
    }

    // Nächste Phase
    @Test
    public void testButtonNext() {
        String playerName;
        String phase;

        for (int playerIndex = 0; playerIndex < getModel().getPlayerCount() * 2; playerIndex++) {
            playerName = getModel().getCurrentPlayer().getName();

            for (int phaseIndex = 0; phaseIndex < 3; phaseIndex++) {
                phase = getModel().getPhase().toString();
                assertText(phase, getBot().labelWithId(SWTBOT_DATA_ID, "LabelPhase"));

                assertText("Spieler: " + playerName, getBot().widget(
                        allOf(withId(SWTBOT_DATA_ID, "playerGroup"), widgetOfType(Group.class))));
                getBot().buttonWithId(SWTBOT_DATA_ID, "buttonPhase").click();
            }
        }
    }

    // Spielzug beenden
    @Test
    public void testButtonEnd() {
        int playerIndex;

        for (int test = 0; test <= 2; test++) {
            playerIndex = getModel().getCurrentPlayerIndex();

            for (int phaseIndex = 0; phaseIndex < test; phaseIndex++) {
                getBot().buttonWithId(SWTBOT_DATA_ID, "buttonPhase").click();
            }

            getBot().buttonWithId(SWTBOT_DATA_ID, "buttonEnd").click();

            Assert.assertTrue("game didn't switch to next player after clicking 'buttonEnd'", playerIndex != getModel().getCurrentPlayerIndex());
        }
    }

    // Kauf
    @Test
    public void testBuy() {
        toBuyPhase();

        int money = getModel().getCurrentPlayer().getMoney();

        List<? extends Widget> widgets = getBot().widgets(
                allOf(
                inGroup(withId(SWTBOT_DATA_ID, "actionGroup")), widgetOfType(Button.class)));
        Card card;
        for (Widget widget : widgets) {
            card = getLinkedCard((Button) widget);
            if (card.getCost() <= money) {
                new SWTBotButton((Button) widget).click();
                break;
            }
        }
    }

    // Kauf, obwohl kein Geld mehr
    @Test
    public void testBuyNoMoney() {
        toBuyPhase();

        int money = getModel().getCurrentPlayer().getMoney();

        List<? extends Widget> widgets = getBot().widgets(
                allOf(
                inGroup(withId(SWTBOT_DATA_ID, "actionGroup")), widgetOfType(Button.class)));
        Card card;
        for (Widget widget : widgets) {
            card = getLinkedCard((Button) widget);
            if (card.getCost() > money) {
                new SWTBotButton((Button) widget).click();
                assertAndCloseMessageDialog("Kauf ist nicht möglich!");
                break;
            }
        }
    }

    // Kauf, obwohl keine Käufe mehr
    @Test
    public void testBuyNoPurchase() {
        toBuyPhase();

        int money = getModel().getCurrentPlayer().getMoney();

        List<? extends Widget> widgets = getBot().widgets(
                allOf(
                inGroup(withId(SWTBOT_DATA_ID, "actionGroup")), widgetOfType(Button.class)));

        for (Widget widget : widgets) {
            new SWTBotButton((Button) widget).click();
            new SWTBotButton((Button) widget).click();
            assertAndCloseMessageDialog("Kauf ist nicht möglich!");
            break;
        }

    }

    private void sleep() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameScreenTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void toBuyPhase() {
        getBot().buttonWithId(SWTBOT_DATA_ID, "buttonEnd").click();
        getBot().buttonWithId(SWTBOT_DATA_ID, "buttonPhase").click();

        if (getModel().getCurrentPlayer().getMoney() == 0) {
            toBuyPhase();
        }
    }

    @After
    public void tearDown() {
        closeShell();

    }
}
