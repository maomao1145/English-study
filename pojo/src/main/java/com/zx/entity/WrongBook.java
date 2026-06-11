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
public class WrongBook implements Serializable {
    private Long id;
    private Long userId;
    private Long wordId;
    private Integer wrongCount;
    private Integer isCorrected;
    private LocalDateTime lastWrong;
    private LocalDateTime createTime;
}
