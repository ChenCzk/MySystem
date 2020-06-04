package com.czk.ssm.controller;

import com.czk.ssm.pojo.SysLog;
import com.czk.ssm.service.ISysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

///sysLog/findAll.do?page=1&size=5
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(
            @RequestParam(value = "page",required = true,defaultValue = "1") Integer pageNum,
            @RequestParam(value = "size",required = true,defaultValue = "4") Integer pageSize){
        List<SysLog> list=  sysLogService.findAll(pageNum,pageSize);
        ModelAndView mv = new ModelAndView();

        // 分页
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
