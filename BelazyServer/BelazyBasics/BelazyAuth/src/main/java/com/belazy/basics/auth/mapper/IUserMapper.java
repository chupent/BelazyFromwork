package com.belazy.basics.auth.mapper;

import com.belazy.basics.auth.model.UserDetail;
import org.apache.ibatis.annotations.Select;

/**
 * @author tangcp
 */
public interface IUserMapper {
    String SQL = "SELECT u.id AS userId , u.account , u.`password` , u.nickname , u.`name` , u.phone , u.is_account_enabled , u.is_account_expired , u.is_account_locked , u.is_credentials_expired FROM sys_belazy_user u WHERE u.is_deleted = 0";

    @Select(SQL + " AND u.account=#{account}")
    UserDetail findUserByAccount(String account);

    @Select(SQL + " AND u.phone=#{phone}")
    UserDetail findUserByMobile(String phone);
}
