package com.zx.service.impl;

import com.zx.entity.UserProgress;
import com.zx.mapper.UserProgressMapper;
import com.zx.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserProgressServiceImpl implements UserProgressService {

    @Autowired
    private UserProgressMapper userProgressMapper;

    public void updateProgress(Long userId, Long unitId, boolean isCorrect) {
        UserProgress progress = userProgressMapper.getByUserIdAndUnitId(userId, unitId);
        if (progress == null) {
            progress = UserProgress.builder()
                    .userId(userId)
                    .unitId(unitId)
                    .totalWords(0)
                    .learnedWords(1)
                    .correctCount(isCorrect ? 1 : 0)
                    .wrongCount(isCorrect ? 0 : 1)
                    .build();
            userProgressMapper.insert(progress);
        } else {
            progress.setLearnedWords(progress.getLearnedWords() + 1);
            if (isCorrect) {
                progress.setCorrectCount(progress.getCorrectCount() + 1);
            } else {
                progress.setWrongCount(progress.getWrongCount() + 1);
            }
            userProgressMapper.update(progress);
        }
    }

    public List<UserProgress> listByUserId(Long userId) {
        return userProgressMapper.listByUserId(userId);
    }

    public UserProgress getByUserIdAndUnitId(Long userId, Long unitId) {
        return userProgressMapper.getByUserIdAndUnitId(userId, unitId);
    }
}
