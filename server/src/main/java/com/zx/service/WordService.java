package com.zx.service;

import com.zx.dto.WordDTO;
import com.zx.dto.WordPageQueryDTO;
import com.zx.entity.Word;
import com.zx.result.PageResult;

import java.util.List;

public interface WordService {

    void save(WordDTO wordDTO);

    PageResult pageQuery(WordPageQueryDTO wordPageQueryDTO);

    Word getById(Long id);

    void update(WordDTO wordDTO);


    List<Word> getByUnitId(Long unitId);

    void deleteById(Long id);

    /**
     * 批量删除单词
     */
//    void deleteBatch(List<Long> ids);
}
