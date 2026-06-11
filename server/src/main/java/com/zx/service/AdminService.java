package com.zx.service;

import com.zx.dto.AdminDTO;
import com.zx.dto.AdminLoginDTO;
import com.zx.dto.AdminPageQueryDTO;
import com.zx.entity.Admin;
import com.zx.result.PageResult;
import com.zx.vo.UserLoginVO;

public interface AdminService {

    Admin login(AdminLoginDTO adminLoginDTO);

    UserLoginVO getById(Long id);

    void save(AdminDTO dto);

    PageResult pageQuery(AdminPageQueryDTO dto);

    void update(AdminDTO dto);

    void deleteById(Long id);
}
