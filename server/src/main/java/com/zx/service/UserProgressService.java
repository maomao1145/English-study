package com.zx.service;

import com.zx.entity.UserProgress;
import java.util.List;

public interface UserProgressService {

    /** 更新学习进度（正确+1 or 错误+1） */
    void updateProgress(Long userId, Long unitId, boolean isCorrect);

    /** 查询某用户所有单元进度 */
    List<UserProgress> listByUserId(Long userId);

    /** 查询某用户某单元进度 */
    UserProgress getByUserIdAndUnitId(Long userId, Long unitId);
}
