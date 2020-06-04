package com.czk.ssm.controller;

import com.czk.ssm.pojo.SysLog;
import com.czk.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//@Component
/**
 * 日志信息
 *      1 、id           无意义uuid
 *      2 、visitTime    访问时间
 *      3 、username     操作者用户名
 *      4 、ip           访问ip
 *      5 、url          访问资源url
 *      6 、executionTime  执行时长
 *      7 、method       访问方法
 * */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request; //在XML的RequestContextListener

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz;    //执行的类
    private Method method;  //执行的方法
    private String url;     //访问路径
    private String ip;      //IP地址
    private String userName; //当前用户名
    private SysLog log = new SysLog();
    private Long executionTime;

    /*
     * 前置通知
     *   获取开始时间，执行的类，执行的方法
     * */
    @Before("execution(* com.czk.ssm.controller.*.*(..))")
    public void before(JoinPoint jp) throws NoSuchMethodException {
        System.out.println("-----------------------------------");
        visitTime = new Date(); // 获取开始时间
        clazz = jp.getTarget().getClass(); //获取访问类
        String method_str = jp.getSignature().getName();// 获取访问方法名字符串
        Object[] args = jp.getArgs(); // 获取访问方法的对应参数

        if (args == null || args.length == 0) {
            // 获取无参方法
            method = clazz.getMethod(method_str);
        } else {
            Class[] argClass = new Class[args.length];  // 将参数数组转换为 Class对象数组
            for (int i = 0; i < args.length; i++) {
                argClass[i] = args[i].getClass();
            }
            // 获取有参数方法
            method = clazz.getMethod(method_str, argClass);
        }
    }


    // 后置通知
    @After("execution(* com.czk.ssm.controller.*.*(..))")
    public void after() {
        System.out.println("-----------------------------------");

        if (clazz!=null&&method!=null&&clazz!=LogAop.class){
            System.out.println("-----------------------------------");

            // 执行时间
            executionTime = (new Date().getTime() - visitTime.getTime());

            // 获取URL("/userController/findAll")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation !=null){
                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];  //获取URL
                }
            }

            // 获取IP
            ip = request.getRemoteAddr();

            // 获取当前用户名
            SecurityContext context = SecurityContextHolder.getContext(); // 从上下文获取登陆用户
            User principal = (User)context.getAuthentication().getPrincipal();  // 获取当前用户
            userName =    principal.getUsername();
//        userName = (String) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");


            // 赋值实体类
            log.setIp(ip);
            log.setVisitTime(visitTime);
            log.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
            log.setUrl(url);
            log.setExecutionTime(executionTime);
            log.setUsername(userName);

            // 存入数据库
            if (clazz.getName() != "com.czk.ssm.controller.SysLogController") {
                sysLogService.save(log);  // 存入数据库
            }
        }

    }


}
