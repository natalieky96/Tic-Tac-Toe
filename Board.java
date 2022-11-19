public class Board {
    /////////////////////////////
    /*     Constant number      */
    /////////////////////////////
    public static int MAX_LENGTH = 9;
    public static int MIN_LENGTH = 2;
    public static int DEFAULT_LENGTH_SIZE = 4;

    /////////////////////////////
    /*       Class members      */
    /////////////////////////////
    private int size;
    private Mark[][] boardState;

    /////////////////////////////
    /*       Constructors      */
    /////////////////////////////
    public Board() {
        this.size = DEFAULT_LENGTH_SIZE;
        initBoard();
    }

    public Board(int size) {
        this.size = size;
        initBoard();
    }

    /**
     * Initiliaze a new board
     */
    private void initBoard() {
        this.boardState = new Mark[this.size][this.size];
        for (int i = 0; i < this.boardState.length; i++) {
            for (int j = 0; j < this.boardState[0].length; j++) {
                this.boardState[i][j] = Mark.BLANK;
            }
        }
    }

    /////////////////////////////
    /*    Getters              */
    /////////////////////////////
    public int getSize() {
        return size;
    }

    /**
     * Returned a deep copy of the board state
     */
    public Mark[][] getBoard() {
        Mark[][] boardStateCopy = new Mark[this.size][this.size];
        for (int i = 0; i < this.boardState.length; i++) {
            for (int j = 0; j < this.boardState[0].length; j++) {
                boardStateCopy[i][j] = this.boardState[i][j];
            }
        }

        return boardStateCopy;
    }

    /*
    Get the mark at the specific place if possible, if it is not legal returned BLANK
     */
    public Mark getMark(int row, int col) {
        if (isLegalSpot(row, col)) {
            return this.boardState[row][col];
        }
        return Mark.BLANK;
    }

    /////////////////////////////
    /*    Other functions      */
    /////////////////////////////

    /**
     * Put the mark in the right place
     *
     * @param mark X or O
     * @param row  the row number
     * @param col  the col number
     * @return true if it was successful False if not
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (!isLegalSpot(row, col)) {
            return false;
        }
        if (this.boardState[row][col] != Mark.BLANK) {
            return false;
        }
        this.boardState[row][col] = mark;
        return true;
    }

    /**
     * Checks if the(row,col) spot is a legal spot
     *
     * @param row
     * @param col
     * @return true if it is legal false if not
     */
    private boolean isLegalSpot(int row, int col) {
        if (row >= this.size || col >= this.size || row < 0 || col < 0) {
            return false;
        }
        return true;
    }
}
