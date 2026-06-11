package com.zx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.dto.GradeDTO;
import com.zx.dto.GradePageQueryDTO;
import com.zx.entity.Grade;
import com.zx.mapper.GradeMapper;
import com.zx.result.PageResult;
import com.zx.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    public void save(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        BeanUtils.copyProperties(gradeDTO, grade);
        gradeMapper.insert(grade);
    }

    public PageResult pageQuery(GradePageQueryDTO gradePageQueryDTO) {
        PageHelper.startPage(gradePageQueryDTO.getPage(), gradePageQueryDTO.getPageSize());
        Page<Grade> page = gradeMapper.pageQuery(gradePageQueryDTO);
        long total = page.getTotal();
        List<Grade> records = page.getResult();
        return new PageResult(total, records);
    }

    public Grade getById(Long id) {
        return gradeMapper.getById(id);
    }

    public void update(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        BeanUtils.copyProperties(gradeDTO, grade);
        gradeMapper.update(grade);
    }

    public void startOrStop(Integer status, Long id) {
        Grade grade = Grade.builder()
                .status(status)
                .id(id)
                .build();
        gradeMapper.update(grade);
    }

    public void deleteById(Long id) {
        gradeMapper.deleteById(id);
    }
}
