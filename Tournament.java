import java.lang.String;

public class Tournament {
    /////////////////////////////
    /*       Constants         */
    /////////////////////////////
    public static final String RESULT_TITLE = "######### Results #########";
    public static final String WRONG_PLAYER_TYPE = "Wrong player type!!";
    private static final String NUM_WINNING_PLAYER_ONE = "Player 1, %s won: %d rounds";
    private static final String NUM_WINNING_PLAYER_TWO = "Player 2, %s won: %d rounds";
    private static final String NUM_OF_TIES = "Ties: ";
    /////////////////////////////
    /*       Class members      */
    /////////////////////////////
    int rounds;
    Renderer renderer;
    Player[] players;
    Game currGame;

    /////////////////////////////
    /*       Constructors     */
    /////////////////////////////
    public Tournament(int rounds, Renderer renderer, Player[] players) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = players;
    }

    /////////////////////////////
    /*   Other Functions       */
    /////////////////////////////

    /**
     * Play a tournament it called by the main function rounds times
     *
     * @param size        the size of the board
     * @param winStreak   the length of sequence to win
     * @param playerNames the players type
     */
    public void playTournament(int size, int winStreak, String[] playerNames) {
        int numWinningPlayerOne = 0, numWinningPlayerTwo = 0, numTies = 0, idx = 0;
        Player playerX = this.players[0];
        Player playerO = this.players[1];
        while (idx < this.rounds) {
            if (idx % 2 == 0) {
                this.currGame = new Game(playerX, playerO, size, winStreak, this.renderer);
            } else {
                this.currGame = new Game(playerO, playerX, size, winStreak, this.renderer);
            }
            Mark winnerMark = this.currGame.run();
            //player1 is x when idx is even and O when odd
            if ((winnerMark == Mark.X && idx % 2 == 0) || winnerMark == Mark.O && idx % 2 == 1) {
                numWinningPlayerOne++;
            }
            //player1 is x when idx is even and O when odd
            else if ((winnerMark == Mark.O && idx % 2 == 0) || winnerMark == Mark.X && idx % 2 == 1) {
                numWinningPlayerTwo++;
            } else {
                numTies++;
            }
            idx++;
        }
        System.out.println(RESULT_TITLE);
        System.out.println(String.format(NUM_WINNING_PLAYER_ONE, playerNames[0], numWinningPlayerOne));
        System.out.println(String.format(NUM_WINNING_PLAYER_TWO, playerNames[1], numWinningPlayerTwo));
        System.out.println(NUM_OF_TIES + numTies);
    }

    /**
     * the main function that runs the game take the arguments and run it according to the given params
     *
     * @param args
     */
    public static void main(String[] args) {
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();

        //Save the arguments at the right place
        int rounds = Integer.parseInt(args[0]);
        int boardSize = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        //Args[3] is the type renderer
        Renderer renderer = rendererFactory.buildRenderer(args[3].toLowerCase(), boardSize);
        String typePlayerOne = args[4].toLowerCase(), typePlayerTwo = args[5].toLowerCase();

        Player playerOne = playerFactory.buildPlayer(typePlayerOne);
        Player playerTwo = playerFactory.buildPlayer(typePlayerTwo);

        if (playerOne == null || playerTwo == null) {
            System.out.println(WRONG_PLAYER_TYPE);
        }
        if (winStreak > boardSize) {
            winStreak = boardSize;
        }
        Player[] players = {playerOne, playerTwo};
        Tournament tournament = new Tournament(rounds, renderer, players);
        String[] playerTypes = {typePlayerOne, typePlayerTwo};
        tournament.playTournament(boardSize, winStreak, playerTypes);
    }
}
