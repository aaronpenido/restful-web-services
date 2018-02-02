package com.in28minutes.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions (Exception ex, WebRequest request) {
        ExceptionResponse response = createExceptionResponse(request, ex.getMessage());

        return  new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions (UserNotFoundException ex, WebRequest request) {
        ExceptionResponse response = createExceptionResponse(request, ex.getMessage());

        return  new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse createExceptionResponse(WebRequest request, String message) {
        return new ExceptionResponse(
                new Date(),
                message,
                request.getDescription(false));
    }
}
