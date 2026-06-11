package com.zx.service.impl;

import com.zx.constant.MessageConstant;
import com.zx.constant.StatusConstant;
import com.zx.dto.UserLoginDTO;
import com.zx.entity.User;
import com.zx.exception.PasswordErrorException;
import com.zx.mapper.UserMapper;
import com.zx.service.UserService;
import com.zx.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @param userLoginDTO 登录参数（用户名、密码）
     * @return 统一返回结果，包含 Token 和用户信息
     */

    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            try {
                throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
            } catch (AccountNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (user.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            try {
                throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
            } catch (AccountLockedException e) {
                throw new RuntimeException(e);
            }
        }

        //3、返回实体对象
        return user;
    }


    public UserLoginVO getById(Long id) {
        User user = userMapper.getById(id);
        if (user == null) return null;
        UserLoginVO vo = UserLoginVO.builder()
                .id(user.getId()).username(user.getUsername())
                .name(user.getName()).build();
        return vo;
    }

    public void updateProfile(User user) {
        userMapper.update(user);
    }
}
