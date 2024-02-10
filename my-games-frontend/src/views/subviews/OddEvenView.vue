<template>
  <div :style="oddEvenStyle" class="odd_even">
    <PregameComponent ref="pregame"></PregameComponent>
    <div v-if="gameData.roleFlag > -1" id="game_body">
      <h1>对手：<span v-cloak style="color: #409EFF">{{ gameData.opponent }}</span></h1>
      <div v-if="state === 1">
        <div v-if="gameData.roleFlag === 0">
          <h1>第{{ gameData.round }}回合你来出题</h1>
          <p>准备好被对手猜的弹珠。</p>
        </div>
        <div v-if="gameData.roleFlag === 1">
          <h1>第{{ gameData.round }}回合你来猜</h1>
          <p>准备好押注的弹珠。</p>
        </div>
        <div>
          <el-row style="width: 600px">
            <el-col :span="12">
              <h3>剩余的弹珠</h3>
              <div>
                <transition-group appear class="ball_container"
                                  enter-active-class="animate__animated animate__bounceInDown" leave-active-class="animate__animated animate__backOutUp">
                  <el-button v-for="item in unusedChips" :key="item" circle
                             icon="el-icon-plus" style="width: 45px; height: 45px; margin: 10px"
                             type="primary" @click="useOneChip(item)"></el-button>
                </transition-group>
              </div>
            </el-col>
            <el-col :span="12">
              <h3 v-if="gameData.roleFlag === 0">用于出题的弹珠</h3>
              <h3 v-else>用于押注的弹珠</h3>
              <div>
                <transition-group appear class="ball_container"
                                  enter-active-class="animate__animated animate__bounceInDown"
                                  leave-active-class="animate__animated animate__backOutUp">
                  <el-button v-for="item in usedChips" :key="item" circle
                             icon="el-icon-check" style="width: 45px; height: 45px; margin: 10px"
                             type="success" @click="resetOneChip(item)"></el-button>
                </transition-group>
              </div>
            </el-col>
          </el-row>
          <el-row v-if="gameData.roleFlag === 1">
            <h3>我猜对手是：</h3>
            <el-button v-cloak circle type="primary" @click="changeOddOrEven">{{ oddOrEvenStr }}</el-button>
          </el-row>
          <el-button :disabled="isBtnDisabled" style="margin-top: 20px" type="success" @click="sendData">我想好了
          </el-button>
        </div>
      </div>
      <div v-else-if="state === 2">
        <h1>请等待对手操作。</h1>
      </div>
      <div v-else-if="state === 3">
        <h1>
          <span v-cloak v-if="gameData.roleFlag === 0 && gameData.deltaChip < 0"><span
            style="color: #F56C6C">你猜错了！</span></span>
          <span v-cloak v-else-if="gameData.roleFlag === 0 && gameData.deltaChip > 0"><span
            style="color: #67C23A">你猜对了！</span></span>
          <span v-cloak v-else-if="gameData.roleFlag === 1 && gameData.deltaChip > 0"><span
            style="color: #67C23A">对手猜错了</span></span>
          <span v-else><span style="color: #F56C6C">对手猜对了！</span></span>
        </h1>
        <p v-if="gameData.roleFlag === 1">
          你出了{{ gameData.prevUsed }}，对手猜了{{
            getOpponentGuess()
          }}，押注了{{ gameData.prevOpponentUsedChip }}
        </p>
        <p v-else>
          对手出了{{ gameData.prevOpponentUsedChip }}，你猜了{{
            gameData.oddOrEvenStr
          }}，押注了{{ Math.ceil(gameData.deltaChip) }}
        </p>
        <h2 v-cloak>弹珠<span v-if="gameData.deltaChip > 0">+</span><span>{{ gameData.deltaChip }}</span></h2>
        <el-button type="success" @click="nextRound">继续</el-button>
      </div>
      <div v-else-if="state === 4">
        <h1>游戏结束</h1>
        <h1><span v-if="gameData.chip <= 0" style="color: #F56C6C">你输了</span><span v-if="gameData.chip > 0"
                                                                                   style="color: #67C23A">你赢了</span>
        </h1>
      </div>
    </div>
  </div>
</template>

<script>
import PregameComponent from "@/components/PregameComponent";

