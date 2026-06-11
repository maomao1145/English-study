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
public class UserBadge implements Serializable {
    private Long id;
    private Long userId;
    private String badgeKey;
    private LocalDateTime earnedTime;
}
