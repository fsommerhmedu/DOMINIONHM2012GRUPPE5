package dominion.model.cards;

import dominion.model.Player;

/**
 * Repräsentiert die Karte Müll.
 *
 * @author Martin
 */
public class Trash extends CardBase {

    /**
     * Konsturktor.
     */
    public Trash() {
        super(NOT_NEEDED, "Müll");
    }

    @Override
    public final void play(final Player player) {
        // TODO: Wegzuwerfende Karte auf den Müll legen.
    }
}
