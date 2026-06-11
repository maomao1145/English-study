package com.zx.mapper;

import com.zx.entity.UserBadge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserBadgeMapper {

    @Insert("insert into user_badge (user_id, badge_key, earned_time) values (#{userId}, #{badgeKey}, #{earnedTime})")
    void insert(UserBadge badge);

    @Select("select * from user_badge where user_id = #{userId}")
    List<UserBadge> listByUserId(Long userId);

    @Select("select * from user_badge where user_id = #{userId} and badge_key = #{badgeKey}")
    UserBadge getByUserIdAndBadgeKey(Long userId, String badgeKey);
}
