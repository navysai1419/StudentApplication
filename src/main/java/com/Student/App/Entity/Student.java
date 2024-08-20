package com.Student.App.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, message = "First name should have at least 3 characters")
    private String firstName;

    @Column(nullable = false)
    @Size(min = 3, message = "Last name should have at least 3 characters")
    private String lastName;

    @Column(nullable = false)
    @Past(message = "DOB should be in the past")
    private LocalDate dob;

    @Column(nullable = false)
    private String section;

    @Column(nullable = false)
    private String gender;

    @Min(value = 0, message = "Marks should not be less than 0")
    @Max(value = 100, message = "Marks should not be greater than 100")
    private Integer marks1;

    @Min(value = 0, message = "Marks should not be less than 0")
    @Max(value = 100, message = "Marks should not be greater than 100")
    private Integer marks2;

    @Min(value = 0, message = "Marks should not be less than 0")
    @Max(value = 100, message = "Marks should not be greater than 100")
    private Integer marks3;

    private Integer total;

    private Double average;

    private String result;

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMarks1() {
        return marks1;
    }

    public void setMarks1(Integer marks1) {
        this.marks1 = marks1;
    }

    public Integer getMarks2() {
        return marks2;
    }

    public void setMarks2(Integer marks2) {
        this.marks2 = marks2;
    }

    public Integer getMarks3() {
        return marks3;
    }

    public void setMarks3(Integer marks3) {
        this.marks3 = marks3;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @PrePersist
    @PreUpdate
    public void calculateTotalAndResult() {
        if (marks1 != null && marks2 != null && marks3 != null) {
            this.total = marks1 + marks2 + marks3;
            this.average = total / 3.0;
            this.result = (marks1 >= 35 && marks2 >= 35 && marks3 >= 35) ? "Pass" : "Fail";
        } else {
            this.total = null;
            this.average = null;
            this.result = null;
        }
    }

    @AssertTrue(message = "Age should be greater than 15 and less than or equal to 20 years")
    public boolean isValidAge() {
        int age = Period.between(this.dob, LocalDate.now()).getYears();
        return age > 15 && age <= 20;
    }

    @AssertTrue(message = "Valid values for section are A, B, and C")
    public boolean isValidSection() {
        return section.equals("A") || section.equals("B") || section.equals("C");
    }

    @AssertTrue(message = "Valid values for gender are M or F")
    public boolean isValidGender() {
        return gender.equals("M") || gender.equals("F");
    }
}
