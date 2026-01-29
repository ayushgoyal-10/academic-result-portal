package com.result_portal.service.impl;

import com.result_portal.dto.ResultDto;
import com.result_portal.dto.SubjectDto;
import com.result_portal.entity.Result;
import com.result_portal.entity.Student;
import com.result_portal.entity.Subject;
import com.result_portal.repository.ResultRepository;
import com.result_portal.repository.StudentRepository;
import com.result_portal.service.ResultService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

    private ResultRepository resultRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public ResultServiceImpl(ResultRepository resultRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.resultRepository = resultRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultDto createResult(ResultDto resultDto) {
        // first fetching the student if it is existing or not
        Student student = studentRepository.findByRollNumber(resultDto.getStudentRollNumber())
                .orElseThrow(() -> new RuntimeException("Student with Roll no" + resultDto.getStudentRollNumber() + " not found"));

        Result result= new Result();
        result.setFeedback(resultDto.getFeedback());
        result.setSemester(resultDto.getSemester());
        result.setStudent(student);

        double totalObtained=0;
        double totalMax=0;
        boolean failedInAny=false;

        List<Subject> subjectEntities= new ArrayList<>();

        for (SubjectDto subjectDto: resultDto.getSubjectList()){
            Subject subject= modelMapper.map(subjectDto, Subject.class);

            totalObtained+= subject.getObtainedMarks();
            totalMax+= subject.getMaxMarks();

            if((subject.getObtainedMarks()/ subject.getMaxMarks()) < 0.33){
                failedInAny= true;
            }

            subject.setResult(result);
            subjectEntities.add(subject);
        }
        result.setSubjectList(subjectEntities);
        result.setTotalMaxMarks(totalMax);
        result.setTotalObtained(totalObtained);

        // if the totalMax Marks is 0 then its will through an ArithmeticException :
        double percentage= (totalMax>0)? (totalObtained / totalMax) * 100 : 0;
        result.setPassOrFailed(failedInAny? "FAIL" : (percentage >=33)? "PASS": "FAIL");
        result.setGrade(calculateGrade(percentage, failedInAny));

        Result saved = resultRepository.save(result);
        ResultDto mapped = modelMapper.map(saved, ResultDto.class);
        mapped.setStudentRollNumber(saved.getStudent().getRollNumber());
        return mapped;

    }
    private String calculateGrade(double percentage, boolean hasFailed){
        if(hasFailed) return "F";
        if(percentage >=90) return "A+";
        if(percentage >=80) return "A";
        if(percentage >=70) return "B+";
        if(percentage >=60) return "B";
        if(percentage >=50) return "C";
        if(percentage >=33) return "D";
        return "F";
    }

    @Override
    public ResultDto updateResult(Long id, ResultDto resultDto) {
        Result result = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));

        result.setFeedback(resultDto.getFeedback());
        result.setSemester(resultDto.getSemester());

        Result saved = resultRepository.save(result);
        return modelMapper.map(saved, ResultDto.class);
    }

    @Override
    public void deleteResult(Long id) {
        Result result = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        resultRepository.delete(result);
    }

    @Override
    public ResultDto getResultById(Long id) {
        Result result = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        return modelMapper.map(result, ResultDto.class);
    }

    @Override
    public List<ResultDto> getAllResults() {
        List<Result> results = resultRepository.findAll();
        List<ResultDto> resultDtos = results.stream().map(result -> modelMapper.map(result, ResultDto.class)).toList();
        return resultDtos;
    }

    @Override
    public List<ResultDto> getResultsByStudentId(Long id) {
        List<Result> results = resultRepository.findByStudentId(id);
        List<ResultDto> resultDtos = results.stream().map(result -> modelMapper.map(result, ResultDto.class)).toList();
        return resultDtos;
    }
}
