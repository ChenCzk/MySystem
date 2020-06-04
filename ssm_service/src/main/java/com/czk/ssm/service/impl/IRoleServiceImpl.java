package com.czk.ssm.service.impl;

import com.czk.ssm.dao.IRoleDao;
import com.czk.ssm.pojo.Permission;
import com.czk.ssm.pojo.Role;
import com.czk.ssm.service.IRoleService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
         roleDao.save(role);
    }

    @Override
    public Role findById2(String id) {
        return roleDao.findById2(id);
    }

    @Override
    public void deleteRole(String id) {
        roleDao.deleteRole(id);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) {
       return roleDao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId,String id) {
        roleDao.addPermissionToRole(roleId,id);
    }
}
