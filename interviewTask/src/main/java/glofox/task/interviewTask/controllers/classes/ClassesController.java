package glofox.task.interviewTask.controllers.classes;

import glofox.task.interviewTask.controllers.StatusResponse;
import glofox.task.interviewTask.entities.ClassEntity;
import glofox.task.interviewTask.services.ClassesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

/**
 * Controller that handles HTTP requests and responses related to classes
 * **/
@RestController
@RequestMapping("/classes")
public class ClassesController {
    private final ClassesServices services;

    public ClassesController(ClassesServices services) {
        this.services = services;
    }

    /**
     * Handler method, for debugging and testing to obtain all available classes on repository
     * **/
    @GetMapping
    public ResponseEntity<LinkedList<ClassEntity>> getAllClasses() {
        StatusResponse<LinkedList<ClassEntity>> status = services.getAllClasses();

        if (status.getSuccess()) {
            return new ResponseEntity<>(status.getMessage(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(status.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Handler method for creating a new class. This method takes an input model defined in the class ClassInputModel.
     * This input model builds an instance from a json structure containing the class name, the start and end dates and the class capacity;
     *
     **/
    @PostMapping
    public ResponseEntity<String> createClass(@RequestBody ClassInputModel classInput) {
        StatusResponse<String> status;

        if (classInput != null) {
            status = services.createClass(classInput);
        } else {
            status = new StatusResponse<>(false, "No input arguments found");
        }

        if (status.getSuccess()) {
            return new ResponseEntity<>(status.getMessage(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(status.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
