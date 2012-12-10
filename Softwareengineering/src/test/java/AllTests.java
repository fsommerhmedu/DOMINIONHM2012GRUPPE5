import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dominion.control.ControlImplTest;
import dominion.model.ActionCardBasicTest;
import dominion.model.ActionCardTest;
import dominion.model.GameImplTest;
import dominion.model.MoneyCardTest;
import dominion.model.PlayerImplTest;
import dominion.model.VictoryCardTest;
import dominion.view.GameScreenTest;
import dominion.view.MessageDialogTest;
import dominion.view.SelectCardsTest;
import dominion.view.SelectPlayersTest;

/**
 * Test-Suite.
 * Enthält alle Testklassen
 *
 * @author Florian
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ActionCardBasicTest.class,
    GameImplTest.class,
    MoneyCardTest.class,
    VictoryCardTest.class,
    SelectCardsTest.class,
    SelectPlayersTest.class,
    ControlImplTest.class,
    ActionCardTest.class,
    PlayerImplTest.class,
    GameScreenTest.class,
    MessageDialogTest.class})
public class AllTests {

}
