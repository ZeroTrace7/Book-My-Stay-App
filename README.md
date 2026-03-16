# Book-My-Stay-App

A clean, console-based Hotel Booking Management System built with Core Java and data structures.  
The project grows step by step: each use case adds one concept, so you learn both **how** and **why** a real system evolves.

## Why This Project

- Make data structures feel practical, not abstract
- Show how fair booking and inventory consistency are enforced
- Keep logic deterministic and easy to debug
- Build habits that scale to real production systems

## What You’ll See

- Strong domain modeling with abstraction and inheritance
- Centralized inventory using `HashMap`
- Read-only search that never mutates state
- FIFO request intake with a queue
- Unique allocation using `Set` + `Map`
- Add-on services without touching core booking logic
- Booking history and reporting with ordered storage
- Validation + custom exceptions (fail fast, recover safely)
- Cancellation with LIFO rollback
- Thread-safe booking simulation
- File-based persistence and recovery

## Structure

- `app/` : Core domain, services, and the all‑in‑one runner
- `usecases/` : Individual use case entry points

## Quick Run

All-in-one runner (UC1–UC12):

```bash
javac app/*.java usecases/*.java
java app.BookMyStayAllInOneApp
```

Single use case example:

```bash
javac app/*.java usecases/UseCase4RoomSearch.java
java usecases.UseCase4RoomSearch
```

## Use Cases At a Glance

- UC1: Entry point + welcome output
- UC2: Room types with abstraction/inheritance
- UC3: Centralized inventory with `HashMap`
- UC4: Search available rooms (read-only)
- UC5: FIFO booking request queue
- UC6: Allocation + unique room IDs + inventory sync
- UC7: Add-on services and cost aggregation
- UC8: Booking history and reporting
- UC9: Validation + custom exceptions
- UC10: Cancellation + rollback (LIFO)
- UC11: Thread-safe concurrent booking
- UC12: Persistence + recovery from file

## Contributing

See `GIT_WORKFLOW.md` for the branching and merging workflow used in this repo.
