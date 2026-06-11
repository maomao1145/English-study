package com.zx.dto;

import lombok.Data;

@Data
public class GradePageQueryDTO {
    private int page;
    private int pageSize;
    private String name;
}
