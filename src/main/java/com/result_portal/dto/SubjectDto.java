package com.result_portal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private String subjectName;

    private String subjectCode;

    private double obtainedMarks;

    private double maxMarks;


}
