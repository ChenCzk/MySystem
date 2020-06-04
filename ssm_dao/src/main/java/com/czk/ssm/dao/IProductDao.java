package com.czk.ssm.dao;

import com.czk.ssm.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    @Select("SELECT * FROM product")
    List<Product> findAll(); // 查询产品
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)" +
            " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product); //添加产品

    @Select("Select * from Product where id = #{id}")
    Product findById(String id); // 根据ID查询产品
}
