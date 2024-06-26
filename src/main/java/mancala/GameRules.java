package mancala;
import java.io.Serializable;
import java.util.List;
/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable {
    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        gameBoard.setUpPits(); //Maybe move to MancalaGame
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(int pitNum) {
        int start = 0;
        int end = 0;
        int i = 0;
        if (pitNum > 0 && pitNum < 7) {
            start = 1;
            end = 6;
        } else if (pitNum > 6 && pitNum < 13){
            start = 7;
            end = 12;
        }

        for (i = start; i <= end; i++) {
            if(gameBoard.getNumStones(i) != 0) {
                return false;
            }
        }
        return true; 
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     
    this method can be implemented in the abstract class.
    make a new store in this method, set the owner
    then use the setStore(store,playerNum) method of the data structure
    */
    public void registerPlayers(Player one, Player two) {
        Store storeOne = new Store();
        Store storeTwo = new Store();

        storeOne.setOwner(one); 
        storeTwo.setOwner(two);

        one.setStore(storeOne);
        two.setStore(storeTwo);

        gameBoard.setStore(storeOne, 1);
        gameBoard.setStore(storeTwo, 2);

    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    //ADDED B
    public void setUpPits() {
        gameBoard.setUpPits();
    }

    public int getStoreCount(int playerNum) {
        return gameBoard.getStoreCount(playerNum);
    }

    public int getCurrentPlayerNum(){
        return currentPlayer;
    }

    public abstract boolean isFreeTurn();

    @Override
    public String toString() {
        return " ";
        //STUB FIX LATERRRRRRRR
        // Implement toString() method logic here.
    }
}
