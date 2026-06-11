package com.zx.dto;

import lombok.Data;

@Data
public class AdminPageQueryDTO {
    private int page;
    private int pageSize;
    private String name;
}
