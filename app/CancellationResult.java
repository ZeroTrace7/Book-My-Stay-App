package app;

/**
 * Result of a cancellation attempt.
 *
 * @author ZeroTrace7
 * @version 10.0
 */
public class CancellationResult {
    private final boolean cancelled;
    private final String message;

    private CancellationResult(boolean cancelled, String message) {
        this.cancelled = cancelled;
        this.message = message;
    }

    public static CancellationResult cancelled(String message) {
        return new CancellationResult(true, message);
    }

    public static CancellationResult rejected(String message) {
        return new CancellationResult(false, message);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public String getMessage() {
        return message;
    }
}

