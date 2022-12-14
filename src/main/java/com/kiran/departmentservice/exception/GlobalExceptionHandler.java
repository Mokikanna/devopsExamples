package com.kiran.departmentservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

    @ControllerAdvice
    public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        // handling specific exception
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
            ErrorDetails errorDetails =
                    new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }

        // handling global exception

        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
            ErrorDetails errorDetails =
                    new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
            return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // handling Validation
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {

            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) ->{

                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errors.put(fieldName, message);
            });
            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
    }


