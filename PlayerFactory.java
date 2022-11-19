public class PlayerFactory {
    /*
    Build a player on base of the type that given as a string,
    returned the new player
     */
    public Player buildPlayer(String type) {
        Player player;
        String lowerCaseType= type.toLowerCase();
        if (lowerCaseType.compareTo("human") == 0) {
            player = new HumanPlayer();
        } else if (lowerCaseType.compareTo("whatever") == 0) {
            player = new WhateverPlayer();
        } else if (lowerCaseType.compareTo("clever") == 0) {
            player = new CleverPlayer();
        } else if (lowerCaseType.compareTo("genius") == 0){
            player = new GeniusPlayer();
        }
        else{
            player= null;
        }
        return player;
    }
}

//Player number1
//        switch (strPlayers[0]) {
//            case "HumanPlayer":
//                players[0] = new HumanPlayer();
//                break;
//            case "WhateverPlayer":
//                players[0] = new WhateverPlayer();
//                break;
//            case "CleverPlayer":
//                players[0] = new CleverPlayer();
//                break;
//            case "GeniusPlayer":
//                players[0] = new GeniusPlayer();
//                break;
//            default:
//                break;
//        }
