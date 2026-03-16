package app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unified runner that demonstrates all use cases (1-12) in sequence.
 *
 * @author ZeroTrace7
 * @version 12.0
 */
public class BookMyStayAllInOneApp {
    public static void main(String[] args) throws InterruptedException {
        header("Book My Stay App", "All Use Cases (1-12)");

        runUseCase1();
        runUseCase2();
        runUseCase3();
        runUseCase4();
        runUseCase5();
        runUseCase6();
        runUseCase7();
        runUseCase8();
        runUseCase9();
        runUseCase10();
        runUseCase11();
        runUseCase12();

        header("End", "All use cases completed");
    }

    private static void runUseCase1() {
        header("UC1", "Application Entry & Welcome Message");
        System.out.println("Welcome to Book My Stay App v1.0");
    }

    private static void runUseCase2() {
        header("UC2", "Basic Room Types & Static Availability");
        Room[] rooms = new Room[] { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };
        int singleAvailable = 3;
        int doubleAvailable = 2;
        int suiteAvailable = 1;

        System.out.println("Room Catalog:");
        for (Room room : rooms) {
            System.out.println("- " + room.getDetails());
        }
        System.out.println("Availability (static variables):");
        System.out.println("Single Room: " + singleAvailable);
        System.out.println("Double Room: " + doubleAvailable);
        System.out.println("Suite Room: " + suiteAvailable);
    }

    private static void runUseCase3() {
        header("UC3", "Centralized Room Inventory (HashMap)");
        Map<String, Integer> initial = new HashMap<>();
        initial.put("Single Room", 4);
        initial.put("Double Room", 2);
        initial.put("Suite Room", 1);

        RoomInventory inventory = new RoomInventory(initial);
        System.out.println("Initial inventory:");
        printInventory(inventory.getSnapshot());

        inventory.updateAvailability("Single Room", -1);
        inventory.setAvailability("Double Room", 3);
        System.out.println("Updated inventory:");
        printInventory(inventory.getSnapshot());
    }

    private static void runUseCase4() {
        header("UC4", "Room Search & Availability Check");
        Room[] catalog = new Room[] { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };
        Map<String, Integer> initial = new HashMap<>();
        initial.put("Single Room", 2);
        initial.put("Double Room", 0);
        initial.put("Suite Room", 1);

        RoomInventory inventory = new RoomInventory(initial);
        RoomSearchService searchService = new RoomSearchService(inventory);
        List<Room> available = searchService.findAvailableRooms(catalog);

        System.out.println("Available rooms:");
        for (Room room : available) {
            System.out.println("- " + room.getDetails());
        }
        System.out.println("Inventory unchanged:");
        printInventory(inventory.getSnapshot());
    }

    private static void runUseCase5() {
        header("UC5", "Booking Request Queue (FIFO)");
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.enqueue(new Reservation("REQ-101", "Aarav", "Single Room", 2));
        queue.enqueue(new Reservation("REQ-102", "Diya", "Double Room", 1));
        queue.enqueue(new Reservation("REQ-103", "Meera", "Suite Room", 3));

        System.out.println("Order preserved (FIFO):");
        while (!queue.isEmpty()) {
            Reservation next = queue.dequeue();
            System.out.println("- " + next.getRequestId()
                    + " | " + next.getGuestName()
                    + " | " + next.getRoomType()
                    + " | Nights: " + next.getNights());
        }
    }

