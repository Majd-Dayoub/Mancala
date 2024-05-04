package mancala;
import java.io.Serializable;
public class Pit implements Countable, Serializable {
    private int pitStones; // Number of stones in pit

    // Constructor
    public Pit() {
        this.pitStones = 0;
    }

    @Override
    public void addStone() {
        pitStones++;
    }

    //A3 Addition
    @Override
    public void addStones(int numToAdd) {
        for (int i = 0; i < numToAdd; i++){
            pitStones++;
        }
    }
    @Override
    public int getStoneCount() {
        return pitStones;
    }
    @Override
    public int removeStones() {
        int removedStones = pitStones;
        pitStones = 0;
        return removedStones;
    }

    @Override
    public String toString() {
        return "Pit: " + pitStones + " stones";
    }
}