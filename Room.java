/**
 * Abstract base class that defines shared attributes for all room types.
 *
 * Encapsulates common state and enforces a consistent structure for subclasses.
 */
public abstract class Room {
    private final String typeName;
    private final int beds;
    private final int sizeSqm;
    private final double pricePerNight;

    protected Room(String typeName, int beds, int sizeSqm, double pricePerNight) {
        this.typeName = typeName;
        this.beds = beds;
        this.sizeSqm = sizeSqm;
        this.pricePerNight = pricePerNight;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getBeds() {
        return beds;
    }

    public int getSizeSqm() {
        return sizeSqm;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getDetails() {
        return typeName + " | Beds: " + beds + " | Size: " + sizeSqm + " sqm | Price: $" + pricePerNight;
    }
}
