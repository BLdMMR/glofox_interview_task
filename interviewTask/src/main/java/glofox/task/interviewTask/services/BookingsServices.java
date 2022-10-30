package glofox.task.interviewTask.services;

import glofox.task.interviewTask.controllers.StatusResponse;
import glofox.task.interviewTask.controllers.bookings.BookingInputModel;
import glofox.task.interviewTask.entities.BookingEntity;
import glofox.task.interviewTask.repositories.BookingsRepository;
import glofox.task.interviewTask.repositories.ClassesRepository;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class BookingsServices {
    private final BookingsRepository bookingsRepository;
    private final ClassesRepository classesRepository;

    public BookingsServices(BookingsRepository bookingsRepository, ClassesRepository classesRepository) {
        this.bookingsRepository = bookingsRepository;
        this.classesRepository = classesRepository;
    }

    public StatusResponse<LinkedList<BookingEntity>> getAllBookings(String className) {
        return bookingsRepository.getAllBookings(className);
    }

    public StatusResponse<String> bookClass(String className, BookingInputModel booking) {
        if (!(booking.getMemberName().equals("")) && booking.getBookingTime() != null) {

            if (classesRepository.getClassByName(className) != null) {
                if (booking.getBookingTime().compareTo(classesRepository.getClassByName(className).getStartDate()) > 0 &&
                    booking.getBookingTime().compareTo(classesRepository.getClassByName(className).getEndDate()) < 0) {
                    return bookingsRepository.addBooking(className, booking.toBookingEntity());
                } else {
                    return new StatusResponse<>(false, "No class with the name " + className + " found at that time");
                }

            } else {
                return new StatusResponse<>(false, "No class with the name " + className + " found");
            }
        } else {
            return new StatusResponse<>(false, "Invalid input arguments");
        }
    }
}