export default {
  components: {PregameComponent},
  data() {
    return {
      // 1-选择创建还是加入 2-创建房间 3-搜索房间 4-创建完成等待 5-进入游戏
      gameUrl: "odd_even",
      wsObj: null,
      state: 0,
      // waiting: false,
      usedChips: [],
      unusedChips: [],
      gameData: {
        round: 0,
        roleFlag: -1,
        chip: 0,
        usedChip: 0,
        opponent: "",
        oddOrEven: 0,
        deltaChip: 0,
        gameOver: false,
        prevOpponentUsedChip: 0,
        prevUsed: 0,
      }
    }
  },
  methods: {
    arrayElemIdx(arr, e) {
      for (let i = 0; i < arr.length; i++) {
        if (arr[i] === e) {
          return i;
        }
      }
      return -1;
    },
    useOneChip(item) {
      if (this.gameData.usedChip < this.gameData.chip) {
        this.gameData.usedChip++;
        let idx = this.arrayElemIdx(this.unusedChips, item);
        this.unusedChips.splice(idx, 1);
        for (let i = 0; i < this.usedChips.length; i++) {
          if (this.arrayElemIdx(this.usedChips, i) === -1) {
            this.usedChips.push(i);
            return;
          }
        }
        this.usedChips.push(this.usedChips.length);
      }
    },
    resetOneChip(item) {
      if (this.unusedChip < this.gameData.chip) {
        this.gameData.usedChip--;
        let idx = this.arrayElemIdx(this.usedChips, item);
        this.usedChips.splice(idx, 1);
        for (let i = 0; i < this.unusedChips.length; i++) {
          if (this.arrayElemIdx(this.unusedChips, i) === -1) {
            this.unusedChips.push(i);
            return;
          }
        }
        this.unusedChips.push(this.unusedChips.length);
      }
    },
    waitPlayer() {
      this.$refs.pregame.waitDuoPlayer();
    },
    enterRoom() {
      this.$refs.pregame.enterDuoRoom();
    },
    createWebSocket() {
      let wsUri = "ws://" + this.$store.state.backURL.split("http://")[1] + "/game/" + this.gameUrl + "/gaming?login-token="
        + localStorage.token;
      try {
        this.wsObj = new WebSocket(wsUri);
        this.initWSEventHandle();
      } catch (e) {
        this.$message.error("连接失败，刷新试试。")
      }
    },
    initWSEventHandle() {
      this.wsObj.onopen = (evt) => {
        this.$message({
          type: "success",
          message: "服务器连接成功"
        });
      };
      this.wsObj.onmessage = (evt) => {
        // if是为了只执行一次
        if (this.gameData.roleFlag === -1) {
          this.$refs.pregame.state = -1;
        }
        let item = JSON.parse(evt.data);
        switch (item.opt) {
          case 1:
            // 接收游戏对象
            this.gameData.deltaChip = item.data.chip - this.gameData.chip;
            this.gameData.chip = item.data.chip;
            this.gameData.prevUsed = this.gameData.usedChip;
            this.gameData.usedChip = 0;
            this.gameData.roleFlag = item.data.roleFlag;
            this.gameData.gameOver = item.data.gameOver;
            this.gameData.prevOpponentUsedChip = item.data.prevOpponentUsedChip;
            if (this.state === 2) {
              this.state = 3;
            } else if (this.state === 0) {
              this.nextRound();
            }
            break;
          case 2:
            // 对方离开
            this.$message.info("对手已经离开了。");
            this.wsObj.close();
            if (!this.gameData.gameOver) {
              Object.assign(this.$data, this.$options.data());
              Object.assign(this.$refs.pregame.$data, this.$refs.pregame.$options.data());
            }
            break;
          case 3:
            // 接收对手名字
            this.gameData.opponent = item.data.opponent;
            break;
        }

      };
      this.wsObj.onclose = (evt) => {
        this.$message({
          type: "info",
          message: "已与服务器断开连接。"
        })
      }
    },
    sendData() {
      this.state = 2;
      if (this.gameData.roleFlag === 1) {
        this.wsObj.send(JSON.stringify({
          usedChip: this.gameData.usedChip,
          oddOrEven: this.gameData.oddOrEven
        }));
      } else {
        this.wsObj.send(this.gameData.usedChip);
      }
    },
    gaming() {
      this.createWebSocket();
    },
    changeOddOrEven() {
      this.gameData.oddOrEven = (this.gameData.oddOrEven === 0 ? 1 : 0);
    },
    nextRound() {
      this.gameData.round++;
      this.state = this.gameData.gameOver ? 4 : 1;
      this.usedChips = [];
      this.unusedChips = [];
      for (let i = 0; i < this.unusedChip; i++) {
        setTimeout(() => {
          this.unusedChips.push(i);
        }, 100 * i);
      }
      if (this.gameData.gameOver) {
        this.$message.info("游戏结束，即将与服务器断开连接。");
      }
    },
    getOpponentGuess() {
      return (this.gameData.deltaChip > 0 && this.gameData.usedChip % 2 === 0) ? "单" : "双"
    }
  },
  computed: {
    unusedChip() {
      return this.gameData.chip - this.gameData.usedChip;
    },
    oddOrEvenStr() {
      return this.gameData.oddOrEven === 0 ? "双" : "单";
    },
    isBtnDisabled() {
      return this.gameData.roleFlag === 1 && this.gameData.usedChip === 0;
    },
    oddEvenStyle() {
      return this.gameData.roleFlag > -1 ? "display: flex; justify-content: center" : "display: flex; align-items: center; justify-content: center";
    }
  },
  beforeCreate() {
    this.$store.state.currentGameTitle = '猜单双'
  },
  beforeDestroy() {
    if (this.wsObj != null) {
      this.wsObj.close();
    }
    // 正在等待玩家加入时退出，通知服务器关闭房间
    if (this.$refs.pregame.state === 3) {
      this.$axios.get(this.$store.state.backURL + "/game/" + this.gameUrl + "/stop_waiting")
        .then((res) => {
          if (res.data.code === 200) {
            this.$message.info('房间已关闭。')
          }
        })
    }
  }
}
</script>

<style scoped>
.odd_even {
  display: flex;
  align-items: center;
  justify-content: center;
}

#game_body {
  text-align: center;
  border: 5px white solid;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.5);
  padding: 20px;
}

.ball_container {
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
  padding: 20px;
  height: 325px;
}
</style>
