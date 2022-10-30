package glofox.task.interviewTask.controllers.bookings;

import com.fasterxml.jackson.annotation.JsonCreator;
import glofox.task.interviewTask.entities.BookingEntity;

import java.sql.Timestamp;

public class BookingInputModel {
    private final String memberName;
    private final Timestamp bookingTime;

    public @JsonCreator BookingInputModel(String memberName, Timestamp classDate) {
        this.memberName = memberName;
        this.bookingTime = classDate;
        //System.out.println(memberName + classDate);
    }

    public BookingEntity toBookingEntity() {
        if (!memberName.equals("") && bookingTime != null) return new BookingEntity(memberName, bookingTime);
        else return null;
    }

    public String getMemberName() {
        return memberName;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }
}
