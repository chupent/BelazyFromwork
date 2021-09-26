import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from '@/App.vue'
import '@/index.css'
import router from '@/router'

const  app = createApp(App)
app.use(router) //使用路由
app.use(ElementPlus) //使用路由
app.mount('#app')
