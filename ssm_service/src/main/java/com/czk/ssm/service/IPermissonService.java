package com.czk.ssm.service;

import com.czk.ssm.pojo.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPermissonService {
    List<Permission> findAll(); // 查询所有权限列表

    void save(Permission permission);// 添加权限

    void deletePermission(String id); // 删除权限
}
