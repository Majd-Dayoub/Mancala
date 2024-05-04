package mancala;

public class PitNotFoundException extends Exception {
    public PitNotFoundException() {
        super("Invalid pit number. Please enter a number between 1 and 12.");
    }

    public PitNotFoundException(String message) {
        super(message);
    }
}