package glofox.task.interviewTask.entities;

import java.sql.Timestamp;

public class ClassEntity {
    /**
     * Class that represents the Class entity characterized by a name, start and end dates and a capacity.
     * This assumes that there's only one class of each per day and the capacity represents the daily attendance limit.
     * **/
    private String className;
    private Timestamp startDate;
    private Timestamp endDate;
    private int capacity;

    public ClassEntity(String className, Timestamp startDate, Timestamp endDate, int capacity) {
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
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

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
