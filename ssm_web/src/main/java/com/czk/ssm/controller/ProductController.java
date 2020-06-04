package com.czk.ssm.controller;

import com.czk.ssm.pojo.Product;
import com.czk.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {



    @Autowired
    // 查询产品信息
    @RolesAllowed("ADMIN")  // 只允许持有ADMIN角色用户进入
    @RequestMapping("findAll")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IProductService bean = context.getBean(IProductService.class);
        List<Product> ps = bean.findAll();
        mv.addObject("productList", ps);  // 结果封装到ModelAndView
        mv.setViewName("product-list");
        return mv;
    }

    // 保存产品信息
    @RequestMapping("/save")
    public ModelAndView save(Product product){
        ModelAndView mv = new ModelAndView();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IProductService bean = context.getBean(IProductService.class);

        bean.save(product); // 添加产品
        mv.setViewName("redirect:findAll.do"); // 添加完成重新查询

        return mv;

    }
}
