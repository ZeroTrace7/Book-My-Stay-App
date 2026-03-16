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
    private final Reservation reservation;

    private AllocationResult(boolean confirmed, String message, String roomId, Reservation reservation) {
        this.confirmed = confirmed;
        this.message = message;
        this.roomId = roomId;
        this.reservation = reservation;
    }

    public static AllocationResult confirmed(String roomId, String message, Reservation reservation) {
        return new AllocationResult(true, message, roomId, reservation);
    }

    public static AllocationResult rejected(String message) {
        return new AllocationResult(false, message, null, null);
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

    public Reservation getReservation() {
        return reservation;
    }
}
