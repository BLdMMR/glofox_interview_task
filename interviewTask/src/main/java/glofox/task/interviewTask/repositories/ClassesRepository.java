package glofox.task.interviewTask.repositories;

import glofox.task.interviewTask.controllers.StatusResponse;
import glofox.task.interviewTask.entities.ClassEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class ClassesRepository {

    //private final String[] demoArray = {"demo", "array", "for", "testing"};

    public StatusResponse<LinkedList<ClassEntity>> getAllClasses() {
        return DatabaseMock.CLASS_DATABASE.isEmpty() ?
                new StatusResponse<>(false, DatabaseMock.CLASS_DATABASE) :
                new StatusResponse<>(true, DatabaseMock.CLASS_DATABASE);
    }

    public ClassEntity getClassByName(String className) {
        return DatabaseMock.classDatabaseContains(className);
    }

    public StatusResponse<String> createClass(ClassEntity classEntity) {
        for (ClassEntity cls : DatabaseMock.CLASS_DATABASE) {
            if (cls.getClassName().equals(classEntity.getClassName())) return new StatusResponse<>(false, "There is a class with that name already");
        }
        DatabaseMock.CLASS_DATABASE.addLast(classEntity);
        return new StatusResponse<>(true, "Success");
    }
}
