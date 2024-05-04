package mancala;

public class KalahRules extends GameRules {
    private MancalaDataStructure boardData;

    public KalahRules() {
        super();
        this.boardData = getDataStructure();
    }

    @Override
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {
        // Validate the move
        if (boardData.getNumStones(startPit) == 0 || 
            (playerNum == 1 && startPit > 6) || 
            (playerNum == 2 && startPit < 7) ||
            (startPit < 1 || startPit > 12)){
            throw new InvalidMoveException("Invalid move: Starting pit is empty or does not belong to the player.");
            }
        int capturedStones = 0;
        int storeBefore = boardData.getStoreCount(playerNum);
        int lastPit = startPit;

        // Distribute stones
        int stonesDistributed = distributeStones(startPit);

        lastPit = (startPit + stonesDistributed);

        if(playerNum == 1 && lastPit <= 6 || playerNum == 2 && lastPit >= 7 && lastPit <= 12){
                capturedStones = captureStones(lastPit);
        }
        int stonesAddedToStore = boardData.getStoreCount(playerNum) - storeBefore;

       return stonesAddedToStore;
    }
    
    
    @Override
    public int distributeStones(int startPit) {
        int i;
        int startPitStones = boardData.removeStones(startPit);
        int playerNum = getCurrentPlayerNum();
        boardData.setIterator(startPit, playerNum, false);

        for(i = 0; i < startPitStones; i++) {
            Countable currentSpot = boardData.next();
            currentSpot.addStone();
        }

        int stoppingPoint = boardData.getCurrentPit();

        boardData.setStoppingPoint(stoppingPoint); // CONSIDER TAKING OUT
        
        return startPitStones; //CHECK IF RETURN IS CORRECT IDEA
    }

    @Override
    public int captureStones(int stoppingPoint) {
        int capturedStones = 0;
        int oppositePitIndex = 13 - stoppingPoint;
        int lastPitStones = boardData.getNumStones(stoppingPoint);
        
        // Check if the last stone landed in an empty pit (now contains 1 stone)
        if (lastPitStones == 1 ) {
            int stonesInOppositePit = boardData.getNumStones(oppositePitIndex);

            if (stonesInOppositePit > 0 && !isFreeTurn()) {
                // Perform capture
                capturedStones += stonesInOppositePit; // Capture stones from the opposite pit
                boardData.removeStones(oppositePitIndex); // Remove stones from the opposite pit
                boardData.removeStones(stoppingPoint); // Remove the last stone from stopping pit

                // Add the captured stones to the player's store
                int playerStore = stoppingPoint <= 6 ? 1 : 2; // Store index for player 1 is 6, and for player 2 is 13
                boardData.addToStore(playerStore, capturedStones + 1);
            }
        }

        return capturedStones; // Return total number of stones captured
    }

    @Override
    public boolean isFreeTurn() {
        return boardData.isLastStoneinStore();
    }

}



  