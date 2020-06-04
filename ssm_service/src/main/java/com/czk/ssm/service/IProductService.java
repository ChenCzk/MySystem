package com.czk.ssm.service;

import com.czk.ssm.pojo.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll(); // 查询产品信息
    void  save(Product product); // 添加产品
}
