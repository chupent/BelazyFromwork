package com.belazy.basics.auth.service;

import com.belazy.basics.auth.model.LoginLog;

/**
 * @author tangcp
 */
public interface ILoginLogService {
    int insertLoginLogMapper(LoginLog loginLog);
}
