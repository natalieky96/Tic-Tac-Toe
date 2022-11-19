public class GeniusPlayer implements Player {
    /////////////////////////////
    /*       Class field      */
    /////////////////////////////
    int currRow;
    int currCol;

    /////////////////////////////
    /*       Constructors      */
    /////////////////////////////
    public GeniusPlayer() {
        currRow = 0;
        currCol = 1;
    }

    /////////////////////////////
    /*   Other functions      */
    //////////////////////// /////

    /**
     * play its turn in the given board with its mark
     *
     * @param board the board we play on
     * @param mark  the players mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        zeroColandRow(board);
        int i = 0, j = 0;
        while (currCol + i < board.getSize()) {
            while (currRow + j < board.getSize()) {
                currRow += j;
                if (board.getMark(currRow, currCol) != Mark.BLANK) {
                    j++;
                } else {
                    board.putMark(mark, currRow, currCol);
                    return;
                }
            }
            currRow = 0;
            j = 0;
            i++;
            currCol += i;
        }
    }

    /**
     * It's probably a new game between Genius and someone, so initiliaze col and row
     */
    private void zeroColandRow(Board board) {
        if (board.getMark(0, 1) == Mark.BLANK) {
            currRow = 0;
            currCol = 1;
        }
    }
}
