package glofox.task.interviewTask.services;

import glofox.task.interviewTask.controllers.classes.ClassInputModel;
import glofox.task.interviewTask.controllers.StatusResponse;
import glofox.task.interviewTask.entities.ClassEntity;
import glofox.task.interviewTask.repositories.ClassesRepository;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class ClassesServices {
    /**
     * Class that handles data validation
     * */
    private final ClassesRepository repository;

    public ClassesServices(ClassesRepository repository) {
        this.repository = repository;
    }

    /**
     * Service method, for debugging and testing to obtain all available classes on repository.
     * For this particular scenario, no validations need to be done.
     * **/

    public StatusResponse<LinkedList<ClassEntity>> getAllClasses() {
        return repository.getAllClasses();
    }

    public StatusResponse<String> createClass(ClassInputModel classInput) {
        //Field validation
        if (!classInput.getClassName().equals("") &&
            classInput.getStartDate().compareTo(classInput.getEndDate()) < 0 &&
            classInput.getCapacity() > 0
        ) {
            ClassEntity classEntity = classInput.toClassEntity();
            return repository.createClass(classEntity);
        } else {
            return new StatusResponse<>(false, "Invalid input arguments");
        }
    }
}
