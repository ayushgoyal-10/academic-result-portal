package com.result_portal.service.impl;

import com.result_portal.dto.StudentDto;
import com.result_portal.entity.Student;
import com.result_portal.repository.StudentRepository;
import com.result_portal.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Student save = studentRepository.save(student);
        return modelMapper.map(save, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDto.getName());
        student.setAddress(studentDto.getAddress());
        student.setGender(studentDto.getGender());
        student.setEmail(studentDto.getEmail());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setFatherName(studentDto.getFatherName());
        student.setMotherName(studentDto.getMotherName());
        student.setRollNumber(studentDto.getRollNumber());
        student.setProfilePic(studentDto.getProfilePic());

        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(student);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream().map(student -> modelMapper.map(student, StudentDto.class)).toList();
        return studentDtos;
    }

    @Override
    public StudentDto getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Student not found"));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto getStudentByRollNumberAndDob(String rollNumber, LocalDate dob) {
        Student student = studentRepository.findByRollNumberAndDob(rollNumber, dob).orElseThrow(() -> new RuntimeException("Student not found"));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto getStudentByRollNumber(String rollNumber) {
        Student student = studentRepository.findByRollNumber(rollNumber).orElseThrow(() -> new RuntimeException("Student not found"));
        return modelMapper.map(student, StudentDto.class);
    }
}
