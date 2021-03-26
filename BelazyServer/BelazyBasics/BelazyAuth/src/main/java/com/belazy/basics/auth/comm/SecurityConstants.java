package com.belazy.basics.auth.comm;

/**
 * @author tangcp
 */
public class SecurityConstants {
    /**
     * sys_oauth_client_details 字段
     */
    public static final String CLIENT_FIELDS = "client_id, client_secret, resources_ids, scope, authorized_grant_types,"
            + "web_server_redirect_uri, authorities, access_token_validity,"
            + "refresh_token_validity, addition_information, autoapprove";
    /**
     * jdbcClientDetailsService查询sql
     */
    public static final String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";

    /**
     * 默认查询语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 根据client_id查询
     */
    public static final String DEFAULT_FIND_STATEMENT_BY_CLIENT_ID = BASE_FIND_STATEMENT + " where client_id = ?";
}
