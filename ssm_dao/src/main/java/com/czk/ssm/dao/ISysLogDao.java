package com.czk.ssm.dao;

import com.czk.ssm.pojo.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISysLogDao {

   @Insert("INSERT INTO syslog VALUES(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
   void save(SysLog log);

   @Select("SELECT * FROM `syslog`")
    List<SysLog> findAll();
}
