package app;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * FIFO queue for incoming booking requests.
 *
 * @author ZeroTrace7
 * @version 5.0
 */
public class BookingRequestQueue {
    private final Deque<Reservation> queue = new ArrayDeque<>();

    public void enqueue(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("reservation is required");
        }
        queue.addLast(reservation);
    }

    public Reservation dequeue() {
        return queue.pollFirst();
    }

    public synchronized Reservation dequeueSynchronized() {
        return queue.pollFirst();
    }

    public synchronized void enqueueSynchronized(Reservation reservation) {
        enqueue(reservation);
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

