<template>
  <div v-if="state < 6 && state !== -1" id="pregame_container">
    <div v-if="state === 1">
      <h1>你想？</h1>
      <el-button type="success" @click="changeState(2)">创建房间</el-button>
      <el-button type="success" @click="changeState(4)">加入房间</el-button>
    </div>
    <div v-else-if="state === 2">
      <h1>起个房间名</h1>
      <el-row align="center" type="flex">
        <el-input v-model="room" class="input" maxlength="20" type="text"></el-input>
        <el-button :disabled="isBtnDisabled" :loading="btnLoading" type="success" @click="changeState(3)">
          创建
        </el-button>
      </el-row>
    </div>
    <div v-else-if="state === 3">
      <h1 v-cloak>正在等待玩家加入{{ room }}<span id="left_time">{{ leftTime }}</span></h1>
      <span id="waiting_circle" v-loading="loading"></span>
      <span v-cloak id="players_num">{{ playerNum }}/2</span>
    </div>
    <div v-else-if="state === 4">
      <h1>加入房间</h1>
      <el-row align="center" type="flex">
        <el-input v-model="room" class="input" maxlength="20" type="text"></el-input>
        <el-button :disabled="isBtnDisabled" :loading="btnLoading" type="success"
                   @click="changeState(5)">
          加入
        </el-button>
      </el-row>
    </div>
    <div v-else-if="state === 5">
      <h1>游戏准备中...</h1>
      <div id="connecting_circle_container">
        <div id="connecting_circle" v-loading="loading"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      state: 1,
      room: "",
      leftTime: 120,
      playerNum: 1,
      loading: false,
      btnLoading: false
    }
  },
  methods: {
    changeState(state) {
      if (state === 2 || state === 4) {
        this.state = state;
      } else if (state === 3) {
        // 表示想要创建房间，先检查是否重复了
        this.btnLoading = true;
        this.$axios.get(this.$store.state.backURL + "/game/" + this.$parent.gameUrl + "/create_room?room=" + this.room)
          .then((res) => {
            if (res.data.code === 200) {
              this.$message({
                type: "success",
                message: "房间创建成功了！",
              })
              let countDown = setInterval(() => {
                if (this.leftTime > 0) {
                  this.leftTime--;
                  if (this.leftTime === 0) {
                    clearInterval(countDown);
                  }
                }
              }, 1000);
              this.state = 3;
              this.btnLoading = false;
              this.loading = true;
              // 通过spring长轮询在这里等待新玩家的加入
              this.$parent.waitPlayer();
            } else if (res.data.code === 420) {
              this.$message.error(res.data.msg);
              this.btnLoading = false;
            }
          })
      } else if (state === 5) {
        this.btnLoading = true;
        this.$parent.enterRoom();
      } else {
        this.$message.error("错误的参数。")
      }
    },
    startGame() {
      this.state = 5;
      this.loading = true;
      this.$parent.gaming();
    },
    enterDuoRoom() {
      // 表示搜索房间
      this.$axios.get(this.$store.state.backURL + "/game/" + this.$parent.gameUrl + "/enter_room?room=" + this.room)
        .then((res) => {
          if (res.data.code === 200) {
            this.$message({
              type: "success",
              message: "加入房间成功！开始游戏。",
            })
            this.startGame();
            this.$parent.gameData.opponent = res.data.opponent;
          } else {
            this.$message.error(res.data.msg);
            this.btnLoading = false;
          }
        })
    },
    waitDuoPlayer() {
      this.$axios.get(this.$store.state.backURL + "/game/" + this.$parent.gameUrl + "/wait_player")
        .then((res) => {
          if (res.data.code === 200) {
            this.loading = false;
            this.playerNum = 2;
            this.$message({
              type: "success",
              message: "人数够了，开始游戏。"
            });
            this.startGame();
          } else if (res.data.code === 421) {
            this.$message.error("等待玩家超时。");
            this.state = 1;
          } else if (res.data.code === 320) {

          } else {
            this.$message.error(res.data.msg);
          }
        })
    }
  },
  computed: {
    isBtnDisabled() {
      return this.room === '' || this.room == null;
    }
  }
}
</script>

<style scoped>
#connecting_circle_container {
  display: flex;
  justify-content: center;
}

#connecting_circle {
  width: 50px;
  height: 50px;
  background: rgb(246, 240, 240);
  float: right;
}

#players_num {
  float: right;
  font-size: 30px;
}

#left_time {
  margin-left: 20px;
  color: rgb(128, 128, 128);
}

#pregame_container {
  background: rgba(255, 255, 255, 0.9);
  text-align: center;
  border: 5px rgb(210, 238, 199) solid;
  border-radius: 10px;
  padding: 20px;
}

.input {
  width: 400px;
  height: 30px;
  font-size: 25px;
}

#waiting_circle {
  width: 50px;
  height: 50px;
  float: left;
}

</style>
