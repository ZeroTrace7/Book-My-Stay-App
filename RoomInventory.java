import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Centralized inventory store for room availability.
 *
 * Encapsulates availability operations and provides a single source of truth.
 *
 * @author ZeroTrace7
 * @version 3.0
 */
public class RoomInventory {
    private final Map<String, Integer> availability;

    public RoomInventory(Map<String, Integer> initialAvailability) {
        if (initialAvailability == null) {
            throw new IllegalArgumentException("initialAvailability is required");
        }
        this.availability = new HashMap<>();
        for (Map.Entry<String, Integer> entry : initialAvailability.entrySet()) {
            setAvailability(entry.getKey(), entry.getValue());
        }
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public void setAvailability(String roomType, int count) {
        if (roomType == null || roomType.isBlank()) {
            throw new IllegalArgumentException("roomType is required");
        }
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
        availability.put(roomType, count);
    }

    public void updateAvailability(String roomType, int delta) {
        int current = getAvailability(roomType);
        int updated = current + delta;
        if (updated < 0) {
            throw new IllegalArgumentException("availability cannot be negative");
        }
        availability.put(roomType, updated);
    }

    public Map<String, Integer> getSnapshot() {
        return Collections.unmodifiableMap(new HashMap<>(availability));
    }

    public void loadFromSnapshot(Map<String, Integer> snapshot) {
        availability.clear();
        if (snapshot == null) {
            return;
        }
        for (Map.Entry<String, Integer> entry : snapshot.entrySet()) {
            setAvailability(entry.getKey(), entry.getValue());
        }
    }
}
