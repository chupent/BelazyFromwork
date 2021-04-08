package com.belazy.basics.auth.controller;

import com.belazy.basics.auth.model.in.LoginIN;
import com.belazy.basics.auth.model.vo.LoginInfoVo;
import com.belazy.library.constant.SecurityConstants;
import com.belazy.library.model.Result;
import com.belazy.library.constant.RedisConstant;
import com.belazy.library.model.enums.GrantTypeEnum;
import com.belazy.library.redis.service.RedisService;
import com.belazy.library.web.util.OkHttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tangcp
 */
@Slf4j
@Api(tags = "认证相关")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    private final RedisService redisService;
    private final ConsumerTokenServices consumerTokenServices;
    private final OkHttpClient okHttpClient;

    @Value("${oauth.clientId}")
    private String clientId;
    @Value("${oauth.clientSecret}")
    private String clientSecret;
    @Value("${oauth.authUri}")
    private String authUri;


    @PostMapping("/sendLoginSmsCode")
    @ApiOperation (value = "发送登录短信验证码")
    public Result<Boolean> sendSmsCode(@ApiParam("手机号码") @RequestParam("moible") String moible) {
        Object obj = redisService.get (RedisConstant.LOGIN_SMS_CODE_KEY + moible);
        if (!StringUtils.isEmpty (obj)) {
            return Result.fail ("已发送短信验证码，请稍后再试!");
        }
        redisService.set (RedisConstant.LOGIN_SMS_CODE_KEY + moible, "123456", RedisConstant.LOGIN_SMS_CODE_EXPIRE);
        return Result.success (true);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "登出接口")
    public Result<Boolean> logout(HttpServletRequest request) {
        String token = request.getHeader (HttpHeaders.AUTHORIZATION);
        String tokenValue = token.split (" ")[1];
        return consumerTokenServices.revokeToken (tokenValue) ? Result.success () : Result.fail ("token 失效!");
    }

    @PostMapping("/login")
    @ApiOperation (value = "登录接口",response = LoginInfoVo.class)
    public Result<LoginInfoVo> login(@RequestBody LoginIN in, HttpServletRequest request) {
        String username = in.getUsername ();
        if (StringUtils.isEmpty (username)) {
            return Result.fail ("账号不能为空!");
        }
        String mGrantType = request.getHeader (SecurityConstants.GRANT_TYPE);
        if (StringUtils.isEmpty (mGrantType)) {
            mGrantType = GrantTypeEnum.PASSWORD.val;
        }
        Map<String, String> param = new HashMap<> ();
        param.put (SecurityConstants.GRANT_TYPE, mGrantType);
        param.put (SecurityConstants.CLIENT_ID, clientId);
        param.put (SecurityConstants.CLIENT_SECRET, clientSecret);
        param.put (SecurityConstants.USERNAME, in.getUsername ());
        if (GrantTypeEnum.SMS_CODE.val.equals (mGrantType)) { //手机+短信验证码登录
            String code = in.getSmsCode ();
            if (StringUtils.isEmpty (code)) {
                return Result.fail ("验证码不能为空!");
            }
            param.put (SecurityConstants.SMS_CODE, code);
        } else {
            String pwd = in.getPassword ();
            if (StringUtils.isEmpty (pwd)) {
                return Result.fail ("密码不能为空!");
            }
            param.put (SecurityConstants.PASSWORD, pwd);
        }

        //调用认证接口
        String result = OkHttpUtil.builder ().okHttpClient (okHttpClient).build ().post (authUri, param);
        if (StringUtils.isEmpty (result)) {
            return Result.fail ();
        }
        try {
            //处理OAuth2结果集
            ObjectMapper mapper = new ObjectMapper ();
            mapper.configure (DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略匹配不是的字段
            mapper.setPropertyNamingStrategy (PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);//下滑线转驼峰

            log.info ("result==>{}", result);
            JsonNode jsonNode = mapper.readTree (result);
            if (null != jsonNode) {
                JsonNode statusNode = jsonNode.get ("status");
                if (null != statusNode) {
                    JsonNode messageNode = jsonNode.get ("message");
                    String message = messageNode.asText ();
                    String status = statusNode.asText ();
                    return Result.fail (status, message);
                }
            }
            LoginInfoVo loginInfoVo = mapper.readValue (result, LoginInfoVo.class);
            //TODO 记录登录日志
            return Result.success (loginInfoVo);
        } catch (Exception e) {
            log.error ("result==>{}", result);
            return Result.unauthorized ();
        }
    }
}
