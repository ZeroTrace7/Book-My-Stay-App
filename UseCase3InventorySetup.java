import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 3: Centralized Room Inventory Management.
 *
 * Demonstrates HashMap-based inventory as a single source of truth with
 * controlled update methods.
 *
 * @author ZeroTrace7
 * @version 3.1
 */
public class UseCase3InventorySetup {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v3.1";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 3: Centralized Room Inventory Management");
        System.out.println();

        Map<String, Integer> initialAvailability = new HashMap<>();
        initialAvailability.put("Single Room", 4);
        initialAvailability.put("Double Room", 2);
        initialAvailability.put("Suite Room", 1);

        RoomInventory inventory = new RoomInventory(initialAvailability);

        System.out.println("Initial inventory:");
        printInventory(inventory.getSnapshot());

        System.out.println();
        System.out.println("Updating inventory...");
        inventory.updateAvailability("Single Room", -1);
        inventory.updateAvailability("Suite Room", 1);
        inventory.setAvailability("Double Room", 3);

        System.out.println("Current inventory:");
        printInventory(inventory.getSnapshot());
    }

    private static void printInventory(Map<String, Integer> snapshot) {
        for (Map.Entry<String, Integer> entry : snapshot.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
