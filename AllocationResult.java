/**
 * Result of a room allocation attempt.
 *
 * @author ZeroTrace7
 * @version 6.0
 */
public class AllocationResult {
    private final boolean confirmed;
    private final String message;
    private final String roomId;

    private AllocationResult(boolean confirmed, String message, String roomId) {
        this.confirmed = confirmed;
        this.message = message;
        this.roomId = roomId;
    }

    public static AllocationResult confirmed(String roomId, String message) {
        return new AllocationResult(true, message, roomId);
    }

    public static AllocationResult rejected(String message) {
        return new AllocationResult(false, message, null);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getMessage() {
        return message;
    }

    public String getRoomId() {
        return roomId;
    }
}
