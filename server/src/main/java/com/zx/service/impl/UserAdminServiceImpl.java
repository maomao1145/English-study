package com.zx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.constant.PasswordConstant;
import com.zx.constant.StatusConstant;
import com.zx.dto.UserDTO;
import com.zx.dto.UserPageQueryDTO;
import com.zx.entity.User;
import com.zx.mapper.UserMapper;
import com.zx.result.PageResult;
import com.zx.service.UserAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;


@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserMapper usermapper;

    /**
     * 新增单词
     * @param userDTO
     */
    public void save(UserDTO userDTO){
        User user = new User();

        //对象属性拷贝
        BeanUtils.copyProperties(userDTO,user);

        //设置账号状态,默认正常状态
        user.setStatus(StatusConstant.ENABLE);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));


//        //设置当前记录的创建时间和修改时间
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//
//        //设置当前记录的创建人id和修改人id
//
//        employee.setCreateUser(BaseContext.getCurrentId());
//        employee.setUpdateUser(BaseContext.getCurrentId());

        usermapper.insert(user);
    }


    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        //select * from employee limit 10
        //开始分类查询
        PageHelper.startPage(userPageQueryDTO.getPage(),userPageQueryDTO.getPageSize());

        Page<User> page =usermapper.pageQuery(userPageQueryDTO);

        long total = page.getTotal();
        List<User> records = page.getResult();
        return new PageResult(total,records);
    }

    public User getById(Long id) {
        User user = usermapper.getById(id);
        return user;
    }

    public void update(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);

        usermapper.update(user);
    }

    public void startOrStop(Integer status, Long id) {
        User user = User.builder()
                .status(status)
                .id(id)
                .build();
        usermapper.update(user);
    }

    public void deleteById(Long id) {
        usermapper.deleteById(id);
    }
}
