import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Processes queued booking requests and assigns unique room IDs.
 *
 * Ensures inventory consistency and prevents double-booking.
 *
 * @author ZeroTrace7
 * @version 6.0
 */
public class RoomAllocationService {
    private final RoomInventory inventory;
    private final BookingRequestQueue queue;
    private final Map<String, Set<String>> allocatedByType = new HashMap<>();
    private final Set<String> allocatedRoomIds = new HashSet<>();
    private final Map<String, Integer> roomTypeCounters = new HashMap<>();

    public RoomAllocationService(RoomInventory inventory, BookingRequestQueue queue) {
        if (inventory == null) {
            throw new IllegalArgumentException("inventory is required");
        }
        if (queue == null) {
            throw new IllegalArgumentException("queue is required");
        }
        this.inventory = inventory;
        this.queue = queue;
    }

    public AllocationResult allocateNext() {
        Reservation reservation = queue.dequeue();
        if (reservation == null) {
            return AllocationResult.rejected("No pending requests.");
        }

        String roomType = reservation.getRoomType();
        int available = inventory.getAvailability(roomType);
        if (available <= 0) {
            return AllocationResult.rejected("Rejected " + reservation.getRequestId()
                    + ": no availability for " + roomType + ".");
        }

        String roomId = generateUniqueRoomId(roomType);
        allocatedRoomIds.add(roomId);
        allocatedByType.computeIfAbsent(roomType, key -> new HashSet<>()).add(roomId);
        inventory.updateAvailability(roomType, -1);

        String message = "Confirmed " + reservation.getRequestId()
                + " -> " + roomId + " (" + roomType + ")";
        return AllocationResult.confirmed(roomId, message);
    }

    public Map<String, Set<String>> getAllocatedSnapshot() {
        Map<String, Set<String>> snapshot = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : allocatedByType.entrySet()) {
            snapshot.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return Collections.unmodifiableMap(snapshot);
    }

    private String generateUniqueRoomId(String roomType) {
        int nextNumber = roomTypeCounters.getOrDefault(roomType, 0) + 1;
        String normalized = roomType.toUpperCase().replace(" ", "-");
        String candidate = normalized + "-" + nextNumber;

        while (allocatedRoomIds.contains(candidate)) {
            nextNumber++;
            candidate = normalized + "-" + nextNumber;
        }

        roomTypeCounters.put(roomType, nextNumber);
        return candidate;
    }
}
