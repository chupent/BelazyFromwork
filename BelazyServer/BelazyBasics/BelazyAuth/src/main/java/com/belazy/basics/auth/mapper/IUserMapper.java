package com.belazy.basics.auth.mapper;

import com.belazy.basics.auth.model.UserDetail;
import org.apache.ibatis.annotations.Select;

/**
 * @author tangcp
 */
public interface IUserMapper {
    String SQL = "SELECT u.id  , u.account,u.phone_number,u.email , u.`password` , u.is_account_enabled , u.is_account_expired , u.is_account_locked , u.is_credentials_expired FROM sys_login_account u WHERE u.is_deleted = 0 AND \t";

    @Select(SQL + "u.account=#{account}")
    UserDetail findUserByAccount(String account);

    @Select(SQL + "u.phone_number=#{phone}")
    UserDetail findUserByMobile(String phone);

    @Select(SQL + "u.email=#{email}")
    UserDetail findUserByEmail(String email);
}
