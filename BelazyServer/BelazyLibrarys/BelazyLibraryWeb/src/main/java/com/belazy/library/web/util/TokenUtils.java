package com.belazy.library.web.util;

import com.belazy.library.model.constant.AuthConstant;
import com.belazy.library.model.dto.UserInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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
     * 获取jwt中的claims信息
     * @param token
     * @return claim
     */
    public static Claims getClaims(String token) {
        String key = Base64.getEncoder().encodeToString(AuthConstant.SIGN_KEY.getBytes());
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
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
            log.info ("获取Token异常!");
            return null;
        }
        return token;
    }
    public static UserInfoDTO getUserInfo(){
        String token = getToken();
        if (token == null) {
            log.info ("获取Token为Null!");
            return null;
        }
        try {
            Claims claims = getClaims(token);
            Object obj = claims.get (AuthConstants.USER_INFO);
            if(null!=obj){
                ObjectMapper mapper = new ObjectMapper ();
                String jsonString = mapper.writeValueAsString(obj);
                UserInfoDTO dto = mapper.readValue (jsonString,UserInfoDTO.class);
                log.info("获取UserInfoDTO成功，值为:{}", dto);
                return dto;
            }
        }catch (Exception e){
            log.info ("获取UserInfo异常!");
            return null;
        }
        return null;
    }
    /**
     * 获取请求中的userId
     * @return userId
     */
    public static String getUserId() {
        UserInfoDTO dto = getUserInfo();
        if(null!=dto){
            return dto.getId ();
        }
        return null;
    }
}
