package com.zx.dto;

import lombok.Data;

@Data
public class UserPageQueryDTO {
    private int page;       // 页码
    private int pageSize;   // 每页条数
    private String name;    // 模糊搜索学生姓名
}