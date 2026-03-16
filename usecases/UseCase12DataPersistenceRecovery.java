package usecases;

import app.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use Case 12: Data Persistence & System Recovery.
 *
 * Demonstrates saving inventory and booking history to a file and
 * restoring them on startup.
 *
 * @author ZeroTrace7
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v12.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 12: Data Persistence & System Recovery");
        System.out.println();

        String filePath = "booking_state.dat";
        PersistenceService persistenceService = new PersistenceService(filePath);

        RoomInventory inventory = new RoomInventory(initialInventory());
        BookingHistory history = new BookingHistory();

        System.out.println("Saving state...");
        history.add(new Reservation("REQ-701", "Aarav", "Single Room", 2));
        history.add(new Reservation("REQ-702", "Diya", "Double Room", 1));
        inventory.updateAvailability("Single Room", -1);

        try {
            PersistenceData data = new PersistenceData(inventory.getSnapshot(), history.getAll());
            persistenceService.save(data);
            System.out.println("State saved to " + filePath);
        } catch (IOException ex) {
            System.out.println("Save failed: " + ex.getMessage());
        }

        System.out.println();
        System.out.println("Simulating restart...");

        RoomInventory recoveredInventory = new RoomInventory(initialInventory());
        BookingHistory recoveredHistory = new BookingHistory();

        try {
            PersistenceData recovered = persistenceService.load();
            recoveredInventory.loadFromSnapshot(recovered.getInventorySnapshot());
            recoveredHistory.loadFrom(recovered.getBookingHistory());
            System.out.println("State restored.");
        } catch (IOException ex) {
            System.out.println("Recovery failed: " + ex.getMessage());
        }

        System.out.println();
        System.out.println("Recovered inventory:");
        for (Map.Entry<String, Integer> entry : recoveredInventory.getSnapshot().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println();
        System.out.println("Recovered booking history:");
        List<Reservation> recoveredBookings = recoveredHistory.getAll();
        for (Reservation reservation : recoveredBookings) {
            System.out.println("- " + reservation.getRequestId()
                    + " | " + reservation.getGuestName()
                    + " | " + reservation.getRoomType()
                    + " | Nights: " + reservation.getNights());
        }
    }

    private static Map<String, Integer> initialInventory() {
        Map<String, Integer> initial = new HashMap<>();
        initial.put("Single Room", 2);
        initial.put("Double Room", 1);
        return initial;
    }
}

