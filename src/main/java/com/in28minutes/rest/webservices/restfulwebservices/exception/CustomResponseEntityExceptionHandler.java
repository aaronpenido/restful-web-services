package com.in28minutes.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions (Exception ex, WebRequest request) {
        ExceptionResponse response = createExceptionResponse(
                ex.getMessage(),
                Arrays.asList(request.getDescription(false)));

        return  new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions (UserNotFoundException ex, WebRequest request) {
        ExceptionResponse response = createExceptionResponse(
                ex.getMessage(),
                Arrays.asList(request.getDescription(false)));

        return  new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        final String message = "Invalid argument(s)";
        final List<String> defaultMessageFromValidation = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        ExceptionResponse response = createExceptionResponse(message, defaultMessageFromValidation);

        return  new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse createExceptionResponse(String message, List<String> details) {
        return new ExceptionResponse(
                new Date(),
                message,
                details);
    }
}
