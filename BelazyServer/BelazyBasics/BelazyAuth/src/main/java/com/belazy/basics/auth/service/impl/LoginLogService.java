package com.belazy.basics.auth.service.impl;

import com.belazy.basics.auth.mapper.ILoginLogMapper;
import com.belazy.basics.auth.model.LoginLog;
import com.belazy.basics.auth.service.ILoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tangcp
 */
@Service
public class LoginLogService implements ILoginLogService {
    @Resource
    private ILoginLogMapper iLoginLogMapper;

    @Override
    public int insertLoginLogMapper(LoginLog loginLog) {
        return iLoginLogMapper.insertLoginLogMapper (loginLog);
    }
}
