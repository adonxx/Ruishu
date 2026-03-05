package com.ruishu.admin.mapper;

import com.ruishu.admin.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {

    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    SysUser findByUsername(String username);
}