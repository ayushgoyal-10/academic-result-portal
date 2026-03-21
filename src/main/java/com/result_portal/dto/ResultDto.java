package com.result_portal.dto;

import com.result_portal.entity.Student;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {

    @NotBlank(message = "Semester is required")
    private String semester;


    private double totalObtained;
    private double totalMaxMarks;

    private String grade;
    private String feedback;
    private String passOrFailed;

    @NotBlank(message = "Student roll number is required")
    private String studentRollNumber;

    @NotEmpty(message = "At least one subject is required")
    @Valid
    private List<SubjectDto> subjectList= new ArrayList<>();
}
