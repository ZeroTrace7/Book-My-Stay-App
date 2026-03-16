import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serializable snapshot of inventory and booking history.
 *
 * @author ZeroTrace7
 * @version 12.0
 */
public class PersistenceData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<String, Integer> inventorySnapshot;
    private final List<Reservation> bookingHistory;

    public PersistenceData(Map<String, Integer> inventorySnapshot, List<Reservation> bookingHistory) {
        this.inventorySnapshot = new HashMap<>(inventorySnapshot);
        this.bookingHistory = bookingHistory;
    }

    public Map<String, Integer> getInventorySnapshot() {
        return new HashMap<>(inventorySnapshot);
    }

    public List<Reservation> getBookingHistory() {
        return bookingHistory;
    }
}
