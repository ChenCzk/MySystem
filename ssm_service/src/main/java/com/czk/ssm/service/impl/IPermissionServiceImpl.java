package com.czk.ssm.service.impl;

import com.czk.ssm.dao.IPermissionDao;
import com.czk.ssm.pojo.Permission;
import com.czk.ssm.service.IPermissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("permissionService")
public class IPermissionServiceImpl implements IPermissonService {

    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public void deletePermission(String id) {
        permissionDao.deletePermission(id);
    }
}
