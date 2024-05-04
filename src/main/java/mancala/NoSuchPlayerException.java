package mancala;

public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException() {
        super("Cannot perform operation: No such player found");
    }
}