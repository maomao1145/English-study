package com.zx.controller.user;

import com.zx.constant.JwtClaimsConstant;
import com.zx.dto.UserLoginDTO;
import com.zx.dto.UserDTO;
import com.zx.entity.User;
import com.zx.properties.JwtProperties;
import com.zx.result.Result;
import com.zx.service.UserService;
import com.zx.utils.JwtUtil;
import com.zx.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     * @param userLoginDTO 登录参数（用户名、密码）
     * @return 统一返回结果，包含 Token 和用户信息
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        // 打印一下日志，方便开发时在控制台排查请求有没有进来
        log.info("用户尝试登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        //登录成功 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }

    /**
     * 根据ID查询用户信息（用于前端初始化或个人中心）
     * @param id 用户ID
     * @return 用户安全信息
     */
    @GetMapping("/{id}")
    public Result<UserLoginVO> getById(@PathVariable Long id) {
        log.info("查询用户详情，id为：{}", id);
        UserLoginVO userLoginVO = userService.getById(id);
        return Result.success(userLoginVO);
    }

    @PutMapping("/update")
    @ApiOperation("更新个人信息")
    public Result updateProfile(@RequestBody UserDTO userDTO) {
        log.info("更新个人信息: {}", userDTO);
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.updateProfile(user);
        return Result.success();
    }
}