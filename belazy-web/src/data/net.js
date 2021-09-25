/**axios网络请求封装**/
import axios from 'axios';
import storage from './storage'
const baseURL = import.meta.env.VITE_APP_BASE_URL
const version = import.meta.env.VITE_APP_VERSION

var _axios = axios.create({baseURL})
_axios.interceptors.request.use(
    (config) => {
        config.headers['Access-Control-Allow-Origin'] = '*'
        let token = storage.getToken()
        if(token){
            config.headers['Authorization'] = token
        }else {
            console.warn("Authorization is null")
        }
        if(version){ //指定访问服务
            config.headers['Belazy-Version'] = version
        }
        return config
    }, (error) => {
        return Promise.reject(error)
    }
)
_axios.interceptors.response.use(
    (response) => {
        var data = response.data;
        switch (data.code){
            case 401:
                storage.clearSession();
                break
        }
        return data;
    }, (error) => {
        console.log(error)
        return Promise.reject(error);
    }
);
export default _axios;
