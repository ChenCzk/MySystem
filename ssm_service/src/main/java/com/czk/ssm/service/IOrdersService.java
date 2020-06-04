package com.czk.ssm.service;

import com.czk.ssm.pojo.Orders;
import java.util.List;

public interface IOrdersService {
    List<Orders> findAll(int pageNum,int pageSize);

    Orders findById(String ordersId);
}
