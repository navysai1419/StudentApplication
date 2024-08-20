package com.Student.App.Service;

import com.Student.App.DTO.UpdateMarksRequest;
import com.Student.App.Entity.Student;
import com.Student.App.Exception.StudentValidationException;
import com.Student.App.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) throws StudentValidationException {
        validateStudent(student);
        calculateStudentMetrics(student);
        return studentRepository.save(student);
    }

    @Override
    public Student updateMarks(Long id, UpdateMarksRequest updateMarksRequest) throws StudentValidationException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentValidationException("Student not found with id " + id));

        validateMarks(updateMarksRequest);
        student.setMarks1(updateMarksRequest.getMarks1());
        student.setMarks2(updateMarksRequest.getMarks2());
        student.setMarks3(updateMarksRequest.getMarks3());

        calculateStudentMetrics(student);
        return studentRepository.save(student);
    }

    private void validateStudent(Student student) throws StudentValidationException {
        if (student.getFirstName().length() < 3 || student.getLastName().length() < 3) {
            throw new StudentValidationException("First and last name must be at least 3 characters long.");
        }
        if (student.getDob() == null) {
            throw new StudentValidationException("Date of Birth is mandatory.");
        }
        LocalDate today = LocalDate.now();
        int age = Period.between(student.getDob(), today).getYears();
        if (age < 15 || age > 20) {
            throw new StudentValidationException("Age must be between 15 and 20 years.");
        }
        if (!student.getSection().matches("[ABC]")) {
            throw new StudentValidationException("Section must be A, B, or C.");
        }
        if (!student.getGender().matches("[MF]")) {
            throw new StudentValidationException("Gender must be M or F.");
        }
    }

    private void validateMarks(UpdateMarksRequest updateMarksRequest) throws StudentValidationException {
        if (updateMarksRequest.getMarks1() == null || updateMarksRequest.getMarks2() == null || updateMarksRequest.getMarks3() == null) {
            throw new StudentValidationException("Marks 1, Marks 2, and Marks 3 are mandatory.");
        }
        if (updateMarksRequest.getMarks1() < 0 || updateMarksRequest.getMarks1() > 100 ||
                updateMarksRequest.getMarks2() < 0 || updateMarksRequest.getMarks2() > 100 ||
                updateMarksRequest.getMarks3() < 0 || updateMarksRequest.getMarks3() > 100) {
            throw new StudentValidationException("Marks must be between 0 and 100.");
        }
    }

    private void calculateStudentMetrics(Student student) {
        int marks1 = student.getMarks1() != null ? student.getMarks1() : 0;
        int marks2 = student.getMarks2() != null ? student.getMarks2() : 0;
        int marks3 = student.getMarks3() != null ? student.getMarks3() : 0;

        int total = marks1 + marks2 + marks3;
        double average = total / 3.0;
        String result = (marks1 >= 35 && marks2 >= 35 && marks3 >= 35) ? "Pass" : "Fail";

        student.setTotal(total);
        student.setAverage(average);
        student.setResult(result);
    }
}
