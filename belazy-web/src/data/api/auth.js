import net from "../net";
export default {
    LoginSMS: function (param) { //登录短信
        return net.get("/belazy-auth/auth/sendLoginSmsCode", param);
    },
    Login:function (data){ //登录
        return net.post("/belazy-auth/auth/login",data)
    },
    Logout:function (){ //登出
        return net.post("/belazy-auth/auth/logout")
    }
}