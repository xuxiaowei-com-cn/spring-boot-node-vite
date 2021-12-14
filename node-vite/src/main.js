import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import request from './utils/request'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { user } from './api/user'

const app = createApp(App)

app.use(ElementPlus)
app.use(router)

app.config.globalProperties.$request = request
app.config.globalProperties.$user = user

app.mount('#app')
