import java.util.Scanner;

class HumanPlayer implements Player {
    /////////////////////////////
    /*       Class members      */
    /////////////////////////////
    private Scanner scanner;

    /////////////////////////////
    /*       Constructors      */
    /////////////////////////////
    public HumanPlayer() {
        scanner = new Scanner(System.in);

    }

    ///////////////////////////////////////
    /* PlayTurn and its helpers function */
    //////////////////////////////////////

    /**
     * play its turn in the given board with its mark
     *
     * @param board the board we play on
     * @param mark  the players mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        System.out.print("Player " + mark + ", type coordinates: ");
        String cordinates = scanner.nextLine();
        //legal input
        while (!legalCordinats(board, cordinates)) {
            System.out.print("Invalid coordinates, type again: ");
            cordinates = scanner.nextLine();
        }
        int[] rowAndCol = convertCordinatesToInt(cordinates);
        board.putMark(mark, rowAndCol[0], rowAndCol[1]);
    }

    /**
     * Checks if the given input is valid
     *
     * @param board      the board we want to check on if its valid
     * @param cordinates the given coordinates as a string
     * @return true if it is valid otherwise false
     */
    private boolean legalCordinats(Board board, String cordinates) {
        int lengthBoard = board.getSize();
        if (cordinates.length() != 2) {
            return false;
        }
        int[] rowAndCol = convertCordinatesToInt(cordinates);
        int row = rowAndCol[0];
        int col = rowAndCol[1];
        if (row >= lengthBoard || col >= lengthBoard) {
            return false;
        }
        //We check
        if (board.getMark(row, col) != Mark.BLANK) {
            return false;
        }
        return true;
    }

    /**
     * Converter to the string coordinates to array of integers where int[0] is the row and int[1] is the col
     *
     * @param cordinates as a string
     */
    private int[] convertCordinatesToInt(String cordinates) {
        int[] rowAndCol = new int[2];
        rowAndCol[0] = Integer.parseInt(String.valueOf(cordinates.charAt(0)));
        rowAndCol[1] = Integer.parseInt(String.valueOf(cordinates.charAt(1)));
        return rowAndCol;
    }
}
