<template>
  <div>
    <input v-model="loginIn.username"><br/>
    <input v-model="loginIn.smsCode"><br/>
    <button @click="login">登录</button>
    <button @click="sendCode">验证码</button>
    <button @click="logout">登出</button>
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

</style>