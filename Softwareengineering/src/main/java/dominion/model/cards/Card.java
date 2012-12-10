package dominion.model.cards;

import dominion.model.Player;
import java.io.InputStream;

/**
 * Eine Spielkarte.
 *
 * @author Moe
 */
public interface Card {

    /**
     * Gibt den Namen der Karte zurück.
     *
     * @return Name der Karte.
     */
    String getName();

    /**
     * Karte wird ausgespielt.
     *
     * @param player Spieler, welcher die Karte ausgespielt hat.
     */
    void play(Player player);

    /**
     * Gibt den Namen der Resource zurück, welche das
     * kleine Bild für die Karte enthält.
     *
     * @return Namen der Resource, welche das kleine Bild für die Karte enthält.
     */
    String getResourceNameSmall();

    /**
     * Gibt die Resource, welche das kleine Bild
     * der Karte enthält, als Stream zurück.
     *
     * @return Resource, welche das kleine Bild der Karte enthält, als Stream.
     */
    InputStream getResourceSmallAsStream();

    /**
     * Gibt den Namen der Resource zurück, welche das große
     * Bild für die Karte enthält.
     *
     * @return Namen der Resource, welche das große Bild für die Karte enthält.
     */
    String getResourceNameBig();

    /**
     * Gibt die Resource, welche das große Bild
     * der Karte enthält, als Stream zurück.
     *
     * @return Resource, welche das große Bild der Karte enthält, als Stream.
     */
    InputStream getResourceBigAsStream();

    /**
     * Getter für die Kosten.
     *
     * @return Kosten der Karte.
     */
    int getCost();
}
