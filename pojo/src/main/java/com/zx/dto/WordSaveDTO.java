package com.zx.dto;

import lombok.Data;

@Data
public class WordSaveDTO {
    private Long id; // 修改时需要传 id，新增时不需要
    private String word;
    private String phonetic;
    private String definition;
    private String imageUrl;
    private String exampleSentence;
    private String exampleTranslation;
    private Long unitId;
}