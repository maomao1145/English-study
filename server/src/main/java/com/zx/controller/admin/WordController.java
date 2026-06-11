package com.zx.controller.admin;


import com.zx.dto.WordDTO;
import com.zx.dto.WordPageQueryDTO;
import com.zx.entity.Word;
import com.zx.result.PageResult;
import com.zx.result.Result;
import com.zx.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/word")
@Slf4j
@Api(tags = "单词相关接口")

public class WordController {

    @Autowired
    private WordService wordService;

    @PostMapping
    public Result save(@RequestBody WordDTO wordDTO){
        log.info("新增单词: {}",wordDTO);
        wordService.save(wordDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("单词分类查询")
    public Result<PageResult> page(WordPageQueryDTO wordPageQueryDTO){
        log.info("单词分类查询,参数为:{}",wordPageQueryDTO);
        PageResult pageResult = wordService.pageQuery(wordPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据单词id查询单词信息")
    public Result<Word> getById(@PathVariable Long id){
        Word word= wordService.getById(id);
        return Result.success(word);
    }
    @GetMapping("/byUnit/{unitId}")
    @ApiOperation("根据单元id查询单词列表")
    public Result<List<Word>> getByUnitId(@PathVariable Long unitId) {
        List<Word> list = wordService.getByUnitId(unitId);
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除单词")
    public Result delete(@PathVariable Long id) {
        log.info("删除单词: {}", id);
        wordService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("编辑单词信息")
    public Result update(@RequestBody WordDTO wordDTO){
        log.info("编辑单词信息:{}",wordDTO);
        wordService.update(wordDTO);
        return Result.success();
    }
    // 菜品的批量删除
//    @DeleteMapping
//    @ApiOperation("单词的批量删除")
//    public Result delete(@RequestParam List<Long> ids){
//        log.info("单词的批量删除: {}", ids);
//        wordService.deleteBatch(ids);
//
//        // 将所有的菜品缓存数据删除 所有以dish_ 开头的缓存数据
////        CleanCache("dish_*");
//        return Result.success();
//    }

}
