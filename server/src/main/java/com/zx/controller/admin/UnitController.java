package com.zx.controller.admin;


import com.zx.dto.UnitPageQueryDTO;
import com.zx.dto.UnitDTO;
import com.zx.entity.Unit;
import com.zx.result.PageResult;
import com.zx.result.Result;
import com.zx.service.UnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/unit")
@Slf4j
@Api(tags = "单元相关接口")

public class UnitController {

    @Autowired
    private UnitService unitService;

    @PostMapping
    public Result save(@RequestBody UnitDTO unitDTO){
        log.info("新增单元: {}",unitDTO);
        unitService.save(unitDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("单元分类查询")
    public Result<PageResult> page(UnitPageQueryDTO unitPageQueryDTO){
        log.info("单元分类查询,参数为:{}",unitPageQueryDTO);
        PageResult pageResult = unitService.pageQuery(unitPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据单元id查询单元信息")
    public Result<Unit> getById(@PathVariable Long id){
        Unit unit= unitService.getById(id);
        return Result.success(unit);
    }
    @PutMapping
    @ApiOperation("编辑单元信息")
    public Result update(@RequestBody UnitDTO unitDTO){
        log.info("编辑单元信息:{}",unitDTO);
        unitService.update(unitDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除单元")
    public Result delete(@PathVariable Long id){
        log.info("删除单元: {}", id);
        unitService.deleteById(id);
        return Result.success();
    }
    // 菜品的批量删除
//    @DeleteMapping
//    @ApiOperation("单元的批量删除")
//    public Result delete(@RequestParam List<Long> ids){
//        log.info("单元的批量删除: {}", ids);
//        unitService.deleteBatch(ids);
//
//        // 将所有的菜品缓存数据删除 所有以dish_ 开头的缓存数据
////        CleanCache("dish_*");
//        return Result.success();
//    }

}
