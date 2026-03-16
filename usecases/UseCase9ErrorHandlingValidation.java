package usecases;

import app.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 9: Error Handling & Validation.
 *
 * Demonstrates custom exceptions and fail-fast validation.
 *
 * @author ZeroTrace7
 * @version 9.0
 */
public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v9.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 9: Error Handling & Validation");
        System.out.println();

        Map<String, Integer> initialAvailability = new HashMap<>();
        initialAvailability.put("Single Room", 1);
        initialAvailability.put("Double Room", 0);

        RoomInventory inventory = new RoomInventory(initialAvailability);
        BookingValidator validator = new BookingValidator();

        Reservation valid = new Reservation("REQ-401", "Aarav", "Single Room", 2);
        Reservation invalidRoom = new Reservation("REQ-402", "Diya", "Penthouse", 1);
        Reservation unavailable = new Reservation("REQ-403", "Meera", "Double Room", 1);

        processReservation(valid, inventory, validator);
        processReservation(invalidRoom, inventory, validator);
        processReservation(unavailable, inventory, validator);

        System.out.println();
        System.out.println("System continues running safely after errors.");
    }

    private static void processReservation(Reservation reservation, RoomInventory inventory, BookingValidator validator) {
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
}

