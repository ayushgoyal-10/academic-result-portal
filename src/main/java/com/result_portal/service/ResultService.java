package com.result_portal.service;

import com.result_portal.dto.ResultDto;

import java.util.List;

public interface ResultService {
    ResultDto createResult(ResultDto resultDto);
    ResultDto updateResult(Long id, ResultDto resultDto);
    void deleteResult(Long id);
    ResultDto getResultById(Long id);
    List<ResultDto> getAllResults();
    List<ResultDto> getResultsByStudentId(Long id);
}