    private static void runUseCase6() {
        header("UC6", "Reservation Confirmation & Room Allocation");
        Map<String, Integer> initial = new HashMap<>();
        initial.put("Single Room", 2);
        initial.put("Double Room", 1);
        initial.put("Suite Room", 1);

        RoomInventory inventory = new RoomInventory(initial);
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.enqueue(new Reservation("REQ-201", "Aarav", "Single Room", 2));
        queue.enqueue(new Reservation("REQ-202", "Diya", "Single Room", 1));
        queue.enqueue(new Reservation("REQ-203", "Vikram", "Single Room", 1));
        queue.enqueue(new Reservation("REQ-204", "Meera", "Suite Room", 3));

        RoomAllocationService allocation = new RoomAllocationService(inventory, queue);
        AllocationResult result;
        while (true) {
            result = allocation.allocateNext();
            System.out.println("- " + result.getMessage());
            if (!result.isConfirmed() && "No pending requests.".equals(result.getMessage())) {
                break;
            }
        }
        System.out.println("Remaining inventory:");
        printInventory(inventory.getSnapshot());
    }

    private static void runUseCase7() {
        header("UC7", "Add-On Service Selection");
        String reservationId = "RES-501";
        AddOnServiceManager manager = new AddOnServiceManager();
        manager.addService(reservationId, new AddOnService("Airport Pickup", 25.00));
        manager.addService(reservationId, new AddOnService("Breakfast Buffet", 15.50));
        manager.addService(reservationId, new AddOnService("Late Checkout", 10.00));

        List<AddOnService> services = manager.getServices(reservationId);
        for (AddOnService service : services) {
            System.out.println("- " + service.getName() + " ($" + service.getPrice() + ")");
        }
        System.out.println("Total add-on cost: $" + manager.getTotalCost(reservationId));
    }

