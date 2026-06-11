package com.zx.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Word {
    private Long id;
    private String word;        // 单词本尊，如 "apple"
    private String phonetic;    // 音标，如 "[ˈæpl]"
    private String definition;  // 中文释义，如 "n. 苹果"
    private String imageUrl;    // 单词配图（小学生记忆非常需要图片）
    private String exampleSentence; // 例句
    private String exampleTranslation; // 例句翻译
    private Long unitId;        // 所属单元ID
    private LocalDateTime updateTime;
}