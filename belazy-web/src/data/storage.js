/**storage**/
const TOKEN = "TOKEN"
const LOGIN_INFO = "LOGIN_INFO"
export default {
    clearSession:function (){sessionStorage.clear()},//清除sessionStorage

    setToken:function (data){ sessionStorage.setItem(TOKEN,data) },//存储Token
    getToken:function () { return sessionStorage.getItem(TOKEN) },//获取Token

    setLoginInfo:function (data){sessionStorage.setItem(LOGIN_INFO,JSON.stringify(data)) },//存储登录信息
    getLoginInfo:function (){
        var data = sessionStorage.getItem(LOGIN_INFO);
        if(undefined!=data && null!=data){
            return JSON.parse(sessionStorage.getItem(LOGIN_INFO))
        }
        return null
    }//获取登录信息
}