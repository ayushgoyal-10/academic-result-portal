package com.result_portal.service;

import com.result_portal.dto.StudentDto;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
    StudentDto getStudentById(Long id);
    List<StudentDto> getAllStudents();
    StudentDto getStudentByEmail(String email);
    StudentDto getStudentByRollNumberAndDob(String rollNumber, LocalDate dob);
    StudentDto getStudentByRollNumber(String rollNumber);
}
