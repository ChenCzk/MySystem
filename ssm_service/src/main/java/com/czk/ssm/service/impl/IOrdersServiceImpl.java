package com.czk.ssm.service.impl;

import com.czk.ssm.dao.IOrdersDao;
import com.czk.ssm.pojo.Orders;
import com.czk.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IOrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int pageNum,int pageSize) {

        // 参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(pageNum, pageSize);
        return ordersDao.findAll(); // 查询
    }

    @Override
    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
