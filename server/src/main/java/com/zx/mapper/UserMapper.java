package com.zx.mapper;

import com.github.pagehelper.Page;
import com.zx.annotation.AutoFill;
//import com.zx.dto.EmployeePageQueryDTO;
import com.zx.dto.UserPageQueryDTO;
import com.zx.entity.User;
import com.zx.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);


    @Insert("insert into user (name, username, password, phone, sex, create_time, update_time, status) " +
            "values" +
            "(#{name},#{username},#{password},#{phone},#{sex},#{createTime},#{updateTime},#{status})")
    @AutoFill(value = OperationType.INSERT)
    void insert(User user);

//    ///分类查询
    Page<User> pageQuery(UserPageQueryDTO userPageQueryDTO);
//
    @AutoFill(value = OperationType.UPDATE)
    void update(User user);

    @Select("select * from user where id = #{id}")
    User getById(Long id);

    @Delete("delete from user where id = #{id}")
    void deleteById(Long id);
}
