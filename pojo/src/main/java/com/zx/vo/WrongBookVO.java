package com.zx.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WrongBookVO {
    private Long id;
    private Long userId;
    private Long wordId;
    private Integer wrongCount;
    private Integer isCorrected;
    private LocalDateTime lastWrong;
    private LocalDateTime createTime;
    // 单词关联信息
    private String word;
    private String phonetic;
    private String definition;
}
