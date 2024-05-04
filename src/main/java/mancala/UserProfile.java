package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private int kalahGamesPlayed;
    private int ayoGamesPlayed;
    private int kalahGamesWon;
    private int ayoGamesWon;

    // Constructors
    public UserProfile() {
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahGamesWon = 0;
        this.ayoGamesWon = 0;
    }

    public UserProfile(String userName) {
        this.userName = userName;
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahGamesWon = 0;
        this.ayoGamesWon = 0;
    }

    // Getters and setters 
    public String setUserName() {
        return userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setKalahGamesPlayed(int kalahGamesPlayed) {
        this.kalahGamesPlayed = kalahGamesPlayed;
    }

    public int getKalahGamesPlayed() {
        return kalahGamesPlayed;
    }

    public void setAyoGamesPlayed(int ayoGamesPlayed) {
        this.ayoGamesPlayed = ayoGamesPlayed;
    }

    public int getAyoGamesPlayed() {
        return ayoGamesPlayed;
    }

    public void setKalahGamesWon(int kalahGamesWon) {
        this.kalahGamesWon = kalahGamesWon;
    }

    public int getKalahGamesWon() {
        return kalahGamesWon;
    }

    public void setAyoGamesWon(int ayoGamesWon) {
        this.ayoGamesWon = ayoGamesWon;
    }

    public int getAyoGamesWon() {
        return ayoGamesWon;
    }

    
}
