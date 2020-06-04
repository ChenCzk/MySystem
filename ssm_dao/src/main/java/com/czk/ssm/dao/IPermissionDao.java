package com.czk.ssm.dao;

import com.czk.ssm.pojo.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPermissionDao {
    // 根据RoleId查询对应权限
    @Select("SELECT * FROM `permission` WHERE id IN (SELECT permissionId FROM role_permission WHERE roleId = #{roleId})")
    List<Permission> findById(int roleId);

    @Select("SELECT * FROM `permission`")
    List<Permission> findAll(); // 查询所有权限

    @Insert("INSERT INTO permission(`permissionName`, `url`) VALUES ( #{permissionName}, #{url})")
    void save(Permission permission); // 添加权限

    @Delete("delete from permission where id = #{id}")
    void deletePermission(String id); // 删除权限
}
