<template>
  <div id="app">
    <div id="app_content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>

export default {
  methods: {
    rejectPhone() {
      var str = navigator.userAgent
      var ipad = str.match(/(iPad).*OS\s([\d_]+)/)
      var isIphone = !ipad && str.match(/(iPhone\sOS)\s([\d_]+)/)
      var isAndroid = str.match(/(Android)\s+([\d.]+)/)
      // 或者单独判断iphone或android
      if (isIphone || isAndroid) {
        this.$router.replace({
          name: 'error',
          params: {
            errorMsg: '使用iPad或者电脑来访问'
          }
        });
      }
    }
  },
  beforeCreate() {
    // ajax请求拦截
    this.$axios.interceptors.request.use(
      (config) => {
        // 判断是否存在token，如果存在将每个页面的header都添加token
        if (localStorage.token != null && localStorage.token !== "") {
          config.headers.common["Login-Token"] = localStorage.token;
        }
        return config;
      },
      (error) => {
        // 请求错误
        return Promise.reject(error);
      }
    );
// ajax响应拦截
    this.$axios.interceptors.response.use(
      (response) => {
        if (response.data.code === 412) {
          this.$store.state.token = "";
          localStorage.removeItem("token"); //删除token
          localStorage.removeItem("username"); //删除token
          this.$router.replace({
            name: "login",
            params: {
              notice: true
            }
          });
        } else if (response.data.code === 500) {
          this.$message.error(this.$store.state.serverErrMsg);
        }
        return response;
      },
      (error) => {
        // 默认除了2XX之外都为错误
        return Promise.reject(error.response);
      }
    );
  },
  mounted() {
    this.rejectPhone();
  }
}
</script>

<style>
/* body写在scoped里会不生效 */
body {
  margin: 0;
  padding: 0;
  font-family: Verdana, Arial, Helvetica, sans-serif;
}

#app {
  background: url("~@/assets/tooth.jpg");
  background-size: 300px;
  width: 100%;
  height: 100vh;
  min-width: 1440px;
  min-height: 900px;
}

#app_content {
  width: 100%;
  height: 100%;
  background: rgba(230, 235, 228, 0.9);
}
</style>
