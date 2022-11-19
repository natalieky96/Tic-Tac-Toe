public class Game {
    public static int DEFAULT_WIN_STREAK = 3;
    public static int TWO_MOVES = 2;
    /////////////////////////////
    /*       Class members      */
    /////////////////////////////
    private Player playerX;
    private Player playerO;
    private Board board;
    private Renderer renderer;
    private int winStreak;

    /////////////////////////////
    /*       Constructors      */
    /////////////////////////////
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = new Board();
        this.winStreak = DEFAULT_WIN_STREAK;
        this.renderer = renderer;
    }

    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = new Board(size);
        this.winStreak = winStreak;
        this.renderer = renderer;
    }

    /////////////////////////////
    /*       Getters            */
    /////////////////////////////
    public int getWinStreak() {
        return this.winStreak;
    }

    /////////////////////////////
    /*     Other function      */
    /////////////////////////////

    /**
     * Run the game and returned the winners mark
     */
    public Mark run() {
        int numPlayMoves = TWO_MOVES;
        int numBoardCells = this.board.getSize() * this.board.getSize();
        this.playerX.playTurn(this.board, Mark.X);
        this.playerO.playTurn(this.board, Mark.O);
        Mark winnerMark = doWeHaveAWinner();
        while (winnerMark == Mark.BLANK && numPlayMoves < numBoardCells) {
            //Print board
            this.renderer.renderBoard(this.board);
            this.playerX.playTurn(this.board, Mark.X);
            winnerMark = doWeHaveAWinner();
            if (winnerMark != Mark.BLANK) {
                break;
            }
            //Print board
            this.renderer.renderBoard(this.board);
            this.playerO.playTurn(this.board, Mark.O);
            winnerMark = doWeHaveAWinner();
            numPlayMoves += TWO_MOVES;
        }
        //Print board
        this.renderer.renderBoard(this.board);
        return winnerMark;
    }

    /**
     * Checks if in the current state we have a winner
     * if we have return who is the winner otherwise returned BLANK
     */
    private Mark doWeHaveAWinner() {
        Mark resultMarkRow, resultMarkCol, resultMark;
        for (int i = 0; i < this.board.getSize(); i++) {
            resultMarkRow = isAStreakInRow(i);
            if (resultMarkRow != Mark.BLANK) {
                return resultMarkRow;
            }
            resultMarkCol = isAStreakInCol(i);
            if (resultMarkCol != Mark.BLANK) {
                return resultMarkCol;
            }
        }
        for (int row = (this.board.getSize() - 1); row >= this.winStreak - 1; row--) {
            for (int col = 0; col <= this.board.getSize() - this.winStreak; col++) {
                resultMark = isAStreakDiagonalRight(row, col);
                if (resultMark != Mark.BLANK) {
                    return resultMark;
                }
            }
        }
        for (int row = (this.board.getSize() - 1); row >= this.winStreak - 1; row--) {
            for (int col = (this.board.getSize() - 1); col >= this.winStreak - 1; col--) {
                resultMark = isAStreakDiagonalLeft(row, col);
                if (resultMark != Mark.BLANK) {
                    return resultMark;
                }
            }
        }
        return Mark.BLANK;
    }

    /**
     * Checks if we have a streak in the length of winStreak in this col
     *
     * @param col the col of the given mark
     * @return The mark of the winner or BLANK if we dont have
     */
    private Mark isAStreakInCol(int col) {
        Mark currMark;
        int currSeqLen = 1;
        for (int row = 1; row < this.board.getSize(); row++) {
            currMark = this.board.getMark(row, col);
            if (currMark == Mark.BLANK) {
                currSeqLen = 0;
                continue;
            }
            if (currMark == this.board.getMark(row - 1, col)) {  //if row and row-1 are equals
                currSeqLen++;
                if (currSeqLen >= this.winStreak) {
                    return currMark;
                }
            } else {
                if (currSeqLen >= this.winStreak) {
                    return this.board.getMark(row - 1, col);  //Return the previous mark
                }
                currSeqLen = 1;
            }
        }
        currMark = this.board.getMark(this.board.getSize() - 1, col);
        if (currSeqLen >= this.winStreak && currMark != Mark.BLANK) {
            return currMark;
        }
        return Mark.BLANK;
    }

    /**
     * Checks if we have a streak in the length of winStreak in this row
     *
     * @param row the col of the given mark
     * @return The mark of the winner or BLANK if we dont have
     */
    private Mark isAStreakInRow(int row) {
        Mark currMark;
        int currSeqLen = 1;
        for (int col = 1; col < this.board.getSize(); col++) {
            currMark = this.board.getMark(row, col);
            if (currMark == Mark.BLANK) {
                currSeqLen = 0;
                continue;
            }
            if (currMark == this.board.getMark(row, col - 1)) { //if row and row-1 are equals
                currSeqLen++;
                if (currSeqLen >= this.winStreak) {
                    return currMark;
                }
            } else {
                if (currSeqLen >= this.winStreak) {
                    return this.board.getMark(row, col - 1);  //Return the previous mark
                }
                currSeqLen = 1;
            }
        }
        currMark = this.board.getMark(row, this.board.getSize() - 1);
        if (currSeqLen >= this.winStreak && currMark != Mark.BLANK) {
            return currMark;
        }
        return Mark.BLANK;
    }

    /**
     * Checks if we have a streak in the length of winStreak in the diagonal up right
     *
     * @param row the row of the given mark
     * @param col the col of the given mark
     * @return The mark of the winner or BLANK if we dont have
     */
    private Mark isAStreakDiagonalRight(int row, int col) {
        int currLength = 1;
        int runnerIdx = 1;
        Mark currMark;
        while (row - runnerIdx >= 0 && (col + runnerIdx < this.board.getSize())) {
            currMark = this.board.getMark(row - runnerIdx, col + runnerIdx);
            if (currMark == Mark.BLANK) {
                currLength = 0;
                runnerIdx++;
                continue;
            }
            if (currMark == this.board.getMark(row - runnerIdx + 1, col + runnerIdx - 1)) {
                currLength++;
                if (currLength >= this.winStreak) {
                    return currMark;
                }
            } else {
                if (currLength >= this.winStreak) {
                    //Return the previous mark
                    return this.board.getMark(row - runnerIdx + 1, col + runnerIdx - 1);
                }
                currLength = 1;
            }
            runnerIdx++;
        }
        return Mark.BLANK;
    }

    /**
     * Checks if we have a streak in the length of winStreak in the diagonal up LEFT
     *
     * @param row the row of the given mark
     * @param col the col of the given mark
     * @return The mark of the winner or BLANK if we dont have
     */
    private Mark isAStreakDiagonalLeft(int row, int col) {
        int currLength = 1, runnerIdx = 1;
        Mark currMark;
        while (row - runnerIdx >= 0 && (col - runnerIdx >= 0)) {
            currMark = this.board.getMark(row - runnerIdx, col - runnerIdx);
            if (currMark == Mark.BLANK) {
                currLength = 0;
                runnerIdx++;
                continue;
            }
            if (currMark == this.board.getMark(row - runnerIdx + 1, col - runnerIdx + 1)) {
                currLength++;
                if (currLength >= this.winStreak) {
                    return currMark;
                }
            } else {
                if (currLength >= this.winStreak) {
                    //Return the previous mark
                    return this.board.getMark(row - runnerIdx + 1, col - runnerIdx + 1);
                }
                currLength = 1;
            }
            runnerIdx++;
        }
        return Mark.BLANK;
    }
}
