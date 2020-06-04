package com.czk.ssm.service.impl;

import com.czk.ssm.dao.IUserDao;
import com.czk.ssm.pojo.Role;
import com.czk.ssm.pojo.UserInfo;
import com.czk.ssm.service.IUserService;
import com.czk.ssm.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("userService")
public class IUserServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;

    // 获取所有用户
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    // 添加用户
    @Override
    public void save(UserInfo userInfo) {
        // 加密
        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));

        userDao.save(userInfo);
    }

    // 通过ID查对应的用户
    @Override
    public UserInfo findById(int id) {
        return userDao.findById(id);
    }


    // 查询用户欠缺的角色列表
    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
        return userDao.findUserByIdAndAllRole(id);
    }

    // 用户添加角色
    @Override
    public void addRoleToUser(String userId, String ids) {
        userDao.addRoleToUser(userId,ids);
    }

    // 1.从数据库获取账号密码角色
    // 2.返回UserDetails对象给Spring Security 校验
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUserNme(s); // 从数据库获取对象

        //org.springframework.security.core.userdetails.User;
        // 封装UserDetails对象User
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), getPermission(userInfo.getRoles())); // 账号,密码,权限

        return user;
    }

    // SimpleGrantedAuthority接口集合,获取用户对应角色集合
    private List<SimpleGrantedAuthority> getPermission(List<Role> roles) {

        List<SimpleGrantedAuthority> list=new ArrayList<>();
        // 遍历用户对应角色集合
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName())); // ROLE_ADMIN / ROLE_USER
        }
        return list;
    }


}
