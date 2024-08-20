package com.Student.App.Service;

import com.Student.App.DTO.UpdateMarksRequest;
import com.Student.App.Entity.Student;
import com.Student.App.Exception.StudentValidationException;

public interface StudentService {
    Student saveStudent(Student student) throws StudentValidationException;
    Student updateMarks(Long id, UpdateMarksRequest updateMarksRequest) throws StudentValidationException;
}
