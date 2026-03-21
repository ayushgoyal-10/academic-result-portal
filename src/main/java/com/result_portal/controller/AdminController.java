package com.result_portal.controller;

import com.result_portal.dto.ResultDto;
import com.result_portal.dto.StudentDto;
import com.result_portal.service.ResultService;
import com.result_portal.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private StudentService studentService;
    private ResultService resultService;

    public AdminController(StudentService studentService, ResultService resultService) {
        this.studentService = studentService;
        this.resultService = resultService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        return "admin/dashboard";
    }

    @GetMapping("/add-student")
    public String addStudentForm(Model model){
        model.addAttribute("student", new StudentDto());
        return "admin/add-student";
    }

    @PostMapping("/save-student")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentDto); // ← this is the fix
            return "admin/add-student";
        }
        studentService.createStudent(studentDto);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/add-result")
    public String addResultForm(Model model) {
        ResultDto resultDto = new ResultDto();

        model.addAttribute("result", resultDto);
        return "admin/add-result";
    }

    @PostMapping("/save-result")
    public String saveResult(@Valid @ModelAttribute ResultDto resultDto,
                             Model model,
                             BindingResult bindingResult) {
        // if any validation fails, go back to the form — errors show automatically
        if (bindingResult.hasErrors()) {
            return "admin/add-result";
        }
        try {
            resultService.createResult(resultDto);
            return "redirect:/admin/dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("result", resultDto);
            return "admin/add-result";
        }
    }
}
