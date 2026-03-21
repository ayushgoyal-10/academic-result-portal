package com.result_portal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.result_portal.entity.Gender;
import com.result_portal.entity.Result;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    private Long id;


    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Roll number is required")
    private String rollNumber;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be in 10 digits")
    private String phoneNumber;


    @Email(message = "Enter a valid email")
    private String email;

    @Size(max = 200, message = "Address should be under 200 characters")
    private String address;

    @NotBlank(message = "Father name is required")
    private String fatherName;

    @NotBlank(message = "Mother name is required")
    private String motherName;

    @NotBlank(message = "Select a gender")
    private String gender;

    @NotBlank(message = "Course is required")
    private String course;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    private String profilePic;


    private List<ResultDto> results = new ArrayList<>();
}
