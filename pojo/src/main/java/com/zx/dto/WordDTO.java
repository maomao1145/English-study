package com.zx.dto;

import lombok.Data;

@Data
public class WordDTO {
    private Long id; // 修改时需要传 id，新增时不需要

    private String word; // 单词

    private String phonetic; // 发音

    private String definition; // 定义

    private String imageUrl; // 图片

    private String exampleSentence; // 例句

    private String exampleTranslation; // 例句翻译

    private Long unitId; // 单元ID
}