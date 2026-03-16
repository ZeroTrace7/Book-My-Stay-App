# Book-My-Stay-App

Hotel Booking Management System (Core Java + Data Structures). The app is console-based and deterministic to keep focus on core logic: FIFO request handling, inventory consistency, and uniqueness enforcement to prevent double-booking.

## Run

All-in-one runner (UC1-UC12):

```bash
javac app/*.java usecases/*.java
java app.BookMyStayAllInOneApp
```

Single use case example:

```bash
javac app/*.java usecases/UseCase4RoomSearch.java
java usecases.UseCase4RoomSearch
```

## Project Focus

- FIFO request handling with a queue
- Real-time inventory consistency
- Uniqueness enforcement to prevent double-booking
- Extensible object-oriented design

## Contributing

See `GIT_WORKFLOW.md` for the branching and merging workflow used in this repo.
