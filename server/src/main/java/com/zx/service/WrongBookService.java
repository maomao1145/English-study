package com.zx.service;

import com.zx.result.PageResult;
import com.zx.vo.WrongBookVO;

import java.util.List;

public interface WrongBookService {

    /** 记录错题：如果已存在则增加错误次数 */
    void recordWrong(Long userId, Long wordId);

    /** 分页查询某用户的错题（含单词信息） */
    PageResult pageQuery(Long userId, int page, int pageSize);

    /** 获取所有错题（用于练习） */
    List<WrongBookVO> listAll(Long userId);

    /** 删除单条错题 */
    void deleteById(Long id);

    /** 清空某用户全部错题 */
    void deleteAll(Long userId);

    /** 标记为已纠正 */
    void markCorrected(Long id);
}
