package com.Student.App.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentValidationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleStudentValidationException(StudentValidationException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("status", "error", "message", ex.getMessage()));
    }
}
