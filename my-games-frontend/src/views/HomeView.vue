<template>
  <div class="home">
    <div>
      <h1>玩点游戏吧</h1>
      <p id="home_content_comment1">虽然界面有一点丑，所以wjn真帅</p>
      <ul id="game_menu">

        <router-link
          v-for="(item, index) in games"
          v-cloak
          :key="index" :to="item.to" tag="span"
        >
          <el-popover
            :content="item.description"
            placement="right"
            title="游戏说明"
            trigger="hover"
            width="200">
            <el-button slot="reference" style="margin-top: 10px; width: 250px; font-size: 20px" type="success">{{
                item.name
              }}
            </el-button>
          </el-popover>
          <br/>
        </router-link
        >

      </ul>
      <el-row v-if="isLogined">
        <span v-cloak style="font-size: 15px; margin-right: 10px">你好，{{ username }}</span>
        <el-button
          style="font-size: 15px"
          type="text"
          @click="logout"
        >退出登录
        </el-button
        >
      </el-row>
      <el-row v-else>
        <span v-cloak style="font-size: 15px; margin-right: 10px">你还没有登录</span>
        <el-button
          style="font-size: 15px"
          type="text"
          @click="toLogin"
        >点我去登录
        </el-button
        >
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      games: [
        {
          name: "猜单双",
          to: "/game/odd_even",
          description: "2人游戏。一个人来猜对手出的弹珠为单还是双，并决定这一回合的赌注，" +
            "赢了的一方会得到另一方猜测者押注的弹珠数量，每一个回合两人的身份会切换。" +
            "如果回合结束后其中一个人的弹珠为负数，则游戏结束。这是来自鱿鱼游戏的猜弹珠游戏。",
        },
        {
          name: "五子棋",
          to: "/game/gobang",
          description: "2人游戏。这还用解释？"
        },
        {
          name: "记忆翻牌（计划中...）",
          // to: "/game/memory_card",
          to: "",
          description: "2人游戏。"
        },
        {
          name: "辽阳麻将（无限期推迟）",
          // to: "/game/ly_mahjong",
          to: "",
          description: "4人游戏。"
        },
        {
          name: "聊天室",
          to: "/game/chat_room",
          description: "这不是个游戏，但是是游戏使用技术的实验程序。"
        }],
      isLogined: false
    }
  },
  computed: {
    username() {
      return localStorage.username;
    },
  },
  methods: {
    logout() {
      localStorage.removeItem("token"); //删除token
      localStorage.removeItem("username"); //删除token
      this.isLogined = false;
    },
    toLogin() {
      this.$router.push("/login");
    }
  },
  mounted() {
    if (this.$route.params.notice) {
      this.$message({
        type: "success",
        message: "登录成功！"
      });
    }
    this.isLogined = !(localStorage.token === '' || localStorage.token == null);
  }
}
</script>

<style scoped>
.home {
  text-align: center;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

#home_content_comment1 {
  color: gray;
}

#game_menu {
  padding: 10px;
}

li {
  margin-bottom: 5px;
}
</style>
