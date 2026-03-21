package com.result_portal.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    @NotBlank(message = "Subject name is required")
    private String subjectName;

    @NotBlank(message = "Subject code is required")
    private String subjectCode;

    @Min(value = 0, message = "Obtained marks can not be negative")
    private double obtainedMarks;

    @Min(value = 1, message = "Max marks must be at least 1")
    @Max(value = 100, message = "Max marks cannot exceed 100")
    private double maxMarks;


}
