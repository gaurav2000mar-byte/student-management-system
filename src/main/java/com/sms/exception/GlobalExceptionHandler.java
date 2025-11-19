package com.sms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFoundException ex, WebRequest req){
        ErrorResponse response=ErrorResponse.builder()
                .error("Resource not found")
                .mesaage(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .path(req.getDescription(false))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> badRequestException(BadRequestException ex,WebRequest req){
        ErrorResponse response=ErrorResponse.builder()
                .error("BAD REQUEST")
                .path(req.getDescription(false))
                .mesaage(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<?> methodNotValidException(MethodArgumentNotValidException ex,WebRequest req){

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse response=ErrorResponse.builder()
                .error(errors)
                .path(req.getDescription(false))
                .mesaage(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);
    }

    public ResponseEntity<?> globalExceptionHandler(Exception ex,WebRequest req){
        ErrorResponse response=ErrorResponse.builder()
                .error("INTERNAL SERVER ERROR")
                .mesaage(ex.getMessage())
                .path(req.getDescription(false))
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
