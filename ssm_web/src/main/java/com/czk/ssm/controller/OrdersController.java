package com.czk.ssm.controller;

import com.czk.ssm.pojo.Orders;
import com.czk.ssm.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {


    // 分页查询订单信息
    @Secured("ROLE_USER") // 需要加前缀,只有持有USER角色才能进入
    @RequestMapping("/findAll")
    public ModelAndView findAll(
            @RequestParam(value = "page",required = true,defaultValue = "1") Integer pageNum,
            @RequestParam(value = "size",required = true,defaultValue = "4") Integer pageSize) {
        System.out.println(pageNum);
        System.out.println(pageSize);
        ModelAndView mv = new ModelAndView();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IOrdersService bean = context.getBean(IOrdersService.class);

        List<Orders> all = bean.findAll(pageNum,pageSize);
//        mv.addObject("OrdersList",all);
        //PageInfo就是一个分页 bean
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    // 详情
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam("id")String ordersId){
        ModelAndView mv = new ModelAndView();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IOrdersService bean = context.getBean(IOrdersService.class);


        Orders orders = bean.findById(ordersId);

        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;


    }
}
