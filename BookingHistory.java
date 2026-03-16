import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores confirmed reservations in insertion order.
 *
 * @author ZeroTrace7
 * @version 8.0
 */
public class BookingHistory {
    private final List<Reservation> confirmed = new ArrayList<>();

    public void add(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("reservation is required");
        }
        confirmed.add(reservation);
    }

    public List<Reservation> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(confirmed));
    }

    public int size() {
        return confirmed.size();
    }

    public void loadFrom(List<Reservation> reservations) {
        confirmed.clear();
        if (reservations == null) {
            return;
        }
        confirmed.addAll(reservations);
    }
}
