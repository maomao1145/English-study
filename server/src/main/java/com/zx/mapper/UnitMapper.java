package com.zx.mapper;

import com.github.pagehelper.Page;
import com.zx.annotation.AutoFill;
import com.zx.dto.UnitPageQueryDTO;
import com.zx.entity.Unit;
import com.zx.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UnitMapper {

    @Insert("insert into unit (name,grade,word_count) values (#{name},#{grade},#{wordCount})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Unit unit);

    ///分类查询
    Page<Unit> pageQuery(UnitPageQueryDTO unitPageQueryDTO);

    @Select("select * from unit where id = #{id}")
    Unit getById(Long id);

    @Delete("delete from unit where id = #{id}")
    void deleteById(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Unit unit);
}
