package com.zx.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // 方便后端快速构建对象
public class UserLoginVO {
    private Long id;

    private String username;

    private String name;

    private String token; // JWT 令牌传给前端保存
}