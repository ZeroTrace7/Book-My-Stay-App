/**
 * Use Case 2: Basic Room Types & Static Availability.
 *
 * Demonstrates abstraction, inheritance, and polymorphism for room modeling,
 * while keeping availability in simple variables.
 *
 * @author ZeroTrace7
 * @version 1.0
 */
public class UseCase2HotelBookingApp {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v1.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 2: Room Types & Static Availability");
        System.out.println();

        Room[] rooms = new Room[] {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        int singleAvailable = 3;
        int doubleAvailable = 2;
        int suiteAvailable = 1;

        System.out.println("Room Catalog:");
        for (Room room : rooms) {
            System.out.println("- " + room.getDetails());
        }

        System.out.println();
        System.out.println("Availability (static variables):");
        System.out.println("Single Room: " + singleAvailable);
        System.out.println("Double Room: " + doubleAvailable);
        System.out.println("Suite Room: " + suiteAvailable);
    }
}
