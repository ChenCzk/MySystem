package com.czk.ssm.service.impl;

import com.czk.ssm.dao.ISysLogDao;
import com.czk.ssm.pojo.SysLog;
import com.czk.ssm.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao dao;

    @Override
    public void save(SysLog log) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        log.setId(uuid);
        dao.save(log);
    }

    @Override
    public List<SysLog> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize); //设置分页
        return dao.findAll();
    }
}
