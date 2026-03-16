/**
 * Represents a guest's booking intent.
 *
 * @author ZeroTrace7
 * @version 5.0
 */
public class Reservation {
    private final String requestId;
    private final String guestName;
    private final String roomType;
    private final int nights;

    public Reservation(String requestId, String guestName, String roomType, int nights) {
        if (requestId == null || requestId.isBlank()) {
            throw new IllegalArgumentException("requestId is required");
        }
        if (guestName == null || guestName.isBlank()) {
            throw new IllegalArgumentException("guestName is required");
        }
        if (roomType == null || roomType.isBlank()) {
            throw new IllegalArgumentException("roomType is required");
        }
        if (nights <= 0) {
            throw new IllegalArgumentException("nights must be positive");
        }
        this.requestId = requestId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }
}
