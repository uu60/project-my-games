import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import jquery from "jquery"

Vue.use(ElementUI);
// 全局注册axios，调用方式是this.$axios
axios.defaults.withCredentials = true;
Vue.prototype.$axios = axios;
Vue.prototype.$ = jquery;
Vue.config.productionTip = false
// Vue.use(animate)

// 配置axios前置拦截器，作用是让所有axios请求携带token，后台需要token校验是否登录
axios.interceptors.request.use(config => {
  // 1.从缓存中获取到token,这里的Authorization时登录时你给用户设置token的键值
  let authorization = localStorage.getItem("token");
  // 2.如果token不为null，那么设置到请求头中，此处哪怕为null，我们也不进行处理，因为后台会进行拦截
  if (authorization) {
    //后台给登录用户设置的token的键时什么，headers['''']里的键也应该保持一致
    config.headers['Authorization'] = authorization;
  }
  // 3.放行
  return config;
}, error => {
//失败后抛出错误
  Promise.reject(error);
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
