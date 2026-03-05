package com.ruishu.admin.service;

import com.ruishu.admin.common.Result;
import com.ruishu.admin.entity.SysUser;
import com.ruishu.admin.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public Result login(String username, String password) {
        // 1. 查询用户
        SysUser user = sysUserMapper.findByUsername(username);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        // 2. 校验密码
        if (!user.getPassword().equals(password)) {
            return Result.fail("密码错误");
        }
        // 3. 校验状态
        if (user.getStatus() == 0) {
            return Result.fail("账号已被禁用");
        }
        return Result.ok("登录成功，欢迎：" + user.getRealName());
    }
}