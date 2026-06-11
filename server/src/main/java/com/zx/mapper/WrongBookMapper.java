package com.zx.mapper;

import com.github.pagehelper.Page;
import com.zx.entity.WrongBook;
import com.zx.vo.WrongBookVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WrongBookMapper {

    @Insert("insert into wrong_book (user_id, word_id, wrong_count, last_wrong) " +
            "values (#{userId}, #{wordId}, #{wrongCount}, #{lastWrong})")
    void insert(WrongBook wrongBook);

    Page<WrongBookVO> pageByUserId(Long userId);

    @Select("select wb.*, w.word, w.phonetic, w.definition " +
            "from wrong_book wb left join word w on wb.word_id = w.id " +
            "where wb.user_id = #{userId} and wb.word_id = #{wordId}")
    WrongBookVO getDetailByUserIdAndWordId(Long userId, Long wordId);

    @Select("select * from wrong_book where user_id = #{userId} and word_id = #{wordId}")
    WrongBook getByUserIdAndWordId(Long userId, Long wordId);

    void update(WrongBook wrongBook);

    @Delete("delete from wrong_book where id = #{id}")
    void deleteById(Long id);

    @Delete("delete from wrong_book where user_id = #{userId}")
    void deleteAllByUserId(Long userId);
}
