package com.czk.ssm.service;

import com.czk.ssm.pojo.Role;
import com.czk.ssm.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll();  //查询用户列表

    UserInfo findById(int id);  // 查询用户详情信息

    void save(UserInfo userInfo);// 存储用户信息

    List<Role> findUserByIdAndAllRole(String id); // 查询用户欠缺的角色列表

    void addRoleToUser(String userId, String ids); // 用户添加角色
}
