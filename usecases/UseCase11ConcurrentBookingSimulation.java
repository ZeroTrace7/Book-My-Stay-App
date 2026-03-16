package usecases;

import app.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 11: Concurrent Booking Simulation (Thread Safety).
 *
 * Demonstrates synchronized access to shared queue and inventory.
 *
 * @author ZeroTrace7
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {
        String appName = "Book My Stay App";
        String version = "v11.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 11: Concurrent Booking Simulation");
        System.out.println();

        Map<String, Integer> initialAvailability = new HashMap<>();
        initialAvailability.put("Single Room", 2);
        initialAvailability.put("Double Room", 1);

        RoomInventory inventory = new RoomInventory(initialAvailability);
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.enqueue(new Reservation("REQ-601", "Aarav", "Single Room", 2));
        queue.enqueue(new Reservation("REQ-602", "Diya", "Single Room", 1));
        queue.enqueue(new Reservation("REQ-603", "Meera", "Double Room", 1));
        queue.enqueue(new Reservation("REQ-604", "Vikram", "Single Room", 1));

        RoomAllocationService allocationService = new RoomAllocationService(inventory, queue);
        BookingHistory history = new BookingHistory();

        Thread worker1 = new Thread(new ThreadSafeBookingProcessor(queue, allocationService, history), "Worker-1");
        Thread worker2 = new Thread(new ThreadSafeBookingProcessor(queue, allocationService, history), "Worker-2");

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();

        System.out.println("Confirmed bookings: " + history.size());
        System.out.println("Remaining inventory:");
        for (Map.Entry<String, Integer> entry : inventory.getSnapshot().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

