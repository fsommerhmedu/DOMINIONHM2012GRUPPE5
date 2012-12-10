package dominion.view;

import dominion.view.utils.SWTBotBase;
import dominion.model.cards.Card;
import java.util.List;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.junit.*;

/**
 * Klasse zum Testen der Kartenauswahl.
 *
 * @author Tobi
 */
public class SelectCardsTest extends SWTBotBase {
    /**
    * Anzahl der Karten die zwingend ausgewählt werden müssen
    */
    private final static int CARDS_TO_SELECT = 10;

    @Before
    public void setUp() {
        super.setUpSWTBotTest();

        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                new SelectCards(getModel(), getControl(), getDisplay()).display();
            }
        });
    }

    // Prüft ob die Checkboxen bei < 10 selektierten aktiviert sind
    @Test
    public void testCheckboxesEnabled() {
        List<SWTBotCheckBox> allCb = getCheckboxes();
        assertCheckboxesEnabled(allCb);

        List<SWTBotCheckBox> cb = selectCheckboxes(allCb, CARDS_TO_SELECT);
        deselectCheckboxes(cb, 1);

        assertCheckboxesEnabled(allCb);
    }

    // Prüft ob die Checkboxen bei = 10 selektierten deaktiviert werden
    @Test
    public void testCheckboxesDisable() {
        List<SWTBotCheckBox> widgets = getCheckboxes();

        List<SWTBotCheckBox> mustBeEnabled = selectCheckboxes(widgets, CARDS_TO_SELECT);
        List<SWTBotCheckBox> mustBeDisabled = subtractList(widgets, mustBeEnabled);

        assertCheckboxesEnabled(mustBeEnabled);
        assertCheckboxesDisabled(mustBeDisabled);
    }

    // Weiter-Button deaktiviert wenn < 10 selektiert?
    @Test
    public void testNextDisabled() {
        List<SWTBotCheckBox> allCb = getCheckboxes();
        assertButtonDisabled("buttonNext");
        List<SWTBotCheckBox> cb = selectCheckboxes(allCb, CARDS_TO_SELECT - 1);
        assertButtonDisabled("buttonNext");
        deselectCheckboxes(cb);
        deselectCheckboxes(selectCheckboxes(allCb, CARDS_TO_SELECT), 1);
        assertButtonDisabled("buttonNext");        
    }

    // Weiter-Button deaktiviert wenn = 10 selektiert?
    @Test
    public void testNextEnabled() {
        List<SWTBotCheckBox> allCb = getCheckboxes();
        List<SWTBotCheckBox> cb = selectCheckboxes(allCb, CARDS_TO_SELECT);
        assertButtonEnabled("buttonNext");
        deselectCheckboxes(cb);
        selectCheckboxes(allCb, CARDS_TO_SELECT);
        assertButtonEnabled("buttonNext");
    }

    // Prüft das pro Checkbox angezeigte Bild
    @Test
    public void testCheckboxHoverImage(){
        List<SWTBotCheckBox> allCb = getCheckboxes();
        SWTBotLabel imageLabel = getBot().labelWithId(SWTBOT_DATA_ID, "cardImage");
        Card card;
        for(SWTBotCheckBox cb:allCb){
            cb.select();
            card = getLinkedCard(cb);
            Assert.assertEquals("shown card doesn't match card '"+card.getName()+"'",card,(Card)getLinkedCard(imageLabel));
            // Echter Bildvergleich war viel zu langsam
            //assertImageEquals("Shown image doesn't match image of card '"+card.getName()+"'",card.getResourceNameBig(), imageLabel);
            cb.deselect();
        }
    }

    @After
    public void tearDown() {
        closeShell();
    }
}
