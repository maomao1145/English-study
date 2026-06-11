package com.zx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPoints implements Serializable {
    private Long id;
    private Long userId;
    private Integer points;
    private Integer level;
    private Integer streakDays;
    private LocalDate lastCheckin;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String userName;  // 排行榜使用，非数据库字段
}
