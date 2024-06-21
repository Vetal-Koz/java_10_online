package org.example.final_server.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler
    ResponseEntity<?> handleNotValidDataException(NotValidDataException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorContainer(Collections.singletonList(exception.getMessage())));
    }

    @ExceptionHandler
    ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorContainer(errors));
    }

    @ExceptionHandler
    ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorContainer(Collections.singletonList(exception.getMessage())));
    }

    @ExceptionHandler
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {

        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .toList();
        return ResponseEntity.badRequest().body(new ErrorContainer(errors));
    }

    @ExceptionHandler
    ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String propertyName = exception.getPropertyName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorContainer(Collections.singletonList(propertyName + ": " + exception.getMessage())));
    }

}
