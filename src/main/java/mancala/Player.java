package mancala;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String playerName;
    private Store playerStore;
    private UserProfile user;

    public Player() {
        playerStore = new Store();
        user = new UserProfile();
    }

    public Player(String name) {
        playerName = name;
        playerStore = new Store();
        user = new UserProfile();
    }

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        playerName = name;
    }

    public Store getStore() {
        return playerStore;
    }

    public int getStoreCount() {
        return playerStore.getTotalStones();
    }

    public void setStore(Store store) {
        playerStore = store;
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + playerName + '\'' + ", store=" + playerStore +'}';
    }
}
