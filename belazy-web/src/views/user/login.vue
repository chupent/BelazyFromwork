<template>
  <div class="login_bg">
    <div class="login_container">
      <div class="login_body">
        <div class="login-form">
          <div class="login-logo">
            <h2>用户登录</h2>
          </div>
          <el-form :model="loginIn" :rules="rules" ref="ruleForm" label-width="0" size="large">
            <el-form-item prop="user">
              <el-input v-model="loginIn.username" prefix-icon="el-icon-user" clearable placeholder="用户名 / 手机 / 邮箱">
                <template #append>
                  <el-select v-model="loginIn.grantType" placeholder="请选择" style="width: 130px;">
                    <el-option label="手机登录" value="sms_code"></el-option>
                    <el-option label="密码登录" value="password"></el-option>
                  </el-select>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="loginIn.smsCode" prefix-icon="el-icon-lock" clearable show-password placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item style="margin-bottom: 10px;">
              <el-row>
                <el-col :span="12">
                  <el-checkbox label="记住我" v-model="loginIn.autologin"></el-checkbox>
                </el-col>
                <el-col :span="12" style="text-align: right;">
                  <el-button type="text">忘记密码？</el-button>
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width: 100%;" :loading="islogin" round @click="login">登 录</el-button>
            </el-form-item>
          </el-form>
<!--          <el-divider>友情链接</el-divider>-->
<!--          <div class="login-oauth">-->
<!--            <el-button size="small" type="primary" icon="el-icon-platform-eleme" circle></el-button>-->
<!--            <el-button size="small" type="success" icon="el-icon-s-goods" circle></el-button>-->
<!--            <el-button size="small" type="info" icon="el-icon-s-promotion" circle></el-button>-->
<!--            <el-button size="small" type="warning" icon="el-icon-menu" circle></el-button>-->
<!--          </div>-->
        </div>
        <div class="login-sidebox">
          <div class="login-sidebox__title">
            <h2>Belazy</h2>
          </div>
          <img src="img/loginbg.svg"/>
        </div>
      </div>
      <div class="login-footer">© aaaa bbbbb</div>
    </div>
  </div>
</template>

<script>
import auth from "@/data/api/auth";
import storage from "@/data/storage";

export default {
  name: "login",
  data() {
    return {
      loginIn:{
        username:'15721539459',
        source:'Web',
        smsCode:'123456',
        grantType:'sms_code'
      }
    }
  },
  methods:{
    login(){
      auth.Login(this.loginIn).then((res)=>{
        if(res.code==200){
          var accessToken = res.body.accessToken;
          var tokenType = res.body.tokenType;
          storage.setLoginInfo(res.body.userInfo)
          storage.setToken(tokenType+" "+ accessToken)
        }
      })
    },
    sendCode(){
      var userName = this.loginIn.username;
      auth.LoginSMS({ params: { 'moible': userName }}).then((res)=>{
        console.log("结果:"+res)
      })
    },
    logout(){
      auth.Logout().then((res)=>{
        if(res.code==200){
          storage.clearSession()
        }
      })
    }
  }
}
</script>

<style scoped>
.login_bg {position: absolute;top:0px;left:0px;right:0px;bottom:0px;}
.login_container {position: absolute;top:50%;left:50%;width: 1100px;margin: 0 auto;z-index: 1;transform: translate(-50%, -50%);}
.login_body {width: inherit;display: flex;box-shadow: 0px 20px 80px 0px rgba(0,0,0,0.3);}
.login-sidebox {width: 50%;padding: 60px;color: #fff;background:var(--el-color-primary);position: relative;overflow: hidden;}
.login-sidebox__title h2 {font-size: 30px;}
.login-sidebox__title h4 {font-size: 18px;margin-top: 10px;font-weight: normal;}
.login-sidebox__title p {font-size: 14px;margin-top:10px;line-height: 1.8;color: rgba(255,255,255,0.6);}
.login-sidebox img {position: absolute;left:-120px;bottom:-160px;width: 550px;}

.login-logo {text-align: center;margin-bottom: 30px;}
.login-logo .logo {width: 70px;height: 70px;vertical-align: bottom;}
.login-logo h2 {font-size: 24px;margin-top: 20px;color: #40485b;}

.login-title {margin-top: 20px;}
.login-title h2 {font-size: 22px;font-weight: normal;}
.login-title p {font-size: 12px;margin-top:40px;line-height: 1.8;color: rgba(255,255,255,0.8);}

.login-form {width: 50%;padding: 60px 100px;background: #fff;}
.login-oauth {display: flex;justify-content:space-around;}
.login-form .el-divider {margin-top:40px;}

.login-footer {text-align: center;color: #999;margin-top: 50px;}

.demo-user-item {display: flex;align-items: center;line-height: 1;padding:10px 0;}
.demo-user-item .icon {margin-right: 20px;}
.demo-user-item .info h2 {font-size: 14px;}
.demo-user-item .info p {color: #666;margin-top: 6px;}

@media (max-height: 650px){
  .login_bg {position: static;}
  .login_container {position: static;transform: none;margin:50px auto;}
  .login-footer {margin-bottom: 50px;}
}
@media (max-width: 1200px){
  .login_container {width: 900px;}
  .login-form {padding:60px;}
}
@media (max-width: 1000px){
  .login_container {width: 100%;background: #fff;margin: 0;transform:none;top:0px;bottom:0px;left:0px;right: 0px;}
  .login_body {box-shadow: none;}
  .login-form {width:100%;padding:60px 40px;}
  .login-sidebox {display: none;}
  .login-footer {margin-top: 0;}
}
</style>