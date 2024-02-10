import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import jquery from "jquery"
import animate from "animate.css"

Vue.use(ElementUI);
// 全局注册axios，调用方式是this.$axios
axios.defaults.withCredentials = true;
Vue.prototype.$axios = axios;
Vue.prototype.$ = jquery;
Vue.config.productionTip = false
// Vue.use(animate)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
