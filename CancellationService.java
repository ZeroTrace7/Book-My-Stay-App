import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles booking cancellations and rollback operations.
 *
 * @author ZeroTrace7
 * @version 10.0
 */
public class CancellationService {
    private final RoomAllocationService allocationService;
    private final BookingHistory history;
    private final Deque<String> rollbackStack = new ArrayDeque<>();
    private final Set<String> cancelledRoomIds = new HashSet<>();

    public CancellationService(RoomAllocationService allocationService, BookingHistory history) {
        if (allocationService == null) {
            throw new IllegalArgumentException("allocationService is required");
        }
        if (history == null) {
            throw new IllegalArgumentException("history is required");
        }
        this.allocationService = allocationService;
        this.history = history;
    }

    public CancellationResult cancel(Reservation reservation, String roomId) {
        if (reservation == null) {
            return CancellationResult.rejected("Reservation is required.");
        }
        if (roomId == null || roomId.isBlank()) {
            return CancellationResult.rejected("Room ID is required.");
        }
        if (cancelledRoomIds.contains(roomId)) {
            return CancellationResult.rejected("Already cancelled: " + roomId);
        }

        boolean released = allocationService.releaseRoom(roomId, reservation.getRoomType());
        if (!released) {
            return CancellationResult.rejected("Cancellation failed: reservation not found.");
        }

        rollbackStack.push(roomId);
        cancelledRoomIds.add(roomId);
        return CancellationResult.cancelled("Cancelled " + reservation.getRequestId() + " -> " + roomId);
    }

    public Deque<String> getRollbackSnapshot() {
        return new ArrayDeque<>(rollbackStack);
    }
}
