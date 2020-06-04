package com.czk.ssm.controller;

import com.czk.ssm.pojo.Permission;
import com.czk.ssm.pojo.Role;
import com.czk.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    // 查询所有角色列表
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> list = roleService.findAll();
        mv.addObject("roleList", list);
        mv.setViewName("role-list");
        return mv;
    }


    // 添加角色
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    // 角色详情
    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById2(id);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    // 删除角色
    @RequestMapping("/deleteRole")
    public String deleteRole(String id) {
        roleService.deleteRole(id);
        return "redirect:findAll.do";
    }


    // 查询角色欠缺的权限
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id) {
        List<Permission> permissionList = roleService.findRoleByIdAndAllPermission(id);// 根据角色Id查询 欠缺的权限

        ModelAndView mv = new ModelAndView();
        mv.addObject("roleId",id);  // 当前角色Id
        mv.addObject("permissionList",permissionList);  //当前角色欠缺的权限列表
        mv.setViewName("role-permission-add");
        return mv;
    }

     // 给角色赋予权限
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(String roleId,String ids ) {
         // roleId: 角色ID
         // ids: 权限ID
        // 给角色赋予相关权限
            roleService.addPermissionToRole(roleId,ids);
        return "redirect:findById.do?id="+roleId;


    }
}
