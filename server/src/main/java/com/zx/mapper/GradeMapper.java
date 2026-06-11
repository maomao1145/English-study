package com.zx.mapper;

import com.github.pagehelper.Page;
import com.zx.annotation.AutoFill;
import com.zx.dto.GradePageQueryDTO;
import com.zx.entity.Grade;
import com.zx.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GradeMapper {

    @Insert("insert into grade (name, status, grade_id) values (#{name}, #{status}, #{gradeId})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Grade grade);

    Page<Grade> pageQuery(GradePageQueryDTO gradePageQueryDTO);

    @Select("select * from grade where id = #{id}")
    Grade getById(Long id);

    @Delete("delete from grade where id = #{id}")
    void deleteById(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Grade grade);
}
