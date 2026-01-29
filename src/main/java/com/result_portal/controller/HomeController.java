package com.result_portal.controller;

import com.result_portal.dto.ResultDto;
import com.result_portal.dto.StudentDto;
import com.result_portal.service.ResultService;
import com.result_portal.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {
    private StudentService studentService;
    private ResultService resultService;

    public HomeController(StudentService studentService, ResultService resultService) {
        this.studentService = studentService;
        this.resultService = resultService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/search")
    public String searchResult(@RequestParam String rollNumber,
                               @RequestParam LocalDate dob,
                               Model model
    ){
        try {
            StudentDto student = studentService.getStudentByRollNumberAndDob(rollNumber, dob);
            model.addAttribute("student", student);
            List<ResultDto> results = resultService.getResultsByStudentId(student.getId());
            model.addAttribute("results", results);

            return "view-result";
        }catch (RuntimeException e){
            model.addAttribute("error", "Invalid Roll Number or Date of Birth");
            return "index";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
