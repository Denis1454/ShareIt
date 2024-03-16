package ru.practicum.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final NotFoundException e) {
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final ItemOwnerDifferenceException e) {
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }
}

