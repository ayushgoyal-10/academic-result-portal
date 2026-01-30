package com.result_portal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.result_portal.entity.Gender;
import com.result_portal.entity.Result;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private String name;
    private Long id;
    private String rollNumber;
    private String phoneNumber;

    private String email;
    private String address;
    private String fatherName;
    private String motherName;

    private String gender;

    private String course;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String profilePic;


    private List<ResultDto> results = new ArrayList<>();
}
