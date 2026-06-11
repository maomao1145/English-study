package com.zx.dto;

import lombok.Data;

@Data
public class UnitPageQueryDTO {
    private int page;       // 页码
    private int pageSize;   // 每页条数
    private String name;    // 模糊搜索单元名称
    private String grade;   // 年级筛选
}