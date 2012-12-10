package dominion.view;

import dominion.view.utils.SWTBotBase;
import dominion.common.GameState;
import dominion.model.Player;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.*;
import static org.eclipse.swtbot.swt.finder.SWTBotAssert.*;
import org.junit.*;

/**
 * Klasse zum Testen der Spielerauswahl.
 *
 * @author Tobi
 */
public class SelectPlayersTest extends SWTBotBase {
    @Before
    public void setUp() {
        super.setUpSWTBotTest();

        getModel().setGameState(GameState.SetPlayers);

        asyncExec(new VoidResult() {
            @Override
            public void run() {
                new Main(getControl(), getModel(), getDisplay());
            }
        });
    }

    /**
     * Weiter-Button.
     */
    @Test
    public void testNext() {
        getBot().buttonWithId("buttonNext").click();
        waitForShell("playerNames");
    }

    /**
     * Prüft ob die Auswahlbox 3 Einträge enthält.
     */
    @Test
    public void testSelect() {
        Assert.assertEquals(3, getBot().
                comboBoxWithId("comboPlayers").itemCount());
    }

    /**
     * 2 Spieler.
     */
    @Test
    public void testSelect2() {
        setPlayerCount(2);
        setPlayerNames(2);
        Assert.assertEquals(2, getModel().getPlayerCount());
    }

    /**
     * 3 Spieler.
     */
    @Test
    public void testSelect3() {
        setPlayerCount(3);
        setPlayerNames(3);
        Assert.assertEquals(3, getModel().getPlayerCount());
    }

    /**
     * 4 Spieler.
     */
    @Test
    public void testSelect4() {
        setPlayerCount(4);
        setPlayerNames(4);
        Assert.assertEquals(4, getModel().getPlayerCount());
    }

    /**
     * Wählt die Spielerzahl aus und wechselt bis zum Karten auswählen Dialog
     *
     * @param count Anzahl Spieler
     */
    private void setPlayerCount(int count) {
        SWTBotCombo combo = getBot().comboBoxWithId("comboPlayers");
        combo.setSelection(count + " Spieler");
        getBot().buttonWithId("buttonNext").click();
        //waitForShell("Spielernamen wählen");
    }

    /**
     * 2 Spieler Textfeldprüfung.
     */
    @Test
    public void testTexts2() {
        setPlayerCount(2);
        checkTexts(2);
    }

    /**
     * 3 Spieler Textfeldprüfung.
     */
    @Test
    public void testTexts3() {
        setPlayerCount(3);
        checkTexts(3);
    }

    /**
     * 4 Spieler Textfeldprüfung.
     */
    @Test
    public void testTexts4() {
        setPlayerCount(4);
        checkTexts(4);
    }

    /**
     * 2 Spieler.
     */
    @Test
    public void testEnterName2() {
        setPlayerCount(2);
        setPlayerNames(2);
        checkPlayerNames(2);
    }

    /**
     * 3 Spieler.
     */
    @Test
    public void testEnterName3() {
        setPlayerCount(3);
        setPlayerNames(3);
        checkPlayerNames(3);
    }

    /**
     * 4 Spieler.
     */
    @Test
    public void testEnterName4() {
        setPlayerCount(4);
        setPlayerNames(4);
        checkPlayerNames(4);
    }

    /**
     * Fehler bei keinen Usernamen?
     */
    @Test
    public void testNoUsername() {
        setPlayerCount(3);
        getBot().buttonWithId("buttonNext").click();
        assertText("Bitte alle Felder ausfüllen.",
                getBot().labelWithId("errorLabel"));
    }

    /**
     * Fehler bei doppelten Usernamen?
     */
    @Test
    public void testDuplicateUsername() {
        setPlayerCount(3);
        for (int num = 0; num < 3; num++) {
            getBot().textWithId("playerName" + num).setText("test");
        }
        getBot().buttonWithId("buttonNext").click();
        assertText("Ein Name darf nur einmal vorkommen.",
                getBot().labelWithId("errorLabel"));
    }

    // Testet die Menü-Option "New Game"
    @Test
    public void testMenuNewGame() {
        getBot().buttonWithId("buttonNext").click();
        getBot().menu("File").click();
        getBot().menu("New Game").click();
        Assert.assertEquals(GameState.SetPlayers, getModel().getGameState());
    }

    /**
     * Prüft ob genug Testfelder vorhanden sind.
     *
     * @param count Anzahl Spieler
     */
    private void checkTexts(final int count) {
        Assert.assertEquals(count, getBot().widgets(
                allOf(widgetOfType(Text.class))).size());
    }

    /**
     * Prüft ob die Spielernamen im Model gespeichert wurden.
     *
     * @param count Anzahl Spieler
     */
    private void checkPlayerNames(final int count) {
        Player player;
        for (int index = 0; index < count; index++) {
            player = getModel().getPlayer(index);
            Assert.assertEquals("test " + index, player.getName());
        }
    }

    /**
     * Fügt für jeden Spieler einen Dummy-Namen ein, wechselt zum
     * nächsten Schritt und schließt die Shell.
     *
     * @param count Anzahl Spieler
     */
    private void setPlayerNames(final int count) {
        for (int num = 0; num < count; num++) {
            getBot().textWithId("playerName" + num).setText("test " + num);
        }
        getBot().buttonWithId("buttonNext").click();
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        closeShell();
    }
}
