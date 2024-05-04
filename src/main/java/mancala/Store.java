package mancala;
import java.io.Serializable;

public class Store implements Countable, Serializable {
    //Instance Variables
    private int stones; // Number of stones of store
    private Player storeOwner; // Owner of store (Player A or Player B)

    //Constructor
    public Store(){
        storeOwner = null;
        this.stones = 0;
    }
    @Override
    public void addStones(int amount) {
        if (amount > 0){
            stones+= amount; 
        }
    }
    @Override
    public void addStone() {
        stones++;
    }

    public int emptyStore() {
        int stonesBeforeEmpty = stones;
        stones = 0;
        return stonesBeforeEmpty;
    }

    public Player getOwner() {
        return storeOwner;
    }

    public int getTotalStones() {
        return stones;
    }

    public void setOwner(Player player) {
        storeOwner = player;
    }

    //A3 Additions are the 2 below methods
    public int removeStones() {
        int removedStones = stones;
        stones = 0;
        return stones;
    }

    public int getStoneCount() {
        return stones;
    }

    @Override
    public String toString() {
        return storeOwner.getName() + "'s Store: " + stones + " stones";
    }

}
