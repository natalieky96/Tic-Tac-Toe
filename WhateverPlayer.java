import java.util.Random;

public class WhateverPlayer implements Player {
    /////////////////////////////
    /*       Class field      */
    /////////////////////////////
    Random rand;

    /////////////////////////////
    /*       Constructors      */
    /////////////////////////////
    public WhateverPlayer() {
        rand = new Random();
    }

    /////////////////////////////
    /*    Other function       */
    /////////////////////////////

    /**
     * play its turn in the given board with its mark
     *
     * @param board the board we play on
     * @param mark  the players mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int row = rand.nextInt(board.getSize());
        int col = rand.nextInt(board.getSize());
        while (board.getMark(row, col) != Mark.BLANK) {
            row = rand.nextInt(board.getSize());
            col = rand.nextInt(board.getSize());
        }
        board.putMark(mark, row, col);
    }

}
