package app;

/**
 * Custom exception for invalid booking input or state.
 *
 * @author ZeroTrace7
 * @version 9.0
 */
public class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

