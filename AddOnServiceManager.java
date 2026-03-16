import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages add-on services attached to reservations.
 *
 * @author ZeroTrace7
 * @version 7.0
 */
public class AddOnServiceManager {
    private final Map<String, List<AddOnService>> servicesByReservation = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        if (reservationId == null || reservationId.isBlank()) {
            throw new IllegalArgumentException("reservationId is required");
        }
        if (service == null) {
            throw new IllegalArgumentException("service is required");
        }
        servicesByReservation
                .computeIfAbsent(reservationId, key -> new ArrayList<>())
                .add(service);
    }

    public List<AddOnService> getServices(String reservationId) {
        List<AddOnService> services = servicesByReservation.get(reservationId);
        if (services == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList<>(services));
    }

    public double getTotalCost(String reservationId) {
        double total = 0.0;
        for (AddOnService service : getServices(reservationId)) {
            total += service.getPrice();
        }
        return total;
    }
}
