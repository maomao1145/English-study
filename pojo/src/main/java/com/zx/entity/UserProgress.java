package com.zx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProgress implements Serializable {
    private Long id;
    private Long userId;
    private Long unitId;
    private Integer totalWords;
    private Integer learnedWords;
    private Integer correctCount;
    private Integer wrongCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
