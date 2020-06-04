package com.czk.ssm.controller;

import com.czk.ssm.pojo.Permission;
import com.czk.ssm.service.IPermissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private  IPermissonService permissionService;

    // 查询所有角色
    @RequestMapping("/findAll")
    public  ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> list = permissionService.findAll();
        mv.addObject("permissionList",list);
        mv.setViewName("permission-list");
        return  mv;
    }

    // 添加角色
    @RequestMapping("/save")
    public  String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    // 删除角色
    @RequestMapping("/deletePermission")
    public String deletePermission(String id){
        permissionService.deletePermission(id);
        return "redirect:findAll.do";
    }


}
