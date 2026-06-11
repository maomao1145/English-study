package com.zx.service.impl;

import com.zx.entity.UserBadge;
import com.zx.entity.UserPoints;
import com.zx.entity.UserProgress;
import com.zx.mapper.UserBadgeMapper;
import com.zx.mapper.UserPointsMapper;
import com.zx.mapper.UserProgressMapper;
import com.zx.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private UserPointsMapper userPointsMapper;

    @Autowired
    private UserBadgeMapper userBadgeMapper;

    @Autowired
    private UserProgressMapper userProgressMapper;

    public UserPoints getUserPoints(Long userId) {
        UserPoints up = userPointsMapper.getByUserId(userId);
        if (up == null) {
            up = UserPoints.builder()
                    .userId(userId).points(0).level(1).streakDays(0).build();
            userPointsMapper.insert(up);
        }
        return up;
    }

    public int dailyCheckin(Long userId) {
        UserPoints up = getUserPoints(userId);
        LocalDate today = LocalDate.now();
        if (up.getLastCheckin() != null && up.getLastCheckin().equals(today)) {
            throw new RuntimeException("今日已打卡，请勿重复打卡");
        }
        int bonus = 10;
        if (up.getLastCheckin() != null && up.getLastCheckin().equals(today.minusDays(1))) {
            up.setStreakDays(up.getStreakDays() + 1);
            bonus += up.getStreakDays() * 2;
        } else {
            up.setStreakDays(1);
        }
        up.setLastCheckin(today);
        up.setPoints(up.getPoints() + bonus);
        updateLevel(up);
        userPointsMapper.update(up);
        // 自动检查连击徽章
        checkAndAwardBadges(userId);
        return bonus;
    }

    public void addPoints(Long userId, int points) {
        UserPoints up = getUserPoints(userId);
        up.setPoints(up.getPoints() + points);
        updateLevel(up);
        userPointsMapper.update(up);
        // 自动检查积分相关徽章
        checkAndAwardBadges(userId);
    }

    private void updateLevel(UserPoints up) {
        int newLevel = up.getPoints() / 200 + 1;
        up.setLevel(Math.max(1, newLevel));
    }

    public List<UserPoints> getLeaderboard() {
        return userPointsMapper.listTop();
    }

    public List<UserBadge> getUserBadges(Long userId) {
        return userBadgeMapper.listByUserId(userId);
    }

    public void awardBadge(Long userId, String badgeKey) {
        if (userBadgeMapper.getByUserIdAndBadgeKey(userId, badgeKey) != null) {
            return;
        }
        UserBadge badge = UserBadge.builder()
                .userId(userId)
                .badgeKey(badgeKey)
                .earnedTime(LocalDateTime.now())
                .build();
        userBadgeMapper.insert(badge);
    }

    public List<String> checkAndAwardBadges(Long userId) {
        List<String> newBadges = new ArrayList<>();
        UserPoints up = getUserPoints(userId);
        List<UserProgress> progressList = userProgressMapper.listByUserId(userId);
        int totalLearned = progressList.stream().mapToInt(UserProgress::getLearnedWords).sum();

        // first_study: 已学习至少1个单词
        if (totalLearned >= 1) {
            if (tryAward(userId, "first_study")) newBadges.add("first_study");
        }
        // vocab_master: 累计学习100个单词
        if (totalLearned >= 100) {
            if (tryAward(userId, "vocab_master")) newBadges.add("vocab_master");
        }
        // persist_30: 累计打卡30天
        if (up.getStreakDays() >= 30) {
            if (tryAward(userId, "persist_30")) newBadges.add("persist_30");
        }
        // streak_7: 连续打卡7天
        if (up.getStreakDays() >= 7) {
            if (tryAward(userId, "streak_7")) newBadges.add("streak_7");
        }
        return newBadges;
    }

    public void onUnitComplete(Long userId, int correctCount, int totalCount) {
        // full_score: 一次练习全对
        if (totalCount > 0 && correctCount == totalCount) {
            awardBadge(userId, "full_score");
        }
    }

    private boolean tryAward(Long userId, String badgeKey) {
        if (userBadgeMapper.getByUserIdAndBadgeKey(userId, badgeKey) == null) {
            awardBadge(userId, badgeKey);
            addPoints(userId, 50); // 获得徽章 +50 积分
            return true;
        }
        return false;
    }
}
