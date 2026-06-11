package com.zx.mapper;

import com.zx.entity.UserProgress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserProgressMapper {

    @Insert("insert into user_progress (user_id, unit_id, total_words, learned_words, correct_count, wrong_count) " +
            "values (#{userId}, #{unitId}, #{totalWords}, #{learnedWords}, #{correctCount}, #{wrongCount})")
    void insert(UserProgress progress);

    @Select("select * from user_progress where user_id = #{userId} and unit_id = #{unitId}")
    UserProgress getByUserIdAndUnitId(Long userId, Long unitId);

    @Select("select * from user_progress where user_id = #{userId}")
    List<UserProgress> listByUserId(Long userId);

    void update(UserProgress progress);
}
