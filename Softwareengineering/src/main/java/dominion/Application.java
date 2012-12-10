package dominion;

import dominion.control.ControlImpl;
import dominion.model.GameImpl;
import dominion.view.Main;

/**
 * Initialisiert und startet das Spiel.
 *
 * @author Martin
 */
public final class Application {

    /**
     * Damit checkstyle ruhe gibt.
     */
    private Application() {
    }

    /**
     * Startet Dominion.
     *
     * @param args Keine Funktion.
     */
    public static void main(final String... args) {
        final GameImpl model = new GameImpl();
        final ControlImpl control = new ControlImpl(model);
        control.start();
        new Main(control, model);
    }
}
