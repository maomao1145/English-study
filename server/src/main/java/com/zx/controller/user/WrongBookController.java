package com.zx.controller.user;

import com.zx.result.PageResult;
import com.zx.result.Result;
import com.zx.service.WrongBookService;
import com.zx.vo.WrongBookVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/wrongbook")
@Slf4j
@Api(tags = "错题本相关接口")
public class WrongBookController {

    @Autowired
    private WrongBookService wrongBookService;

    @PostMapping("/record")
    @ApiOperation("记录错题")
    public Result recordWrong(Long userId, Long wordId) {
        log.info("记录错题: userId={}, wordId={}", userId, wordId);
        wrongBookService.recordWrong(userId, wordId);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询错题")
    public Result<PageResult> page(Long userId,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "20") int pageSize) {
        PageResult result = wrongBookService.pageQuery(userId, page, pageSize);
        return Result.success(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除单条错题")
    public Result delete(@PathVariable Long id) {
        log.info("删除错题: {}", id);
        wrongBookService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/all")
    @ApiOperation("清空全部错题")
    public Result deleteAll(Long userId) {
        log.info("清空错题: userId={}", userId);
        wrongBookService.deleteAll(userId);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("获取所有错题（用于练习）")
    public Result<List<WrongBookVO>> listAll(Long userId) {
        List<WrongBookVO> list = wrongBookService.listAll(userId);
        return Result.success(list);
    }

    @PutMapping("/{id}/correct")
    @ApiOperation("标记错题为已纠正")
    public Result markCorrected(@PathVariable Long id) {
        log.info("标记已纠正: {}", id);
        wrongBookService.markCorrected(id);
        return Result.success();
    }
}
