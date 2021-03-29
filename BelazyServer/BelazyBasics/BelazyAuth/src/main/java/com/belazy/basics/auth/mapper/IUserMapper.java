package com.belazy.basics.auth.mapper;

import com.belazy.basics.auth.model.UserDetail;
import org.apache.ibatis.annotations.Select;

/**
 * @author tangcp
 */
public interface IUserMapper {
    @Select("select * from sys_belazy_user u where u.is_deleted=0 and u.account=#{account}")
    UserDetail findUserByAccount(String account);
    @Select("select * from sys_belazy_user u where u.is_deleted=0 and u.phone=#{phone}")
    UserDetail findUserByMobile(String phone);
}
