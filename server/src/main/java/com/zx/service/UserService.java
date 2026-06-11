package com.zx.service;

import com.zx.dto.UserLoginDTO;
import com.zx.entity.User;
import com.zx.vo.UserLoginVO;

public interface UserService {


    User login(UserLoginDTO userLoginDTO) ;

    UserLoginVO getById(Long id);

    void updateProfile(User user);
}
