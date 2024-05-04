package mancala;

import java.util.ArrayList;
import java.io.Serializable;

public class MancalaGame implements Serializable {
    private GameRules gameBoard;
    private Player playerA;
    private Player playerB;

    private Player currentPlayer;
    private int playerNum;

    // Constructor
    public MancalaGame() {
        playerA = new Player();
        playerB = new Player();

        // Create the game board
        gameBoard = new KalahRules(); //default 

        // Set the current player (you can choose a random starting player)
        currentPlayer = playerA;
        playerNum = 1;
    }


    public GameRules getBoard() {
        // Return the game's board
        return gameBoard;
    }

    public Player getCurrentPlayer() {
        // Return the current player
        return currentPlayer;
    }

    public int getNumStones(int pitNum) /*throws PitNotFoundException*/ {
        // Get the number of stones in a specific pit from the board
        /*if (pitNum < 0 || pitNum > 12){
            throw new PitNotFoundException("Invalid pit number: " + pitNum);
        }*/
        return gameBoard.getNumStones(pitNum);
    }

    public int getStoreCount(Player player) {
        // Get the total number of stones in a player's store
        return player.getStore().getTotalStones();
    }

    public Player getWinner() throws GameNotOverException {
        if(!isGameOver()) {
            throw new GameNotOverException();
        } else {
            // Get the total stones in each player's store
            int playerAStones = gameBoard.getStoreCount(1);
            int playerBStones = gameBoard.getStoreCount(2);
            

            // Compare the total stones and return the player with the most stones
            if (playerAStones > playerBStones) {
                return playerA;
            } else if (playerBStones > playerAStones) {
                return playerB;
            } else {
                return null;
            }
        }
    }

    //change board line to: gameBoard.getNumStones(i) > 0
    public boolean isGameOver() {
        if (gameBoard.isSideEmpty(1) == true || gameBoard.isSideEmpty(7) == true) {
            return true;
        }
        return false; 
    }


    public int move(int startPit) throws InvalidMoveException {
        // Validate the pit number
        if(startPit < 1 || startPit > 12){
            throw new InvalidMoveException("Invalid! Pit must be 1 - 12");
        }
        int result = gameBoard.moveStones(startPit,gameBoard.getCurrentPlayerNum());
        if(!gameBoard.isFreeTurn()) {
            switchPlayers();
        }
        
        return result; 
    }
    public void setBoard(GameRules theBoard) {
        // Set the board for the game
        gameBoard = theBoard;
    }

    public void setCurrentPlayer(Player player) {
        // Set the current player
        currentPlayer = player;
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        playerA = onePlayer;
        playerB = twoPlayer;
        setCurrentPlayer(playerA);
        gameBoard.registerPlayers(playerA, playerB);
    }

    @Override
    public String toString() {
        StringBuilder gameStatus = new StringBuilder();

        gameStatus.append("It is Player ").append(playerNum).append("'s Turn\n");

        // Print the state of Player B's side of the game
        gameStatus.append("Player 2: ");
        for (int i = 12; i >= 7; i--) {
            gameStatus.append("Pit ").append(i).append(": ").append(gameBoard.getNumStones(i)).append(" | ");
        }

        gameStatus.append("\n\tStore: ").append(gameBoard.getStoreCount(2)).append("\t\t\t\t\t\t\t\tStore:").append(gameBoard.getStoreCount(1));

        // Print the state of Player A's side of the game
        gameStatus.append("\nPlayer 1: ");
        for (int i = 1; i <= 6; i++) {
            gameStatus.append("Pit ").append(i).append(": ").append(gameBoard.getNumStones(i)).append(" | ");
        }

        return gameStatus.toString();
    }


    //ADDED METHODS
    private void switchPlayers() {
        if (currentPlayer.equals(playerA)) {
            currentPlayer = playerB;
            playerNum = 2;
            gameBoard.setPlayer(2);
        } else {
            currentPlayer = playerA;
            playerNum = 1;
            gameBoard.setPlayer(1);
        }
    }

    public void setRule(int gameRule) {
        if (gameRule == 1) {
            gameBoard = new KalahRules();
        } else {
            gameBoard = new AyoRules();
        }
    }

    public int getStoreCount(int playerNum) {
       return gameBoard.getStoreCount(playerNum);
    }

    public int getCurrentPlayerNum() {
        return gameBoard.getCurrentPlayerNum();
    }

    public void resetGame() {
        gameBoard.resetBoard();
    }
}


