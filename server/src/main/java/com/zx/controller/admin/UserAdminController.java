package com.zx.controller.admin;

import com.zx.dto.UserDTO;
import com.zx.dto.UserPageQueryDTO;
import com.zx.entity.User;
import com.zx.result.PageResult;
import com.zx.result.Result;
import com.zx.service.UserAdminService;
import com.zx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/student")
@Slf4j
@Api(tags = "学生相关接口")

public class UserAdminController {

    @Autowired
    private UserAdminService userAdminService;

    @PostMapping
    public Result save(@RequestBody UserDTO userDTO){
        log.info("新增学生: {}",userDTO);
        userAdminService.save(userDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("学生分类查询")
    public Result<PageResult> page(UserPageQueryDTO userPageQueryDTO){
        log.info("学生分类查询,参数为:{}",userPageQueryDTO);
        PageResult pageResult = userAdminService.pageQuery(userPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据学生id查询学生信息")
    public Result<User> getById(@PathVariable Long id){
        User user= userAdminService.getById(id);
        return Result.success(user);
    }
    @PutMapping
    @ApiOperation("编辑学生信息")
    public Result update(@RequestBody UserDTO userDTO){
        log.info("编辑学生信息:{}",userDTO);
        userAdminService.update(userDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除学生")
    public Result delete(@PathVariable Long id) {
        log.info("删除学生: {}", id);
        userAdminService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用学生账号")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("启用禁用学生账号:{},{}",status,id);
        userAdminService.startOrStop(status,id);
        return Result.success();
    }

}
