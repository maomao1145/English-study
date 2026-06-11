package com.zx.service;

import com.zx.entity.UserBadge;
import com.zx.entity.UserPoints;
import java.util.List;
import java.util.Map;

public interface GameService {

    /** 查询用户积分等级 */
    UserPoints getUserPoints(Long userId);

    /** 每日打卡，返回获得积分 */
    int dailyCheckin(Long userId);

    /** 增加积分 */
    void addPoints(Long userId, int points);

    /** 查询积分排行榜 topN */
    List<UserPoints> getLeaderboard();

    /** 查询用户徽章列表 */
    List<UserBadge> getUserBadges(Long userId);

    /** 授予徽章 */
    void awardBadge(Long userId, String badgeKey);

    /** 检查并自动授予符合条件的徽章，返回本次新获得的徽章列表 */
    List<String> checkAndAwardBadges(Long userId);

    /** 单元完成检查（用于满分学霸判定） */
    void onUnitComplete(Long userId, int correctCount, int totalCount);
}
