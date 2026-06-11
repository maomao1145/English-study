package com.zx.controller.admin;

import com.zx.dto.GradeDTO;
import com.zx.dto.GradePageQueryDTO;
import com.zx.entity.Grade;
import com.zx.result.PageResult;
import com.zx.result.Result;
import com.zx.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/grade")
@Slf4j
@Api(tags = "年级相关接口")
public class GradeContoller {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    @ApiOperation("新增年级")
    public Result save(@RequestBody GradeDTO gradeDTO) {
        log.info("新增年级: {}", gradeDTO);
        gradeService.save(gradeDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("年级分页查询")
    public Result<PageResult> page(GradePageQueryDTO gradePageQueryDTO) {
        log.info("年级分页查询,参数为:{}", gradePageQueryDTO);
        PageResult pageResult = gradeService.pageQuery(gradePageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询年级信息")
    public Result<Grade> getById(@PathVariable Long id) {
        Grade grade = gradeService.getById(id);
        return Result.success(grade);
    }

    @PutMapping
    @ApiOperation("编辑年级信息")
    public Result update(@RequestBody GradeDTO gradeDTO) {
        log.info("编辑年级信息:{}", gradeDTO);
        gradeService.update(gradeDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除年级")
    public Result delete(@PathVariable Long id) {
        log.info("删除年级: {}", id);
        gradeService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用年级")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("启用禁用年级:{},{}", status, id);
        gradeService.startOrStop(status, id);
        return Result.success();
    }
}
