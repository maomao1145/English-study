package com.zx.vo;

import lombok.Data;

@Data
public class WordVO {
    private Long id;
    private String word;
    private String phonetic;
    private String definition;
    private String imageUrl;
    private String exampleSentence;
    private String exampleTranslation;
    private Long unitId;
    private String unitName; // 👈 冗余这一个字段，前端就不用二次查询单元名字了
}