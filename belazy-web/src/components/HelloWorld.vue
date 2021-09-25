<template>
  <h2>环境:{{ nodeName }}</h2>
  <h2>地址:{{ baseUrl }}</h2>
  <h2>消息:{{ msg }}</h2>
  <input v-model="loginIn.username"><br/>
  <input v-model="loginIn.smsCode"><br/>
  <button @click="login">登录</button>
  <button @click="sendCode">验证码</button>
  <button @click="logout">登出</button>
</template>

<script>
import auth from "../data/api/auth";
import storage from "../data/storage";
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      count: 0,
      nodeName:import.meta.env.VITE_APP_NAME,
      baseUrl:import.meta.env.VITE_APP_BASE_URL,
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
