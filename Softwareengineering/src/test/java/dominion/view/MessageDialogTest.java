package dominion.view;

import dominion.view.utils.SWTBotBase;
import org.eclipse.swt.SWT;
import org.junit.*;

/**
 *
 * @author Tobi
 */
public class MessageDialogTest extends SWTBotBase {

    @Before
    public void setUp() {
    }

    @Test
    public void testMessageDialogOk() {
        startTest(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(MessageDialog.Result.OK,
                        MessageDialog.show("test"));
            }
        }, SWT.OK);
    }

    @Test
    public void testMessageDialogYes() {
        startTest(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(MessageDialog.Result.YES,
                        MessageDialog.show("test","titel",
                        MessageDialog.Type.INFO,MessageDialog.Buttons.YES_NO));
            }
        }, SWT.YES);
    }

    @Test
    public void testMessageDialogNo() {
        startTest(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(MessageDialog.Result.NO,
                        MessageDialog.show("test","titel",
                        MessageDialog.Type.INFO,MessageDialog.Buttons.YES_NO));
            }
        }, SWT.NO);
    }


    private void startTest(Runnable dialogRunnable, int buttonToClick) {
        new Thread(dialogRunnable).start();

        waitForShell(MessageDialog.DIALOG_ID);

        getBot().buttonWithId(SWTBOT_DATA_ID, buttonToClick + "").click();

        getBot().waitUntil(new ShellClosedCondition(MessageDialog.DIALOG_ID));
    }
}
