package dominion.view;

import dominion.control.Control;
import dominion.model.Game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

/**
 * Klasse für den Begrüßungsbildschirm.
 * Zeigt das Logo von Dominion.
 *
 * @author Florian
 */
public class StartScreen extends DominionShell {

    /**
     * Wartezeit bevor das Startbild verschwindet.
     */
    private static final int WAIT_TIME = 1500;

    /**
     * Konstruktor.
     *
     * @param model Model des Spiels.
     * @param control Controller des Spiels
     * @param display Displayinstanz.
     */
     public StartScreen(final Game model, final Control control,
            final Display display) {
        super(model, control, display, SWT.NO_TRIM | SWT.ON_TOP, false);

    }

    /**
     * Startscreen anzeigen.
     */
    @Override
    public final void display() {

        final Shell shell = getShell();
        Image image = new Image(getDisplay(),
                DominionShell.class.getResourceAsStream(
                "Images/dominion-big.jpg"));
        shell.setImage(image);
        shell.setText("Dominion");
        shell.setSize(image.getImageData().width, image.getImageData().height);
        Monitor primary = getDisplay().getPrimaryMonitor();
        Rectangle bounds = primary.getBounds();
        Rectangle rect = shell.getBounds();
        //Bild in der Mitte des Bildschirms anzeigen
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        shell.setLocation(x, y);
        shell.setBackgroundImage(image);

        //Nächsten Bildschirm beim Loslassen einer Taste
        shell.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(final KeyEvent arg0) {
            }

            @Override
            public void keyReleased(final KeyEvent arg0) {
                dispose();
                getControl().startGame();
            }
        });

        //Nächstes Bild beim Bewegen der Maus (+ WAIT_TIME sec)
        shell.addMouseMoveListener(new MouseMoveListener() {

            @Override
            public void mouseMove(final MouseEvent arg0) {
                try {
                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
                dispose();
                getControl().startGame();
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!getDisplay().readAndDispatch()) {
                getDisplay().sleep();
            }
        }
    }
}
