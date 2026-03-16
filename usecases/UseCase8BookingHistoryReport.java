package usecases;

import app.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use Case 8: Booking History & Reporting.
 *
 * Demonstrates historical tracking of confirmed reservations and
 * read-only reporting.
 *
 * @author ZeroTrace7
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v8.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 8: Booking History & Reporting");
        System.out.println();

        Map<String, Integer> initialAvailability = new HashMap<>();
        initialAvailability.put("Single Room", 2);
        initialAvailability.put("Double Room", 1);

        RoomInventory inventory = new RoomInventory(initialAvailability);

        BookingRequestQueue queue = new BookingRequestQueue();
        queue.enqueue(new Reservation("REQ-301", "Aarav", "Single Room", 2));
        queue.enqueue(new Reservation("REQ-302", "Diya", "Double Room", 1));
        queue.enqueue(new Reservation("REQ-303", "Meera", "Single Room", 1));

        RoomAllocationService allocationService = new RoomAllocationService(inventory, queue);
        BookingHistory history = new BookingHistory();

        AllocationResult result;
        while (true) {
            result = allocationService.allocateNext();
            if (!result.isConfirmed()) {
                if ("No pending requests.".equals(result.getMessage())) {
                    break;
                }
                continue;
            }
            Reservation confirmed = result.getReservation();
            if (confirmed != null) {
                history.add(confirmed);
            }
        }

        System.out.println("Confirmed bookings:");
        BookingReportService reportService = new BookingReportService();
        List<Reservation> confirmed = history.getAll();
        reportService.printAll(confirmed);

        System.out.println();
        System.out.println("Summary by room type:");
        Map<String, Integer> counts = reportService.countByRoomType(confirmed);
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

