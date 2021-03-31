package com.belazy.library.web.util;

import com.belazy.library.constant.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
/**
 * @author tangcp
 */
@Slf4j
public class TokenUtils {
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes ();
        return (requestAttributes == null) ? null : ((ServletRequestAttributes) requestAttributes).getRequest ();
    }
    /**
     * 获取请求中的token
     * @return token
     */
    public static String getToken() {
        String token = "";
        try{
            HttpServletRequest request = getRequest();
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization != null) {
                token = authorization.split(" ")[1];
            }
        }catch (Exception e){
            //e.printStackTrace();
            return null;
        }

        return token;
    }

    /**
     * 获取jwt中的claims信息
     *
     * @param token
     * @return claim
     */
    private static Claims getClaims(String token) {
        String key = Base64.getEncoder().encodeToString(SecurityConstants.SIGN_KEY.getBytes());
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
    /**
     * 获取请求中的userId
     *
     *
     * @return userId
     */
    public static String getUserId() {
        String token = getToken();
        if (token == null) {
            return null;
        }
        try {
            Claims claims = getClaims(token);
            Object o  = claims.get(SecurityConstants.USER_DETAIL);
            String userId = claims.get(SecurityConstants.USER_DETAIL).toString();
            log.info("获取userId成功，值为:{}", userId);
            return userId;
        }catch (Exception e){
            return null;
        }
    }
//
//    /**
//     * 获取请求中的 username
//     *
//     *
//     * @return userId
//     */
//    public static String getNickName() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        try {
//            Claims claims = getClaims(token);
//            String nickname = ChkUtil.isEmpty(claims.get(UserConstants.NICK_NAME)) ? ""
//                    : claims.get(UserConstants.NICK_NAME).toString();
//            log.debug("获取nickname成功，值为", nickname);
//            return nickname;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 获取请求中的 username
//     *
//     *
//     * @return userId
//     */
//    public static String getUserName() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        String username = (String) claims.get(UserConstants.USER_NAME);
//        log.debug("获取username成功，值为", username);
//        return username;
//    }
//
//    /**
//     * 获取请求中的 roles
//     * @return userId
//     */
//    public static ArrayList<String> getRoles() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        ArrayList<String> roles = (ArrayList<String>) claims.get(UserConstants.ROLES);
//        log.debug("获取roles成功，值为", roles);
//        return roles;
//    }
//
//    /**
//     * 获取请求中的 roles
//     * @return userId
//     */
//    public static ArrayList<String> getPermissions() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        ArrayList<String> roles = (ArrayList<String>) claims.get(UserConstants.PERMISSIONS);
//        log.debug("获取roles成功，值为", roles);
//        return roles;
//    }
//
//    /**
//     * 获取请求中的 门店ID
//     *
//     *
//     * @return userId
//     */
//    public static String getCompId() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        String compId = (String) claims.get(UserConstants.COMP_ID);
//        log.debug("获取compId成功，值为", compId);
//        return compId;
//    }
//
//    /**
//     * 获取请求中的 门店名称
//     *
//     *
//     * @return userId
//     */
//    public static String getCompName() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        String compName = (String) claims.get(UserConstants.COMP_NAME);
//        log.debug("获取compName成功，值为", compName);
//        return compName;
//    }
//
//    /**
//     * 获取请求中的 部门ID
//     *
//     *
//     * @return userId
//     */
//    public static String getDeptId() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        String deptId = (String) claims.get(UserConstants.DEPT_ID);
//        log.debug("获取deptId成功，值为", deptId);
//        return deptId;
//    }
//
//    /**
//     * 获取请求中的 部门名称
//     *
//     *
//     * @return userId
//     */
//    public static String getDeptName() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        String deptName = (String) claims.get(UserConstants.DEPT_NAME);
//        log.debug("获取deptName成功，值为", deptName);
//        return deptName;
//    }
//
//    /**
//     * 获取请求中的client_id
//     *
//     *
//     * @return userId
//     */
//    public static String getClientId() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        String clientId = (String) claims.get(UserConstants.CLIENT_ID);
//        log.debug("获取clientId成功，值为", clientId);
//        return clientId;
//    }
//
//    /**
//     * 获取请求中的roles集合
//     *
//     *
//     * @return roles
//     */
//    public static List<String> getAuthorities() {
//        String token = getToken();
//        if (token == null) {
//            return null;
//        }
//        Claims claims = getClaims(token);
//        return (List<String>) claims.get(UserConstants.AUTHORITIES);
//    }
}
