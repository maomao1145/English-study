package com.zx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.constant.MessageConstant;
import com.zx.constant.PasswordConstant;
import com.zx.constant.StatusConstant;
import com.zx.dto.WordDTO;
import com.zx.dto.WordPageQueryDTO;
import com.zx.dto.WordSaveDTO;
import com.zx.entity.Word;
import com.zx.exception.DeletionNotAllowedException;
import com.zx.mapper.WordMapper;
import com.zx.result.PageResult;
import com.zx.service.WordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;


@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordMapper wordMapper;

    /**
     * 新增单词
     *
     * @param wordDTO
     */
    public void save(WordDTO wordDTO) {
        Word word = new Word();

        //对象属性拷贝
        BeanUtils.copyProperties(wordDTO, word);


        wordMapper.insert(word);
    }


    public PageResult pageQuery(WordPageQueryDTO wordPageQueryDTO) {
        //select * from employee limit 10
        //开始分类查询
        PageHelper.startPage(wordPageQueryDTO.getPage(), wordPageQueryDTO.getPageSize());

        Page<Word> page = wordMapper.pageQuery(wordPageQueryDTO);

        long total = page.getTotal();
        List<Word> records = page.getResult();
        return new PageResult(total, records);
    }

    public Word getById(Long id) {
        Word word = wordMapper.getById(id);
        return word;
    }

    public List<Word> getByUnitId(Long unitId) {
        return wordMapper.getByUnitId(unitId);
    }

    public void deleteById(Long id) {
        wordMapper.deleteById(id);
    }

    public void update(WordDTO wordDTO) {
        Word word = new Word();
        BeanUtils.copyProperties(wordDTO, word);

        wordMapper.update(word);
    }

//    public void deleteBatch(List<Long> ids) {
//        //判断当前的单词是否能够删除 是否存在启售中的菜品? status
////        for (Long id : ids) {
////            Word word = wordMapper.getById(id);
////            if (dish.getStatus() == StatusConstant.ENABLE) {
////                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
////            }
////        }
//        //判断当前单词是否能够删除 是否被单元关联了
//        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
//        if(setmealIds != null && setmealIds.size() > 0){
//            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
//        }
//
//
//        //如果ids很多性能也会很大
//        //代码优化
//        //根据菜品集合删除菜品表的数据
//        dishMapper.deleteByIds(ids);
//        //根据菜品集合删除菜品关联的口味数据
//        dishFlavorMapper.deleteByDishIds(ids);
//
//
//    }

}
