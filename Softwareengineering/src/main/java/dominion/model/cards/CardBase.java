package dominion.model.cards;

import dominion.model.cards.ActionCard.ActionCard;
import dominion.model.cards.MoneyCard.MoneyCard;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Abstrakte Basisklasse für alle Karten.
 *
 * @author moe
 */
public abstract class CardBase implements Card {

    /**
     * Comparator, um eine Menge von Karten nach Kosten zu sortieren.
     */
    @SuppressWarnings("serial")
    public static class CardCostComparator implements Comparator<Card>,
            Serializable {

        @Override
        public final int compare(final Card o1, final Card o2) {
            return o1.getCost() - o2.getCost();
        }
    }

    /**
     * Comparator, um eine Menge von Karten nach Kartentyp zu sortieren.
     */
    @SuppressWarnings("serial")
    public static class CardTypeComparator implements Comparator<Card>,
            Serializable {

        @Override
        public final int compare(final Card o1, final Card o2) {
            int i = 0;
            int j = 0;
            if (o1 instanceof ActionCard) {
                i = 2;
            } else if (o1 instanceof MoneyCard) {
                i = 1;
            }

            if (o2 instanceof ActionCard) {
                j = 2;
            } else if (o2 instanceof MoneyCard) {
                j = 1;
            }

            return i - j;
        }
    }
    /**
     * Wert der Kosten, falls diese nicht gebraucht werden.
     */
    protected static final int NOT_NEEDED = 0;

    /**
     * Basispfad zu den Bildern (Resourcen).
     */
    private static final String RESOURCES_PATH_BASE = "/dominion/view/Images";

    /**
     * Pfad zu den kleinen Bildern.
     */
    private static final String RESOURCES_PATH_SMALL = RESOURCES_PATH_BASE
            + "/small/";

    /**
     * Pfad zu den großen Bildern.
     */
    private static final String RESOURCES_PATH_BIG = RESOURCES_PATH_BASE
            + "/big/";

    /**
     * Kosten der Karte.
     */
    private final int cost;

    /**
     * Name der Karte.
     */
    private final String name;

    /**
     * @param newCost Kosten der Karte.
     * @param newName Name der Karte.
     */
    public CardBase(final int newCost, final String newName) {
        this.name = newName;
        this.cost = newCost;
    }

    @Override
    public final String getResourceNameSmall() {

        // getName() gibt vollqualifizierten Pfad zurück, wir splitten
        // anhand des Punkts und verwenden nur den letzten Teil (= Klassenname).
        String[] parts = this.getClass().getName().split("\\.");

        return RESOURCES_PATH_SMALL + parts[parts.length - 1] + ".jpg";
    }

    @Override
    public final InputStream getResourceSmallAsStream() {
        return CardBase.class.getResourceAsStream(getResourceNameSmall());
    }

    @Override
    public final InputStream getResourceBigAsStream() {
        return CardBase.class.getResourceAsStream(getResourceNameBig());
    }

    @Override
    public final String getResourceNameBig() {

        // getName() gibt vollqualifizierten Pfad zurück, wir splitten
        // anhand des Punkts und verwenden nur den letzten Teil (= Klassenname).
        String[] parts = this.getClass().getName().split("\\.");

        return RESOURCES_PATH_BIG + parts[parts.length - 1] + ".jpg";
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int getCost() {
        return cost;
    }
}
