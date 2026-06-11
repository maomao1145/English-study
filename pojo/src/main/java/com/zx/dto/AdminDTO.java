package com.zx.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private Integer status;
}
