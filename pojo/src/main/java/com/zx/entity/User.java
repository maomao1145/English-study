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
public class User implements Serializable {
    private Long id;

    private String username;

    private String name;

    private String password;

    private Integer status;

    private String phone;

    private String sex;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;



}