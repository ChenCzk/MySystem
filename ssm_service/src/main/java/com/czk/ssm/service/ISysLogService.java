package com.czk.ssm.service;

import com.czk.ssm.pojo.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog log);

    List<SysLog> findAll(int pageNum, int pageSize);
}
