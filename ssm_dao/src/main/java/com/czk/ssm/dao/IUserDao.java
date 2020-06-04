package com.czk.ssm.dao;

import com.czk.ssm.pojo.Role;
import com.czk.ssm.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("Select  * from users where  username = #{userName}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.czk.ssm.dao.IRoleDao.findByUserId"))
    })
    UserInfo findByUserNme(String userName); // 查找用户


    @Select("select * from users")
    List<UserInfo> findAll();  // 查询所有用户

    @Insert("Insert into users (`email`,`username`,`password`,`phoneNum`,`status`)  " +
            "values ( #{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);  // 添加用户功能

    @Select("Select  * from users where  id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.czk.ssm.dao.IRoleDao.findById"))
    })
    UserInfo findById(int id);


    @Select("SELECT * FROM role WHERE id NOT IN (SELECT roleId FROM users_role WHERE userId= #{id}) ")
    List<Role> findUserByIdAndAllRole(String id);// 通过ID查询欠缺的角色列表

    @Insert("INSERT INTO users_role(userId,roleId) VALUES(#{arg0},#{arg1})")
    void addRoleToUser(String userId, String ids);// 用户添加角色
}
