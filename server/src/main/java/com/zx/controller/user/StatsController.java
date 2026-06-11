package com.zx.controller.user;

import com.zx.entity.UserBadge;
import com.zx.entity.UserPoints;
import com.zx.entity.UserProgress;
import com.zx.result.Result;
import com.zx.service.GameService;
import com.zx.service.UserProgressService;
import com.zx.service.WrongBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user/stats")
@Slf4j
@Api(tags = "学习分析接口")
public class StatsController {

    @Autowired
    private UserProgressService userProgressService;

    @Autowired
    private GameService gameService;

    @Autowired
    private WrongBookService wrongBookService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/dashboard")
    @ApiOperation("学习分析仪表盘数据")
    public Result<Map<String, Object>> dashboard(Long userId) {
        Map<String, Object> data = new HashMap<>();

        // --- 1. 单元进度 (饼图) ---
        List<UserProgress> progressList = userProgressService.listByUserId(userId);
        List<Map<String, Object>> pieData = new ArrayList<>();
        int totalLearned = 0, totalCorrect = 0, totalWrong = 0, completedUnits = 0;
        for (UserProgress p : progressList) {
            totalLearned += p.getLearnedWords() != null ? p.getLearnedWords() : 0;
            totalCorrect += p.getCorrectCount() != null ? p.getCorrectCount() : 0;
            totalWrong += p.getWrongCount() != null ? p.getWrongCount() : 0;
            if (p.getTotalWords() != null && p.getTotalWords() > 0 && p.getLearnedWords() >= p.getTotalWords()) {
                completedUnits++;
            }
            Map<String, Object> item = new HashMap<>();
            item.put("unitId", p.getUnitId());
            item.put("learned", p.getLearnedWords());
            item.put("total", p.getTotalWords());
            pieData.add(item);
        }
        data.put("pieData", pieData);
        data.put("totalLearned", totalLearned);
        data.put("totalCorrect", totalCorrect);
        data.put("totalWrong", totalWrong);
        data.put("completedUnits", completedUnits);
        data.put("totalUnits", progressList.size());

        // --- 2. 学习趋势 (折线图 - 按日期统计答题数) ---
        try {
            List<Map<String, Object>> trend = jdbcTemplate.queryForList(
                "SELECT DATE(update_time) as date, SUM(learned_words) as learned, SUM(correct_count) as correct " +
                "FROM user_progress WHERE user_id = ? GROUP BY DATE(update_time) ORDER BY date",
                userId
            );
            data.put("trendData", trend);
        } catch (Exception e) {
            data.put("trendData", List.of());
        }

        // --- 3. 错题净化 (净化率) ---
        try {
            int totalWrongBook = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM wrong_book WHERE user_id = ?", Integer.class, userId
            );
            int corrected = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM wrong_book WHERE user_id = ? AND is_corrected = 1", Integer.class, userId
            );
            data.put("wrongTotal", totalWrongBook);
            data.put("wrongCorrected", corrected);
            data.put("purifyRate", totalWrongBook > 0 ? (corrected * 100 / totalWrongBook) : 0);
        } catch (Exception e) {
            data.put("wrongTotal", 0);
            data.put("wrongCorrected", 0);
            data.put("purifyRate", 0);
        }

        // --- 4. 五维能力 ---
        UserPoints points = gameService.getUserPoints(userId);
        Map<String, Object> radar = new HashMap<>();
        radar.put("vocabulary", Math.min(100, totalLearned));                    // 词汇量
        radar.put("accuracy", totalCorrect + totalWrong > 0
                ? (totalCorrect * 100 / (totalCorrect + totalWrong)) : 0);       // 准确率
        radar.put("diligence", Math.min(100, (points.getStreakDays() != null ? points.getStreakDays() : 0) * 10)); // 毅力
        radar.put("recovery", data.get("purifyRate"));                           // 纠错力
        radar.put("speed", 50);                                                   // 速度(暂无数据暂设50)
        data.put("radar", radar);

        // --- 5. 好友里程碑 ---
        List<UserPoints> leaderboard = gameService.getLeaderboard();
        data.put("leaderboard", leaderboard.size() > 5 ? leaderboard.subList(0, 5) : leaderboard);

        // --- 6. 徽章 ---
        List<UserBadge> badges = gameService.getUserBadges(userId);
        data.put("badges", badges);

        return Result.success(data);
    }
}
