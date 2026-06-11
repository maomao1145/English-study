package com.zx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.constant.MessageConstant;
import com.zx.constant.StatusConstant;
import com.zx.dto.AdminDTO;
import com.zx.dto.AdminLoginDTO;
import com.zx.dto.AdminPageQueryDTO;
import com.zx.entity.Admin;
import com.zx.exception.PasswordErrorException;
import com.zx.mapper.AdminMapper;
import com.zx.result.PageResult;
import com.zx.service.AdminService;
import com.zx.vo.UserLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin login(AdminLoginDTO adminLoginDTO) {
        String username = adminLoginDTO.getUsername();
        String password = adminLoginDTO.getPassword();
        Admin admin = adminMapper.getByUsername(username);
        if (admin == null) {
            try { throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND); }
            catch (AccountNotFoundException e) { throw new RuntimeException(e); }
        }
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(admin.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        if (admin.getStatus() != null && admin.getStatus() == StatusConstant.DISABLE) {
            try { throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED); }
            catch (AccountLockedException e) { throw new RuntimeException(e); }
        }
        return admin;
    }

    public UserLoginVO getById(Long id) {
        Admin admin = adminMapper.getById(id);
        if (admin == null) return null;
        return UserLoginVO.builder().id(admin.getId()).username(admin.getUsername()).build();
    }

    public void save(AdminDTO dto) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(dto, admin);
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        adminMapper.insert(admin);
    }

    public PageResult pageQuery(AdminPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<Admin> page = adminMapper.pageQuery(dto);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public void update(AdminDTO dto) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(dto, admin);
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            admin.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        } else {
            admin.setPassword(null);
        }
        adminMapper.update(admin);
    }

    public void deleteById(Long id) {
        adminMapper.deleteById(id);
    }
}
