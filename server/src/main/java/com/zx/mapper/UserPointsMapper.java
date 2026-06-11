package com.zx.mapper;

import com.zx.entity.UserPoints;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserPointsMapper {

    @Insert("insert into user_points (user_id, points, level, streak_days, last_checkin) " +
            "values (#{userId}, #{points}, #{level}, #{streakDays}, #{lastCheckin})")
    void insert(UserPoints userPoints);

    @Select("select * from user_points where user_id = #{userId}")
    UserPoints getByUserId(Long userId);

    void update(UserPoints userPoints);

    @Select("select up.*, u.name as userName from user_points up left join user u on up.user_id = u.id order by up.points desc")
    List<UserPoints> listTop();
}
