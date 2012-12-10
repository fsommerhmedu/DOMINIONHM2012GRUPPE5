/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.common;

/**
 * Aktuelle Phase des Spiels.
 *
 * @author Moe
 */
public enum GamePhase {

    /**
     * "Leerphase".
     */
    None,
    /**
     * Kaufphase.
     */
    Buy,
    /**
     * Aufräumphase.
     */
    CleanUp,
    /**
     * Aktionsphase.
     */
    Action;

    @Override
    public String toString() {
        switch (this) {
            case None:
                return "Keine";
            case Buy:
                return "Kaufphase";
            case Action:
                return "Aktionsphase";
            case CleanUp:
                return "Aufräumen";
            default:
                assert false : "Illegal case";
        }

        return null;
    }
}
