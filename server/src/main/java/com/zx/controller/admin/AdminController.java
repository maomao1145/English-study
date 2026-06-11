package com.zx.controller.admin;

import com.zx.constant.JwtClaimsConstant;
import com.zx.dto.AdminDTO;
import com.zx.dto.AdminLoginDTO;
import com.zx.dto.AdminPageQueryDTO;
import com.zx.entity.Admin;
import com.zx.properties.JwtProperties;
import com.zx.result.PageResult;
import com.zx.result.Result;
import com.zx.service.AdminService;
import com.zx.utils.JwtUtil;
import com.zx.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
@Slf4j
@Api(tags = "用户相关接口")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     * @param adminLoginDTO 登录参数（用户名、密码）
     * @return 统一返回结果，包含 Token 和用户信息
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        // 打印一下日志，方便开发时在控制台排查请求有没有进来
        log.info("用户尝试登录：{}", adminLoginDTO);

        Admin admin = adminService.login(adminLoginDTO);

        //登录成功 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID,admin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
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
    @ApiOperation("根据id查询管理员")
    public Result<UserLoginVO> getById(@PathVariable Long id) {
        log.info("查询管理员详情，id为：{}", id);
        UserLoginVO vo = adminService.getById(id);
        return Result.success(vo);
    }

    @PostMapping
    @ApiOperation("新增管理员")
    public Result save(@RequestBody AdminDTO dto) {
        log.info("新增管理员: {}", dto);
        adminService.save(dto);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("管理员分页查询")
    public Result<PageResult> page(AdminPageQueryDTO dto) {
        log.info("管理员分页查询: {}", dto);
        PageResult result = adminService.pageQuery(dto);
        return Result.success(result);
    }

    @PutMapping
    @ApiOperation("编辑管理员")
    public Result update(@RequestBody AdminDTO dto) {
        log.info("编辑管理员: {}", dto);
        adminService.update(dto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除管理员")
    public Result delete(@PathVariable Long id) {
        log.info("删除管理员: {}", id);
        adminService.deleteById(id);
        return Result.success();
    }
}