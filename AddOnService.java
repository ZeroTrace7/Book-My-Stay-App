/**
 * Represents an optional service add-on for a reservation.
 *
 * @author ZeroTrace7
 * @version 7.0
 */
public class AddOnService {
    private final String name;
    private final double price;

    public AddOnService(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (price < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
