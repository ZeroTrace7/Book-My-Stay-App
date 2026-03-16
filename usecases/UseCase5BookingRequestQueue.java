package usecases;

import app.*;
/**
 * Use Case 5: Booking Request (First-Come-First-Served).
 *
 * Demonstrates FIFO request intake without inventory mutation.
 *
 * @author ZeroTrace7
 * @version 5.0
 */
public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        String appName = "Book My Stay App";
        String version = "v5.0";

        System.out.println("Welcome to " + appName + " " + version);
        System.out.println("Use Case 5: Booking Request Queue (FCFS)");
        System.out.println();

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.enqueue(new Reservation("REQ-101", "Aarav", "Single Room", 2));
        requestQueue.enqueue(new Reservation("REQ-102", "Diya", "Double Room", 1));
        requestQueue.enqueue(new Reservation("REQ-103", "Meera", "Suite Room", 3));

        System.out.println("Requests received: " + requestQueue.size());
        System.out.println("Order preserved (FIFO):");

        while (!requestQueue.isEmpty()) {
            Reservation next = requestQueue.dequeue();
            System.out.println("- " + next.getRequestId()
                    + " | " + next.getGuestName()
                    + " | " + next.getRoomType()
                    + " | Nights: " + next.getNights());
        }

        System.out.println();
        System.out.println("No inventory updates performed at this stage.");
    }
}

