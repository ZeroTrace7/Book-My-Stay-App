package usecases;

import app.*;
import java.util.List;

/**
 * Use Case 7: Add-On Service Selection.
 *
 * Demonstrates attaching optional services to reservations
 * without modifying core booking or inventory logic.
 *
 * @author ZeroTrace7
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v7.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 7: Add-On Service Selection");
        System.out.println();

        String reservationId = "RES-501";

        AddOnServiceManager manager = new AddOnServiceManager();
        manager.addService(reservationId, new AddOnService("Airport Pickup", 25.00));
        manager.addService(reservationId, new AddOnService("Breakfast Buffet", 15.50));
        manager.addService(reservationId, new AddOnService("Late Checkout", 10.00));

        List<AddOnService> services = manager.getServices(reservationId);

        System.out.println("Add-on services for reservation " + reservationId + ":");
        for (AddOnService service : services) {
            System.out.println("- " + service.getName() + " ($" + service.getPrice() + ")");
        }

        System.out.println();
        System.out.println("Total add-on cost: $" + manager.getTotalCost(reservationId));
        System.out.println("Core booking and inventory state unchanged.");
    }
}

