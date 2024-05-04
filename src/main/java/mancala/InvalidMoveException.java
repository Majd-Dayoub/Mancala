package mancala;

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("Cannot choose this move: Invalid move");
    }

    public InvalidMoveException(String message) {
        super(message);
    }
}