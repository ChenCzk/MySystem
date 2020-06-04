package com.czk.ssm.dao;

import com.czk.ssm.pojo.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {
    @Select("select * from member where id = #{id}")
    Member findById(String id);
}
