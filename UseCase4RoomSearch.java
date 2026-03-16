import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use Case 4: Room Search & Availability Check.
 *
 * Demonstrates read-only access to inventory and room details without
 * mutating system state.
 *
 * @author ZeroTrace7
 * @version 4.0
 */
public class UseCase4RoomSearch {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v4.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 4: Room Search & Availability Check");
        System.out.println();

        Room[] catalog = new Room[] {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        Map<String, Integer> initialAvailability = new HashMap<>();
        initialAvailability.put("Single Room", 2);
        initialAvailability.put("Double Room", 0);
        initialAvailability.put("Suite Room", 1);

        RoomInventory inventory = new RoomInventory(initialAvailability);
        RoomSearchService searchService = new RoomSearchService(inventory);

        List<Room> available = searchService.findAvailableRooms(catalog);

        System.out.println("Available rooms:");
        for (Room room : available) {
            System.out.println("- " + room.getDetails());
        }

        System.out.println();
        System.out.println("Inventory snapshot remains unchanged:");
        printInventory(inventory.getSnapshot());
    }

    private static void printInventory(Map<String, Integer> snapshot) {
        for (Map.Entry<String, Integer> entry : snapshot.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
