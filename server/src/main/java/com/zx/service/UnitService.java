package com.zx.service;

import com.zx.dto.UnitDTO;
import com.zx.dto.UnitPageQueryDTO;
import com.zx.entity.Unit;

import com.zx.result.PageResult;

import java.util.List;

public interface UnitService {

    void save(UnitDTO unitDTO);

    PageResult pageQuery(UnitPageQueryDTO unitPageQueryDTO);

    Unit getById(Long id);

    void update(UnitDTO unitDTO);

    void deleteById(Long id);


    /**
     * 批量删除单词
     */
//    void deleteBatch(List<Long> ids);
}
