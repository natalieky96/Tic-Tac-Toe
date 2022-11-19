/**
 * The interface of all players
 */
interface Player {
    /**
     * play its turn in the given board with its mark
     *
     * @param board the board we play on
     * @param mark  the players mark
     */
    void playTurn(Board board, Mark mark);
}
