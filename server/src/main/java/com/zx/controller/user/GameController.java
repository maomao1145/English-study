package com.zx.controller.user;

import com.zx.entity.UserBadge;
import com.zx.entity.UserPoints;
import com.zx.result.Result;
import com.zx.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/game")
@Slf4j
@Api(tags = "游戏激励相关接口")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/points")
    @ApiOperation("查询用户积分等级")
    public Result<UserPoints> getPoints(Long userId) {
        UserPoints points = gameService.getUserPoints(userId);
        return Result.success(points);
    }

    @PostMapping("/checkin")
    @ApiOperation("每日打卡")
    public Result<Map<String, Object>> checkin(Long userId) {
        log.info("用户打卡: userId={}", userId);
        try {
            int bonus = gameService.dailyCheckin(userId);
            Map<String, Object> result = new HashMap<>();
            result.put("bonus", bonus);
            result.put("message", "打卡成功！获得 " + bonus + " 积分");
            return Result.success(result);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/addPoints")
    @ApiOperation("增加积分")
    public Result addPoints(Long userId, Integer points) {
        log.info("增加积分: userId={}, points={}", userId, points);
        gameService.addPoints(userId, points != null ? points : 0);
        return Result.success();
    }

    @PostMapping("/award")
    @ApiOperation("授予徽章")
    public Result awardBadge(Long userId, String badgeKey) {
        log.info("授予徽章: userId={}, badgeKey={}", userId, badgeKey);
        gameService.awardBadge(userId, badgeKey);
        return Result.success();
    }

    @GetMapping("/badges")
    @ApiOperation("查询用户徽章列表")
    public Result<List<UserBadge>> getBadges(Long userId) {
        List<UserBadge> badges = gameService.getUserBadges(userId);
        return Result.success(badges);
    }

    @GetMapping("/checkBadges")
    @ApiOperation("检查并自动授予徽章")
    public Result<List<String>> checkBadges(Long userId) {
        log.info("检查徽章: userId={}", userId);
        List<String> newBadges = gameService.checkAndAwardBadges(userId);
        return Result.success(newBadges);
    }

    @PostMapping("/unitComplete")
    @ApiOperation("单元完成通知")
    public Result unitComplete(Long userId, Integer correctCount, Integer totalCount) {
        log.info("单元完成: userId={}, {}/{}", userId, correctCount, totalCount);
        gameService.onUnitComplete(userId, correctCount != null ? correctCount : 0, totalCount != null ? totalCount : 0);
        List<String> newBadges = gameService.checkAndAwardBadges(userId);
        return Result.success(newBadges);
    }

    @GetMapping("/leaderboard")
    @ApiOperation("积分排行榜")
    public Result<List<UserPoints>> leaderboard() {
        List<UserPoints> list = gameService.getLeaderboard();
        return Result.success(list);
    }
}
