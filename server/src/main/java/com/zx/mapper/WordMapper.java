package com.zx.mapper;

import com.github.pagehelper.Page;
import com.zx.annotation.AutoFill;
import com.zx.dto.WordPageQueryDTO;
import com.zx.entity.Word;
import com.zx.enumeration.OperationType;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface WordMapper {

    @Insert("insert into word(word,phonetic,definition,image_url,example_sentence,example_translation,unit_id) values(#{word},#{phonetic},#{definition},#{imageUrl},#{exampleSentence},#{exampleTranslation},#{unitId})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Word word);

    ///分类查询
    Page<Word> pageQuery(WordPageQueryDTO wordPageQueryDTO);

    @Select("select * from word where id = #{id}")
    Word getById(Long id);

    @Select("select * from word where unit_id = #{unitId}")
    List<Word> getByUnitId(Long unitId);

    @Delete("delete from word where id = #{id}")
    void deleteById(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Word word);
}
