package app;

/**
 * Processes booking requests with synchronized access to shared state.
 *
 * @author ZeroTrace7
 * @version 11.0
 */
public class ThreadSafeBookingProcessor implements Runnable {
    private final BookingRequestQueue queue;
    private final RoomAllocationService allocationService;
    private final BookingHistory history;

    public ThreadSafeBookingProcessor(BookingRequestQueue queue,
                                      RoomAllocationService allocationService,
                                      BookingHistory history) {
        this.queue = queue;
        this.allocationService = allocationService;
        this.history = history;
    }

    @Override
    public void run() {
        while (true) {
            Reservation reservation = queue.dequeueSynchronized();

            if (reservation == null) {
                break;
            }

            AllocationResult result = allocationService.allocateReservation(reservation);

            if (result.isConfirmed() && result.getReservation() != null) {
                synchronized (history) {
                    history.add(result.getReservation());
                }
            }
        }
    }
}

