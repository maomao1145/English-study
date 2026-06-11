package com.zx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
    private Long id;

    private String name;

    private Integer status;

    private String gradeId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;



}