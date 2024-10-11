package com.ReacconMind.ReacconMind.exception;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleException(NoSuchElementException e) {
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested item is not registered");
        return new ResponseEntity<>(
            "The requested item is not registered",
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested item is not registered");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException e) {
        return new ResponseEntity<>(
            e.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(
            "An unexpected error occurred",
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleException(NullPointerException e) {
        return new ResponseEntity<>(
            "Null pointer exception occurred",
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<?> handleException(ArrayIndexOutOfBoundsException e) {
        return new ResponseEntity<>(
            "Array index out of bounds",
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
