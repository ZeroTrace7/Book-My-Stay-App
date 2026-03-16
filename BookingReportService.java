import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generates reports from booking history without mutating data.
 *
 * @author ZeroTrace7
 * @version 8.0
 */
public class BookingReportService {
    public void printAll(List<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation reservation : reservations) {
            System.out.println("- " + reservation.getRequestId()
                    + " | " + reservation.getGuestName()
                    + " | " + reservation.getRoomType()
                    + " | Nights: " + reservation.getNights());
        }
    }

    public Map<String, Integer> countByRoomType(List<Reservation> reservations) {
        Map<String, Integer> counts = new HashMap<>();
        if (reservations == null) {
            return counts;
        }

        for (Reservation reservation : reservations) {
            String type = reservation.getRoomType();
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }

        return counts;
    }
}
