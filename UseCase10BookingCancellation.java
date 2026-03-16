import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 10: Booking Cancellation & Inventory Rollback.
 *
 * Demonstrates safe cancellation with LIFO rollback tracking.
 *
 * @author ZeroTrace7
 * @version 10.0
 */
public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v10.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 10: Booking Cancellation & Inventory Rollback");
        System.out.println();

        Map<String, Integer> initialAvailability = new HashMap<>();
        initialAvailability.put("Single Room", 2);
        initialAvailability.put("Double Room", 1);

        RoomInventory inventory = new RoomInventory(initialAvailability);
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.enqueue(new Reservation("REQ-501", "Aarav", "Single Room", 2));
        queue.enqueue(new Reservation("REQ-502", "Diya", "Double Room", 1));

        RoomAllocationService allocationService = new RoomAllocationService(inventory, queue);
        BookingHistory history = new BookingHistory();

        AllocationResult first = allocationService.allocateNext();
        AllocationResult second = allocationService.allocateNext();

        Reservation firstReservation = first.getReservation();
        Reservation secondReservation = second.getReservation();

        if (firstReservation != null) {
            history.add(firstReservation);
        }
        if (secondReservation != null) {
            history.add(secondReservation);
        }

        CancellationService cancellationService = new CancellationService(allocationService, history);

        System.out.println("Canceling most recent booking:");
        CancellationResult cancelResult = cancellationService.cancel(secondReservation, second.getRoomId());
        System.out.println("- " + cancelResult.getMessage());

        System.out.println();
        System.out.println("Rollback stack (LIFO):");
        Deque<String> rollback = cancellationService.getRollbackSnapshot();
        while (!rollback.isEmpty()) {
            System.out.println("- " + rollback.pop());
        }

        System.out.println();
        System.out.println("Inventory after cancellation:");
        for (Map.Entry<String, Integer> entry : inventory.getSnapshot().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
