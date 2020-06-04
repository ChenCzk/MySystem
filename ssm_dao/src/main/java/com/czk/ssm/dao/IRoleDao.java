package com.czk.ssm.dao;

import com.czk.ssm.pojo.Permission;
import com.czk.ssm.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

// 角色管理
public interface IRoleDao {



    @Select("select * from  role  where id in (select roleId from users_role where userId = #{userId})")
    List<Role> findByUserId(int  userId); // 根据用户ID查找对应 角色列表ADMIN、USER


    @Select("select * from  role  where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(property = "id", column = "username"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id" , javaType = List.class,many = @Many(select = "com.czk.ssm.dao.IPermissionDao.findById")),
    })
    List<Role>  findById(int userId);// 根据用户ID查找对应 角色列表+权限列表



    @Select("select * from  role  where id = #{roleId}")
    @Results({
            @Result(property = "id", column = "username"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id" , javaType = List.class,many = @Many(select = "com.czk.ssm.dao.IPermissionDao.findById")),
    })
    Role findById2(String roleId);// 根据角色ID查找对应 角色信息+权限列表



    @Select("select * from  role")
    List<Role> findAll();  // 查询所有角色


    @Insert("INSERT INTO `eesy`.`role` (`roleName`, `roleDesc`) VALUES (#{roleName}, #{roleDesc})")
    void save(Role role);  // 添加角色

    @Delete("delete from role where id= #{id}")
    void deleteRole(String id); // 删除角色

    @Select("SELECT * FROM permission WHERE id  NOT IN (SELECT permissionId FROM role_permission WHERE roleid = #{id})")
    List<Permission> findRoleByIdAndAllPermission(String id);  // 根据角色Id查询 欠缺的权限

    @Insert("INSERT INTO role_permission(roleId,permissionId) VALUES(#{arg0},#{arg1})")
    void addPermissionToRole(String roleId,String id);
}
