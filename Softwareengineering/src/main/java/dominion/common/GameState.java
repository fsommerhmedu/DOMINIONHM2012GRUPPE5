package dominion.common;

/**
 * Status des Spiels.
 *
 * @author Martin
 */
public enum GameState {
    /**
     * Status wenn das Spiel startet.
     */
    Start,
    /**
     * Status wenn der Spieler die Anzahl und Namen der Spieler angibt.
     */
    SetPlayers,
    /**
     * Status wenn die 10 Aktionskarten gewählt werden.
     */
    SelectCards,
    /**
     * Spiel läuft.
     */
    GameScreen,
    /**
     * Spiel ist beendet.
     */
    End,
}
