package com.zx.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Unit {
    private Long id;
    private String name;        // 单元名称（如：Unit 1 Hello!）
    private String grade;       // 年级（如：三年级上）
    private Integer wordCount;  // 单词总数
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}