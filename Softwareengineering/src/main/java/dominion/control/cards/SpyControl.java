package dominion.control.cards;

import dominion.model.Player;

/**
 * Karte Spion.
 *
 * @author Florian
 */
public class SpyControl extends ControlBase {

    /**
     * @param player Spieler der die Karte ausspielt
     */
    public SpyControl(final Player player) {
        super(player);
    }

    /**
     * Plus eine Karte.
     * Plus eine Aktion
     *
     * <br><br><b>Noch nicht implementiert:</b><br>
     * Jeder Spieler (auch du selbst) deckt die oberste
     * Karte seines Nachziehstapels auf.
     * Du entscheidest, ob er sie ablegt oder zurück auf
     * seinen Nachziehstapel legt.
     */
    @Override
    public final void play() {
        final Player player = getPlayer();

        player.addCardToHand(player.getUppestCard());
        player.addActions(1);
        //TODO: Jeder spieler deckt die erste Karte seines Nachziehstapels
        //auf und entscheidet
    }
}
