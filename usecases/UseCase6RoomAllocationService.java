package usecases;

import app.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation.
 *
 * Demonstrates FIFO request processing, unique room ID assignment,
 * and immediate inventory updates.
 *
 * @author ZeroTrace7
 * @version 6.0
 */
public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v6.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 6: Reservation Confirmation & Room Allocation");
        System.out.println();

        Map<String, Integer> initialAvailability = new HashMap<>();
        initialAvailability.put("Single Room", 2);
        initialAvailability.put("Double Room", 1);
        initialAvailability.put("Suite Room", 1);

        RoomInventory inventory = new RoomInventory(initialAvailability);

        BookingRequestQueue queue = new BookingRequestQueue();
        queue.enqueue(new Reservation("REQ-201", "Aarav", "Single Room", 2));
        queue.enqueue(new Reservation("REQ-202", "Diya", "Single Room", 1));
        queue.enqueue(new Reservation("REQ-203", "Vikram", "Single Room", 1));
        queue.enqueue(new Reservation("REQ-204", "Meera", "Suite Room", 3));

        RoomAllocationService allocationService = new RoomAllocationService(inventory, queue);

        System.out.println("Processing requests (FIFO):");
        AllocationResult result;
        while (true) {
            result = allocationService.allocateNext();
            System.out.println("- " + result.getMessage());
            if (!result.isConfirmed() && "No pending requests.".equals(result.getMessage())) {
                break;
            }
        }

        System.out.println();
        System.out.println("Allocated room IDs:");
        printAllocated(allocationService.getAllocatedSnapshot());

        System.out.println();
        System.out.println("Remaining inventory:");
        printInventory(inventory.getSnapshot());
    }

    private static void printAllocated(Map<String, Set<String>> allocatedByType) {
        if (allocatedByType.isEmpty()) {
            System.out.println("None");
            return;
        }
        for (Map.Entry<String, Set<String>> entry : allocatedByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void printInventory(Map<String, Integer> snapshot) {
        for (Map.Entry<String, Integer> entry : snapshot.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

