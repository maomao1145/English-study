package com.zx.service;

import com.zx.dto.GradeDTO;
import com.zx.dto.GradePageQueryDTO;
import com.zx.entity.Grade;
import com.zx.result.PageResult;

public interface GradeService {

    void save(GradeDTO gradeDTO);

    PageResult pageQuery(GradePageQueryDTO gradePageQueryDTO);

    Grade getById(Long id);

    void update(GradeDTO gradeDTO);

    void startOrStop(Integer status, Long id);

    void deleteById(Long id);
}
