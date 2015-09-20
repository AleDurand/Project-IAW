package project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.exceptions.*;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler({
            EntityNotFoundException.class,
            AssociationNotFoundException.class
    })
    public ResponseEntity<?> handleNotFounds(CustomException e) {
        return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            InvalidEntityConstraintsException.class,
            InvalidIdException.class,
            EntityAlreadyExistsException.class,
            AssociationAlreadyExistsException.class
    })
    public ResponseEntity<?> handleBadRequests(CustomException e) {
        return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }
}
