package glofox.task.interviewTask.repositories;

import glofox.task.interviewTask.controllers.StatusResponse;
import glofox.task.interviewTask.entities.BookingEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class BookingsRepository {

    //private final HashMap<String, LinkedList<BookingEntity>> repo = new HashMap<>();

    public StatusResponse<LinkedList<BookingEntity>> getAllBookings(String className) {
        if (DatabaseMock.BOOKINGS_DATABASE.containsKey(className))
            return new StatusResponse<>(true, DatabaseMock.BOOKINGS_DATABASE.get(className));
        else
            return new StatusResponse<>(false, null);
    }

//    public void loadMockData() {
//        repo.put("Pilates", new LinkedList<>());
//        repo.get("Pilates").addLast(new BookingEntity("Emma", new Timestamp(999999999)));
//        repo.get("Pilates").addLast(new BookingEntity("Eliot", new Timestamp(122356432)));
//        repo.get("Pilates").addLast(new BookingEntity("John", new Timestamp(963281093)));
//        repo.put("Yoga", new LinkedList<>());
//        repo.get("Yoga").addLast(new BookingEntity("Eliot", new Timestamp(963281093)));
//        repo.put("Spinning", new LinkedList<>());
//    }

    public StatusResponse<String> addBooking(String className, BookingEntity booking) {
        if (!DatabaseMock.BOOKINGS_DATABASE.containsKey(className)) {
            if (DatabaseMock.classDatabaseContains(className) != null) {
                DatabaseMock.BOOKINGS_DATABASE.put(className, new LinkedList<>());
            } else return new StatusResponse<>(false, "Class does not exist");
        }

        DatabaseMock.BOOKINGS_DATABASE.get(className).addLast(booking);
        return new StatusResponse<>(true, "Class booked successfully");
    }
}
