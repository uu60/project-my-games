<template>
  <div>
    <el-container>
      <el-main>
        <div id="login">
          <h1 v-cloak>小pè游戏</h1>
          <el-form
            ref="form"
            :model="form"
            :rules="rules"
            label-width="80px"
            @keyup.enter.native="doLogin"
          >
            <el-form-item label="用户名" prop="username">
              <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item style="text-align: center">
              <el-button v-loading="logining" :disabled="logining" type="primary" @click="doLogin"
              >登录
              </el-button
              >
              <el-button
                style="float: right"
                type="text"
                @click="dialogFormVisible = true"
              >注册账号
              </el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </el-main>
    </el-container>
    <el-dialog :visible.sync="dialogFormVisible" title="注册账号" width="500px">
      <el-form
        ref="form1"
        :model="form1"
        :rules="rules"
        style="margin-left: 20%; margin-right: 20%"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form1.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form1.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="password">
          <div style="width: 100%; display: flex; align-items: center">
            <el-input v-model="form1.code" show-password
                      style="width: calc(100% - 120px); margin-right: 20px"></el-input>
            <el-image
              :src="codeUrl"
              style="width: 100px; height: 40px; cursor: pointer"
              @click="updateCode"></el-image>
          </div>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button v-loading="registering" :disabled="registering" type="primary" @click="doRegister"
          >注册
          </el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import md5 from "js-md5";

export default {
  data: function () {
    return {
      form: {
        username: "",
        password: "",
      },
      form1: {
        username: "",
        password: "",
        code: ""
      },
      codeUrl: this.$store.state.backURL + "/kaptcha?d=" + new Date(),
      dialogFormVisible: false,
      logining: false,
      registering: false,
      rules: {
        username: [
          {required: true, message: "用户名不能为空", trigger: "blur"},
        ],
        password: [
          {required: true, message: "密码不能为空", trigger: "blur"},
          {min: 6, message: "密码最少6位", trigger: "blur"},
        ],
      },
    };
  },
  beforeCreate() {
    if (localStorage.token) {
      this.$router.push("/home");
    }
  },
  mounted() {
    if (this.$route.params.notice) {
      this.$message({
        type: "info",
        message: "现在还没登录，先登录吧。"
      });
    }
  },
  computed: {
    hashedPwd() {
      return md5(this.form.password);
    },
    hashedPwd1() {
      return md5(this.form1.password);
    },
  },
  methods: {
    doLogin() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.logining = true;
          if (localStorage.token) {
            // console.log("forbidden relogin.");
            this.$router.push({
              name: "Home",
            });
            return;
          }
          this.$axios({
            type: "get",
            url: this.$store.state.backURL + "/login/do_login",
            params: {username: this.form.username, password: this.hashedPwd},
          })
            .then((res) => {
              console.log(res);
              // code == 200说明登录验证成功
              if (res.data.code === 200) {
                localStorage.setItem("token", res.data.token);
                localStorage.setItem("username", res.data.username);
                this.$router.push("/home");
              } else if (res.data.code === 410) {
                this.$message.error("用户不存在。");
              } else {
                this.$message.error("密码错误。");
              }
              this.logining = false;
            })
            .catch((err) => {
              this.$message.error(this.$store.state.serverErrMsg);
              console.log(err.msg);
              this.logining = false;
            });
        }
      });
    },
    doRegister() {
      this.registering = true;
      this.$refs["form1"].validate((valid) => {
        if (valid) {
          this.$axios
            .post(this.$store.state.backURL + "/register/do_register", {
              username: this.form1.username,
              password: this.hashedPwd1,
              code: this.form1.code
            })
            .then((res) => {
              if (res.data.code === 200) {
                this.$message({
                  type: "success",
                  message: "注册成功",
                });
                this.dialogFormVisible = false;
              } else if (res.data.code === 413) {
                this.$message.error("参数不正确，注册失败。");
              } else if (res.data.code === 414) {
                this.$message.error("用户名已存在。");
              } else {
                this.$message.error(this.$store.state.serverErrMsg);
              }
              this.registering = false;
            })
            .catch((err) => {
              console.log(err);
              this.$message.error(this.$store.state.serverErrMsg);
              this.registering = false;
            });
        } else {
          this.registering = false;
        }
      });
    },
    updateCode() {
      this.codeUrl = this.$store.state.backURL + "/kaptcha?d=" + new Date();
    }
  },

};
</script>

<style scoped>
.el-container {
  text-align: center;
  height: 100vh;
  min-width: 1000px;
}

#login {
  width: 100%;
  max-width: 300px;
}

.el-main {
  padding: 20px;
  height: 100%;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
}

h1 {
  text-align: center;
}
</style>
