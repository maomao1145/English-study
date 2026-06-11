package com.zx.controller.user;

import com.zx.entity.UserProgress;
import com.zx.result.Result;
import com.zx.service.UserProgressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/progress")
@Slf4j
@Api(tags = "学习进度相关接口")
public class ProgressController {

    @Autowired
    private UserProgressService userProgressService;

    @PostMapping("/update")
    @ApiOperation("更新学习进度（答对/答错）")
    public Result updateProgress(Long userId, Long unitId, Boolean isCorrect) {
        log.info("更新学习进度: userId={}, unitId={}, isCorrect={}", userId, unitId, isCorrect);
        userProgressService.updateProgress(userId, unitId, isCorrect != null && isCorrect);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("查询用户所有单元进度")
    public Result<List<UserProgress>> list(Long userId) {
        List<UserProgress> list = userProgressService.listByUserId(userId);
        return Result.success(list);
    }

    @GetMapping("/get")
    @ApiOperation("查询用户某单元进度")
    public Result<UserProgress> get(Long userId, Long unitId) {
        UserProgress progress = userProgressService.getByUserIdAndUnitId(userId, unitId);
        return Result.success(progress);
    }
}
