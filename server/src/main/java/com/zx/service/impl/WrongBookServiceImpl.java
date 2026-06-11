package com.zx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.entity.WrongBook;
import com.zx.mapper.WrongBookMapper;
import com.zx.result.PageResult;
import com.zx.service.WrongBookService;
import com.zx.vo.WrongBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WrongBookServiceImpl implements WrongBookService {

    @Autowired
    private WrongBookMapper wrongBookMapper;

    public void recordWrong(Long userId, Long wordId) {
        WrongBook exist = wrongBookMapper.getByUserIdAndWordId(userId, wordId);
        if (exist != null) {
            exist.setWrongCount(exist.getWrongCount() + 1);
            exist.setLastWrong(LocalDateTime.now());
            wrongBookMapper.update(exist);
        } else {
            WrongBook wb = WrongBook.builder()
                    .userId(userId)
                    .wordId(wordId)
                    .wrongCount(1)
                    .lastWrong(LocalDateTime.now())
                    .build();
            wrongBookMapper.insert(wb);
        }
    }

    public PageResult pageQuery(Long userId, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        Page<WrongBookVO> p = wrongBookMapper.pageByUserId(userId);
        return new PageResult(p.getTotal(), p.getResult());
    }

    public List<WrongBookVO> listAll(Long userId) {
        PageHelper.startPage(1, 500);
        Page<WrongBookVO> p = wrongBookMapper.pageByUserId(userId);
        return p.getResult();
    }

    public void deleteById(Long id) {
        wrongBookMapper.deleteById(id);
    }

    public void deleteAll(Long userId) {
        wrongBookMapper.deleteAllByUserId(userId);
    }

    public void markCorrected(Long id) {
        WrongBook wb = WrongBook.builder().id(id).isCorrected(1).build();
        wrongBookMapper.update(wb);
    }
}
