package com.Student.App.DTO;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateMarksRequest {

    @NotNull(message = "Marks 1 is mandatory")
    @Min(value = 0, message = "Marks 1 must be between 0 and 100")
    @Max(value = 100, message = "Marks 1 must be between 0 and 100")
    private Integer marks1;

    @NotNull(message = "Marks 2 is mandatory")
    @Min(value = 0, message = "Marks 2 must be between 0 and 100")
    @Max(value = 100, message = "Marks 2 must be between 0 and 100")
    private Integer marks2;

    @NotNull(message = "Marks 3 is mandatory")
    @Min(value = 0, message = "Marks 3 must be between 0 and 100")
    @Max(value = 100, message = "Marks 3 must be between 0 and 100")
    private Integer marks3;

    // Getters and Setters

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
}

