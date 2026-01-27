package com.result_portal.dto;

import com.result_portal.entity.Student;
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

    private String semester;

    private double totalObtained;
    private double totalMaxMarks;

    private String grade;
    private String feedback;
    private String passOrFailed;

    private String studentRollNumber;

    private List<SubjectDto> subjectList= new ArrayList<>();
}
