package com.zx.mapper;

import com.github.pagehelper.Page;
import com.zx.annotation.AutoFill;
import com.zx.dto.AdminPageQueryDTO;
import com.zx.entity.Admin;
import com.zx.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where username = #{username}")
    Admin getByUsername(String username);

    @Insert("insert into admin (username, password, name, phone, status, create_time, update_time) " +
            "values (#{username},#{password},#{name},#{phone},#{status},#{createTime},#{updateTime})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Admin admin);

    Page<Admin> pageQuery(AdminPageQueryDTO dto);

    @Select("select * from admin where id = #{id}")
    Admin getById(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Admin admin);

    @Delete("delete from admin where id = #{id}")
    void deleteById(Long id);
}
