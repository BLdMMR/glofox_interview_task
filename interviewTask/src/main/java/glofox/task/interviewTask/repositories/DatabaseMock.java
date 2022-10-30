package glofox.task.interviewTask.repositories;

import glofox.task.interviewTask.entities.BookingEntity;
import glofox.task.interviewTask.entities.ClassEntity;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * Class that simulates the existence of a persistent database
 * **/
public class DatabaseMock {

    public static final LinkedList<ClassEntity> CLASS_DATABASE = new LinkedList<>();

    public static final HashMap<String, LinkedList<BookingEntity>> BOOKINGS_DATABASE = new HashMap<>();

    public static ClassEntity classDatabaseContains(String className) {
        for (ClassEntity classEntity : CLASS_DATABASE) {
            if (classEntity.getClassName().equals(className)) return classEntity;
        }

        return null;
    }

}
