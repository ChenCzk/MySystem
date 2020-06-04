package com.czk.ssm.controller;

import com.czk.ssm.pojo.Role;
import com.czk.ssm.pojo.UserInfo;
import com.czk.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
/**
* 用户管理
* */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_USER')") //只有持有USER角色才能访问
    public ModelAndView  findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = userService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/findById")
    @PreAuthorize("authentication.principal.username=='admin'") // 只有tom用户才能访问
    public ModelAndView  findById(int id){
        ModelAndView mv = new ModelAndView();
        // 根据用户id 查询到 角色值 , 再从角色中的id查询到权限值
        UserInfo user = userService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");  // 用户详情页面

        return mv;
    }

    // 保存用户
    @RequestMapping("/save")
    public String  save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";  // 重新查询
    }

    // 查询用户欠缺的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList =  userService.findUserByIdAndAllRole(id); // 寻缺该用户欠缺的权限
        mv.addObject("userId",id);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId,String ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:findById.do?id="+userId;
    }
}
