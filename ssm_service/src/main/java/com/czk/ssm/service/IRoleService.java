package com.czk.ssm.service;

import com.czk.ssm.pojo.Permission;
import com.czk.ssm.pojo.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();  // 查询所有角色

    void save(Role role);  // 添加角色

    Role findById2(String id); // 角色详情

    void deleteRole(String id); // 删除角色

    List<Permission> findRoleByIdAndAllPermission(String id); // 根据角色Id查询 欠缺的权限

    void addPermissionToRole(String roleId,String id);//
}
