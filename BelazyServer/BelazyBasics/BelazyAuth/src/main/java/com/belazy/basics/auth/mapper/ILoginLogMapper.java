package com.belazy.basics.auth.mapper;

import com.belazy.basics.auth.model.LoginLog;
import org.apache.ibatis.annotations.Insert;

/**
 * @author tangcp
 */
public interface ILoginLogMapper {
    @Insert ("INSERT INTO `belazy_base`.`sys_login_log` (`id`,`source`,`user_name`,`ipaddr`,`login_location`,`browser`,`os`,`version`,`device_id`,`status`,`msg`,`login_time`,`create_by`,`create_time`,`update_by`,`update_time`) " +
            "VALUES (UUID(),#{source},#{userName},#{ipaddr},#{loginLocation},#{browser},#{os},#{version},#{deviceId},#{status},#{msg},SYSDATE(),#{createBy},SYSDATE(),#{updateBy},SYSDATE());")
    int insertLoginLogMapper(LoginLog loginLog);
}
