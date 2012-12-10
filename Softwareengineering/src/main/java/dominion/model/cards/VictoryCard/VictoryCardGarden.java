package dominion.model.cards.VictoryCard;

import dominion.model.Player;

/**
 * Gärtenkarte.
 *
 * @author Martin
 */
public class VictoryCardGarden extends VictoryCardBase implements VictoryCard {

    /**
     * Kosten der Karte.
     */
    private static final int COST = 4;

    /**
     * Konstruktor.
     */
    public VictoryCardGarden() {
        super(0, COST, "Gärten");
    }

    @Override
    public final int getValue(final Player player) {

        // TODO: Wert der Karte kann ist unterschiedlich. Am
        // Ende des Spiels werden all Punktekarten gezählt (Anzahl)
        // TODO: Die Anzahl durch 10 geteilt und abgerundet.
        //Das Ergebnis gibt es für jeden Garten als Wert.
        return super.getValue(player);
    }

    @Override
    public final void play(final Player player) {
    }
}
