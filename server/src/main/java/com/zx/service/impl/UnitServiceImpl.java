package com.zx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.dto.UnitDTO;
import com.zx.dto.UnitPageQueryDTO;
import com.zx.dto.WordDTO;
import com.zx.dto.WordPageQueryDTO;
import com.zx.entity.Unit;
import com.zx.entity.Word;
import com.zx.mapper.UnitMapper;
import com.zx.mapper.WordMapper;
import com.zx.result.PageResult;
import com.zx.service.UnitService;
import com.zx.service.WordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitMapper unitMapper;

    /**
     * 新增单词
     *
     * @param unitDTO
     */
    public void save(UnitDTO unitDTO) {
        Unit unit = new Unit();

        //对象属性拷贝
        BeanUtils.copyProperties(unitDTO, unit);


        unitMapper.insert(unit);
    }


    public PageResult pageQuery(UnitPageQueryDTO unitPageQueryDTO) {
        //select * from employee limit 10
        //开始分类查询
        PageHelper.startPage(unitPageQueryDTO.getPage(), unitPageQueryDTO.getPageSize());

        Page<Unit> page = unitMapper.pageQuery(unitPageQueryDTO);

        long total = page.getTotal();
        List<Unit> records = page.getResult();
        return new PageResult(total, records);
    }

    public Unit getById(Long id) {
        Unit unit = unitMapper.getById(id);
        return unit;
    }

    public void update(UnitDTO unitDTO) {
        Unit unit = new Unit();
        BeanUtils.copyProperties(unitDTO, unit);
        unitMapper.update(unit);
    }

    public void deleteById(Long id) {
        unitMapper.deleteById(id);
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
