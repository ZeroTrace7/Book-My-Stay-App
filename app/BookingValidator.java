package app;

import java.util.Map;

/**
 * Validates booking input and inventory constraints.
 *
 * @author ZeroTrace7
 * @version 9.0
 */
public class BookingValidator {
    public void validateReservation(Reservation reservation, RoomInventory inventory) throws InvalidBookingException {
        if (reservation == null) {
            throw new InvalidBookingException("Reservation is required.");
        }
        if (reservation.getGuestName() == null || reservation.getGuestName().isBlank()) {
            throw new InvalidBookingException("Guest name is required.");
        }
        if (reservation.getRoomType() == null || reservation.getRoomType().isBlank()) {
            throw new InvalidBookingException("Room type is required.");
        }
        if (reservation.getNights() <= 0) {
            throw new InvalidBookingException("Nights must be positive.");
        }
        if (inventory == null) {
            throw new InvalidBookingException("Inventory is required.");
        }

        Map<String, Integer> snapshot = inventory.getSnapshot();
        if (!snapshot.containsKey(reservation.getRoomType())) {
            throw new InvalidBookingException("Invalid room type: " + reservation.getRoomType());
        }
        if (inventory.getAvailability(reservation.getRoomType()) <= 0) {
            throw new InvalidBookingException("No availability for " + reservation.getRoomType());
        }
    }

    public void validateInventory(RoomInventory inventory, String roomType, int delta) throws InvalidBookingException {
        if (inventory == null) {
            throw new InvalidBookingException("Inventory is required.");
        }
        if (roomType == null || roomType.isBlank()) {
            throw new InvalidBookingException("Room type is required.");
        }
        int current = inventory.getAvailability(roomType);
        int updated = current + delta;
        if (updated < 0) {
            throw new InvalidBookingException("Inventory cannot be negative for " + roomType);
        }
    }
}

