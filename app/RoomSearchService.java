package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Read-only search service for available rooms.
 *
 * Keeps search logic separate from inventory mutation.
 *
 * @author ZeroTrace7
 * @version 4.0
 */
public class RoomSearchService {
    private final RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("inventory is required");
        }
        this.inventory = inventory;
    }

    public List<Room> findAvailableRooms(Room[] catalog) {
        if (catalog == null) {
            throw new IllegalArgumentException("catalog is required");
        }

        Map<String, Integer> snapshot = inventory.getSnapshot();
        List<Room> available = new ArrayList<>();

        for (Room room : catalog) {
            if (room == null) {
                continue;
            }
            int count = snapshot.getOrDefault(room.getTypeName(), 0);
            if (count > 0) {
                available.add(room);
            }
        }

        return available;
    }
}

