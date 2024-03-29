package com.belazy.basics.auth.constant;

/**
 * @author tangcp
 */
public class SecurityConstants {
    /**
     * TOKEN签名
     */
    public static final String SIGN_KEY = "$2a$10$FIypxkWFK3z2RQLxM86.Ku2oQ7WtFcV3a2FIW8F68VoralDT2fwxa";
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

    public static final String[] OPEN_API = new String[]{
            "/oauth/**",
            "/actuator/**",
            "/auth/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
    /**静态资源**/
    public static final String[] IGNORING_RESOURCES = new String[]{
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/configuration/security",
            "/swagger-ui.html",
            "/css/**",
            "/js/**",
            "/images/**",
            "/webjars/**",
            "**/favicon.ico",
            "/index"
    };


    public static final String USER_INFO = "user_info";

    public static final String GRANT_TYPE = "grant_type";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SMS_CODE = "smsCode";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
}
