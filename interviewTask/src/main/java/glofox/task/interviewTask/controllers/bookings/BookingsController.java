package glofox.task.interviewTask.controllers.bookings;

import glofox.task.interviewTask.controllers.StatusResponse;
import glofox.task.interviewTask.entities.BookingEntity;
import glofox.task.interviewTask.services.BookingsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/classes/{className}/bookings")
public class BookingsController {
    private final BookingsServices services;

    public BookingsController(BookingsServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<LinkedList<BookingEntity>> getAllBookings(@PathVariable("className") String className) {
        StatusResponse<LinkedList<BookingEntity>> status =  services.getAllBookings(className);
        if (status.getSuccess()) {
            return new ResponseEntity<>(status.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(status.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<String> bookClass(@PathVariable("className") String className, @RequestBody BookingInputModel booking) {
        if (booking != null) {
            StatusResponse<String> status = services.bookClass(className, booking);
            if (status.getSuccess()) {
                return new ResponseEntity<>(status.getMessage(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(status.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("No information to book class found", HttpStatus.BAD_REQUEST);
    }



}