    private static void runUseCase8() {
        header("UC8", "Booking History & Reporting");
        BookingHistory history = new BookingHistory();
        history.add(new Reservation("REQ-301", "Aarav", "Single Room", 2));
        history.add(new Reservation("REQ-302", "Diya", "Double Room", 1));
        history.add(new Reservation("REQ-303", "Meera", "Single Room", 1));

        BookingReportService report = new BookingReportService();
        System.out.println("Confirmed bookings:");
        report.printAll(history.getAll());
        System.out.println("Summary by room type:");
        Map<String, Integer> counts = report.countByRoomType(history.getAll());
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void runUseCase9() {
        header("UC9", "Error Handling & Validation");
        Map<String, Integer> initial = new HashMap<>();
        initial.put("Single Room", 1);
        initial.put("Double Room", 0);
        RoomInventory inventory = new RoomInventory(initial);
        BookingValidator validator = new BookingValidator();

        Reservation valid = new Reservation("REQ-401", "Aarav", "Single Room", 2);
        Reservation invalidRoom = new Reservation("REQ-402", "Diya", "Penthouse", 1);
        Reservation unavailable = new Reservation("REQ-403", "Meera", "Double Room", 1);

        processReservation(valid, inventory, validator);
        processReservation(invalidRoom, inventory, validator);
        processReservation(unavailable, inventory, validator);
    }

    private static void runUseCase10() {
        header("UC10", "Booking Cancellation & Inventory Rollback");
        Map<String, Integer> initial = new HashMap<>();
        initial.put("Single Room", 2);
        initial.put("Double Room", 1);

        RoomInventory inventory = new RoomInventory(initial);
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.enqueue(new Reservation("REQ-501", "Aarav", "Single Room", 2));
        queue.enqueue(new Reservation("REQ-502", "Diya", "Double Room", 1));

        RoomAllocationService allocation = new RoomAllocationService(inventory, queue);
        BookingHistory history = new BookingHistory();

        AllocationResult first = allocation.allocateNext();
        AllocationResult second = allocation.allocateNext();
        if (first.isConfirmed() && first.getReservation() != null) {
            history.add(first.getReservation());
        }
        if (second.isConfirmed() && second.getReservation() != null) {
            history.add(second.getReservation());
        }

        CancellationService cancellation = new CancellationService(allocation, history);
        CancellationResult cancelResult = cancellation.cancel(second.getReservation(), second.getRoomId());
        System.out.println("- " + cancelResult.getMessage());
        System.out.println("Inventory after cancellation:");
        printInventory(inventory.getSnapshot());
    }

    private static void runUseCase11() throws InterruptedException {
        header("UC11", "Concurrent Booking Simulation (Thread Safety)");
        Map<String, Integer> initial = new HashMap<>();
        initial.put("Single Room", 2);
        initial.put("Double Room", 1);

        RoomInventory inventory = new RoomInventory(initial);
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.enqueue(new Reservation("REQ-601", "Aarav", "Single Room", 2));
        queue.enqueue(new Reservation("REQ-602", "Diya", "Single Room", 1));
        queue.enqueue(new Reservation("REQ-603", "Meera", "Double Room", 1));
        queue.enqueue(new Reservation("REQ-604", "Vikram", "Single Room", 1));

        RoomAllocationService allocation = new RoomAllocationService(inventory, queue);
        BookingHistory history = new BookingHistory();

        Thread worker1 = new Thread(new ThreadSafeBookingProcessor(queue, allocation, history), "Worker-1");
        Thread worker2 = new Thread(new ThreadSafeBookingProcessor(queue, allocation, history), "Worker-2");
        worker1.start();
        worker2.start();
        worker1.join();
        worker2.join();

        System.out.println("Confirmed bookings: " + history.size());
        System.out.println("Remaining inventory:");
        printInventory(inventory.getSnapshot());
    }

    private static void runUseCase12() {
        header("UC12", "Data Persistence & System Recovery");
        String filePath = "booking_state.dat";
        PersistenceService persistence = new PersistenceService(filePath);

        RoomInventory inventory = new RoomInventory(initialInventory());
        BookingHistory history = new BookingHistory();
        history.add(new Reservation("REQ-701", "Aarav", "Single Room", 2));
        history.add(new Reservation("REQ-702", "Diya", "Double Room", 1));
        inventory.updateAvailability("Single Room", -1);

        try {
            persistence.save(new PersistenceData(inventory.getSnapshot(), history.getAll()));
            System.out.println("State saved to " + filePath);
        } catch (IOException ex) {
            System.out.println("Save failed: " + ex.getMessage());
        }

        RoomInventory recoveredInventory = new RoomInventory(initialInventory());
        BookingHistory recoveredHistory = new BookingHistory();
        try {
            PersistenceData recovered = persistence.load();
            recoveredInventory.loadFromSnapshot(recovered.getInventorySnapshot());
            recoveredHistory.loadFrom(recovered.getBookingHistory());
            System.out.println("State restored.");
        } catch (IOException ex) {
            System.out.println("Recovery failed: " + ex.getMessage());
        }

        System.out.println("Recovered inventory:");
        printInventory(recoveredInventory.getSnapshot());
        System.out.println("Recovered booking history:");
        for (Reservation reservation : recoveredHistory.getAll()) {
            System.out.println("- " + reservation.getRequestId()
                    + " | " + reservation.getGuestName()
                    + " | " + reservation.getRoomType()
                    + " | Nights: " + reservation.getNights());
        }
    }

    private static void processReservation(Reservation reservation,
                                           RoomInventory inventory,
                                           BookingValidator validator) {
        try {
            validator.validateReservation(reservation, inventory);
            inventory.updateAvailability(reservation.getRoomType(), -1);
            System.out.println("Confirmed " + reservation.getRequestId()
                    + " for " + reservation.getGuestName());
        } catch (InvalidBookingException ex) {
            System.out.println("Failed " + reservation.getRequestId() + ": " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Failed " + reservation.getRequestId() + ": " + ex.getMessage());
        }
    }

    private static Map<String, Integer> initialInventory() {
        Map<String, Integer> initial = new HashMap<>();
        initial.put("Single Room", 2);
        initial.put("Double Room", 1);
        return initial;
    }

    private static void printInventory(Map<String, Integer> snapshot) {
        for (Map.Entry<String, Integer> entry : snapshot.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void header(String title, String subtitle) {
        System.out.println();
        System.out.println("=== " + title + " ===");
        System.out.println(subtitle);
        System.out.println();
    }
}

