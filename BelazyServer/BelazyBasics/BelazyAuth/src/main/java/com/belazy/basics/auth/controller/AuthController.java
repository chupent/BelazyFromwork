package com.belazy.basics.auth.controller;

import com.belazy.basics.auth.constant.RedisConstant;
import com.belazy.basics.auth.constant.SecurityConstants;
import com.belazy.basics.auth.model.LoginLog;
import com.belazy.basics.auth.model.in.LoginIN;
import com.belazy.basics.auth.model.vo.LoginInfoVo;
import com.belazy.basics.auth.service.ILoginLogService;
import com.belazy.library.model.Result;
import com.belazy.library.model.enums.GrantTypeEnum;
import com.belazy.library.model.enums.SourceEnum;
import com.belazy.library.redis.service.RedisService;
import com.belazy.library.web.util.IpUtils;
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
    private final ILoginLogService iLoginLogService;
    @Value("${oauth.clientId}")
    private String clientId;
    @Value("${oauth.clientSecret}")
    private String clientSecret;
    @Value("${oauth.authUri}")
    private String authUri;


    @GetMapping("/sendLoginSmsCode")
    @ApiOperation (value = "发送登录短信验证码")
    public Result<Boolean> sendSmsCode(@ApiParam("手机号码") @RequestParam("moible") String moible) {
        Object obj = redisService.get (RedisConstant.LOGIN_SMS_CODE_KEY + moible);
        if (!StringUtils.isEmpty (obj)) {
            return Result.fail ("已发送短信验证码，请稍后再试!");
        }
        //TODO 调用发送短信接口
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
        String ipAddr = IpUtils.getIpAddr (request);
        if(SourceEnum.WEB.source.equals (in.getSource ())){
            String browserName = IpUtils.getBrowserName (request);
            String browserVersion = IpUtils.getBrowserVersion (request);
            in.setBrowser (browserName+" "+browserVersion);
            in.setDeviceId (IpUtils.getMac (ipAddr));
        }
        LoginLog mLog = initLoginLog(in);
        mLog.setIpaddr (ipAddr);
        mLog.setOs (IpUtils.getOsName (request));


        if (StringUtils.isEmpty (username)) {
            mLog.setStatus ("1");
            String message = "账号不能为空!";
            mLog.setMsg (message);
            iLoginLogService.insertLoginLogMapper (mLog);
            return Result.fail (message);
        }



        String mGrantType = request.getHeader (SecurityConstants.GRANT_TYPE);
        if (StringUtils.isEmpty (mGrantType)) {
            mGrantType  = in.getGrantType ();
            if (StringUtils.isEmpty (mGrantType)){
                mGrantType = GrantTypeEnum.PASSWORD.val;
            }
        }
        Map<String, String> param = new HashMap<> ();
        param.put (SecurityConstants.GRANT_TYPE, mGrantType);
        param.put (SecurityConstants.CLIENT_ID, clientId);
        param.put (SecurityConstants.CLIENT_SECRET, clientSecret);
        param.put (SecurityConstants.USERNAME, in.getUsername ());
        if (GrantTypeEnum.SMS_CODE.val.equals (mGrantType)) { //手机+短信验证码登录
            String code = in.getSmsCode ();
            if (StringUtils.isEmpty (code)) {
                String message = buildFailLoginLog (mLog,"验证码不能为空!");
                return Result.fail (message);
            }
            param.put (SecurityConstants.SMS_CODE, code);
        } else {
            String pwd = in.getPassword ();
            if (StringUtils.isEmpty (pwd)) {
                String message = buildFailLoginLog (mLog,"密码不能为空!");
                return Result.fail (message);
            }
            param.put (SecurityConstants.PASSWORD, pwd);
        }

        //调用认证接口
        String result = OkHttpUtil.builder ().okHttpClient (okHttpClient).build ().post (authUri, param);
        log.info ("result==>{}", result);
        if (StringUtils.isEmpty (result)) {
            String message = buildFailLoginLog (mLog,"认证请求失败!");
            return Result.fail (message);
        }
        try {
            //处理OAuth2结果集
            ObjectMapper mapper = new ObjectMapper ();
            mapper.configure (DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略匹配不是的字段
            mapper.setPropertyNamingStrategy (PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);//下滑线转驼峰

            JsonNode jsonNode = mapper.readTree (result);
            if (null != jsonNode) {
                JsonNode statusNode = jsonNode.get ("status");
                if (null != statusNode) {
                    JsonNode messageNode = jsonNode.get ("message");
                    String message = messageNode.asText ();
                    String status = statusNode.asText ();
                    buildFailLoginLog (mLog,message);
                    return Result.fail (status, message);
                }
            }

            LoginInfoVo loginInfoVo = mapper.readValue (result, LoginInfoVo.class);
            mLog.setMsg ("登录成功");
            iLoginLogService.insertLoginLogMapper (mLog);
            return Result.success (loginInfoVo);
        } catch (Exception e) {
            buildFailLoginLog (mLog,"认证请求失败");
            return Result.unauthorized ();
        }
    }

    private String buildFailLoginLog(LoginLog mLog,String msg) {
        mLog.setStatus ("1");
        mLog.setMsg (msg);
        log.error (msg);
        iLoginLogService.insertLoginLogMapper (mLog);
        return msg;
    }

    private LoginLog initLoginLog(LoginIN in){
        LoginLog mLog = new LoginLog ();
        mLog.setSource (in.getSource ());
        mLog.setStatus ("0");
        mLog.setUserName (in.getUsername ());
        mLog.setDeviceId (in.getDeviceId ());
        mLog.setBrowser (in.getBrowser ());
        mLog.setVersion (in.getVersion ());
        mLog.setCreateBy (in.getUsername ());
        mLog.setUpdateBy (in.getUsername ());
        mLog.setLoginLocation (in.getLoginLocation ());
        return mLog;
    }
}
