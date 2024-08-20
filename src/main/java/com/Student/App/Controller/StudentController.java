package com.Student.App.Controller;

import com.Student.App.DTO.UpdateMarksRequest;
import com.Student.App.Entity.Student;
import com.Student.App.Service.StudentService;
import com.Student.App.Exception.StudentValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        } catch (StudentValidationException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/marks")
    public ResponseEntity<Object> updateMarks(@PathVariable Long id, @Valid @RequestBody UpdateMarksRequest updateMarksRequest) {
        try {
            Student updatedStudent = studentService.updateMarks(id, updateMarksRequest);
            return ResponseEntity.ok(updatedStudent);
        } catch (StudentValidationException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errorResponse.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return ResponseEntity.status(status).body(errorResponse);
    }
}
