package glofox.task.interviewTask.controllers.classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import glofox.task.interviewTask.entities.ClassEntity;

import java.sql.Timestamp;


/**
 * Input model for class creation.
 * This class is going to be built based on an input JSON structure of the request body
 * **/
public class ClassInputModel {
    private final String className;
    private final Timestamp startDate;
    private final Timestamp endDate;
    private final int capacity;

    public @JsonCreator ClassInputModel(String className, Timestamp startDate, Timestamp endDate, int capacity) {
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
    }

    public ClassEntity toClassEntity() {
        if (!className.equals("") && startDate != null && endDate != null && capacity != -1) {
            return new ClassEntity(className, startDate, endDate, capacity);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ClassInputModel{" +
                "className='" + className + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", capacity=" + capacity +
                '}';
    }

    public String getClassName() {
        return className;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public int getCapacity() {
        return capacity;
    }
}
