package com.zx.service;

import com.zx.dto.UserDTO;
import com.zx.dto.UserPageQueryDTO;
import com.zx.dto.WordDTO;
import com.zx.dto.WordPageQueryDTO;
import com.zx.entity.User;
import com.zx.entity.Word;
import com.zx.result.PageResult;

public interface UserAdminService {

    void save(UserDTO userDTO);

    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    User getById(Long id);

    void update(UserDTO userDTO);

    void startOrStop(Integer status, Long id);

    void deleteById(Long id);
}
