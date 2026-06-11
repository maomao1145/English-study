package com.zx.dto;

import lombok.Data;

@Data
public class WordPageQueryDTO {
    private int page;       // 页码
    private int pageSize;   // 每页条数
    private String word;    // 模糊搜索单词
}