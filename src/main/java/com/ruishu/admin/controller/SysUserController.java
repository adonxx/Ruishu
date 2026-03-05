package com.ruishu.admin.controller;

import com.ruishu.admin.common.Result;
import com.ruishu.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result login(@RequestParam String username,
                        @RequestParam String password) {
        return sysUserService.login(username, password);
    }
}