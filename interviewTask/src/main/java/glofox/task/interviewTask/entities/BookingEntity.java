package glofox.task.interviewTask.entities;

import java.sql.Timestamp;

public class BookingEntity {
    private String memberName;
    private Timestamp bookingTime;

    public BookingEntity(String memberName, Timestamp bookingTime) {
        this.memberName = memberName;
        this.bookingTime = bookingTime;
    }

    public String getMemberName() {
        return memberName;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "memberName='" + memberName + '\'' +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
