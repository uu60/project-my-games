<template>
  <div class="gobang">
    <PregameComponent ref="pregame"></PregameComponent>
    <div v-if="gameData.roleFlag > -1" id="game_body">
      <div class="content_center"><h1>对手：<span v-cloak style="color: #409EFF">{{
          gameData.opponent
        }}</span><span style="margin-left: 30px">我方：<span v-if="gameData.roleFlag === 0"
                                                          style="border-radius: 10px; padding: 5px">黑</span><span v-else
                                                                                                                  style="color: white; -webkit-text-stroke: 1px black; border-radius: 10px; padding: 5px">白</span></span>
      </h1></div>
      <canvas id="board"></canvas>
      <div class="content_center" style="overflow: hidden">
        <transition enter-active-class="animate__animated animate__backInRight"
                    leave-active-class="animate__animated animate__backOutLeft">
          <h1 v-if="noticeState === 1" key="1" style="color: #67C23A">
            轮到我方落子。
          </h1>
          <h1 v-if="noticeState === 2" key="2">
            等待对方落子...
          </h1>
          <h1 v-else key="3">游戏结束。<span v-if="gameData.winner !== gameData.roleFlag"
                                        style="color: #F56C6C">你输了。</span><span
            v-else style="color: #67C23A">你赢了。</span></h1>
        </transition>
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
      gameUrl: "gobang",
      ctx: null, // 渲染context
      cellWidth: 40,
      r: 10,
      wsObj: null,
      uploading: false, // 点击棋子后等待服务器响应
      gameData: {
        opponent: "",
        gomoku: null,
        roleFlag: -1,
        turn: -1,
        winner: -1
      },
      playerNum: 1,
      totalPlayerNum: 2
    }
  },
  methods: {
    drawBoard() {
      let board = this.$('#board')[0] // 获取棋盘element
      this.ctx = board.getContext('2d') // return a rending context
      board.width = board.height = 16 * this.cellWidth // 棋盘长宽800px
      this.ctx.strokeStyle = "#000000"; // 棋盘画线颜色
      // 绘制棋盘线
      for (let i = 0; i < 15; i++) {
        this.ctx.beginPath();
        this.ctx.moveTo((i + 1) * this.cellWidth, this.cellWidth);
        this.ctx.lineTo((i + 1) * this.cellWidth, 15 * this.cellWidth);
        this.ctx.stroke();
      }
      for (let i = 0; i < 15; i++) {
        this.ctx.beginPath();
        this.ctx.moveTo(this.cellWidth, (i + 1) * this.cellWidth);
        this.ctx.lineTo(15 * this.cellWidth, (i + 1) * this.cellWidth);
        this.ctx.stroke();
      }
    },
    handleClick(event) {
      console.log(this.gameData.winner);
      if (this.gameData.winner !== -1) {
        this.$message.warning("游戏已经结束了。");
        return;
      } else if (this.uploading) {
        this.$message.warning("落子已提交，等待服务器响应。");
        return;
      } else if (this.gameData.roleFlag !== this.gameData.turn) {
        this.$message.warning("对手的回合，请等待对手下棋。");
        return;
      }
      // x和y是点击向左上偏移(50px, 50px)，使棋盘左上角点为(0, 0)
      let x = event.offsetX - this.cellWidth
      let y = event.offsetY - this.cellWidth

      // 点击位置靠近的整数坐标值
      let xclose = this.close(x, 0, 14)
      let yclose = this.close(y, 0, 14)

      let nodearr = []
      let minxd = Math.abs(x - xclose[0] * this.cellWidth) < Math.abs(x - xclose[1] * this.cellWidth) ?
        Math.abs(x - xclose[0] * this.cellWidth) < this.r ? xclose[0] * this.cellWidth : undefined
        :
        Math.abs(x - xclose[1] * this.cellWidth) < this.r ? xclose[1] * this.cellWidth : undefined
      let maxyd = Math.abs(y - yclose[0] * this.cellWidth) < Math.abs(y - yclose[1] * this.cellWidth) ?
        Math.abs(y - yclose[0] * this.cellWidth) < this.r ? yclose[0] * this.cellWidth : undefined
        :
        Math.abs(y - yclose[1] * this.cellWidth) < this.r ? yclose[1] * this.cellWidth : undefined

      if ((minxd !== undefined) && (maxyd !== undefined)) {
        nodearr = [minxd, maxyd]
      }
      if (nodearr.length !== 0) {
        this.setNode(nodearr)
      }
    },
    close(coo, lort, rorb) {
      coo = ((coo < 0) ? 0 : coo)
      coo = ((coo > 14 * this.cellWidth) ? 14 * this.cellWidth : coo)
      let dect = rorb - lort
      if (dect === 1) {
        return [lort, rorb]
      } else {
        if (coo < (lort + Math.ceil(dect / 2)) * this.cellWidth) {
          let nlort = lort
          let nrorb = lort + Math.ceil(dect / 2)
          return this.close(coo, nlort, nrorb)
        } else {
          let nlort = lort + Math.ceil(dect / 2)
          let nrorb = rorb
          return this.close(coo, nlort, nrorb)
        }
      }
    },
    setNode(arr) {
      if (this.gameData.gomoku[arr[0] / this.cellWidth][arr[1] / this.cellWidth] !== undefined || this.gameData.roleFlag !== this.gameData.turn) {
        return
      }
      this.wsObj.send(arr[0] / this.cellWidth + "," + arr[1] / this.cellWidth);
    },
    enterRoom() {
      this.$refs.pregame.enterDuoRoom();
    },
    waitPlayer() {
      this.$refs.pregame.waitDuoPlayer();
    },
    createWebSocket() {
      let wsUri = "ws://" + this.$store.state.backURL.split("http://")[1] + "/ws/game/" + this.gameUrl + "/gaming?login-token="
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
            // 接收新的棋子 字符串1,3形式
            this.uploading = false;
            let newPiece = item.data.newPiece;
            let arr = [parseInt(newPiece.split(",")[0]) * this.cellWidth, parseInt(newPiece.split(",")[1]) * this.cellWidth];
            this.gameData.roleFlag = item.data.roleFlag;
            // 此时this.turn还没有更新
            if (this.gameData.turn === 0) {
              this.ctx.fillStyle = "black"
              this.ctx.beginPath();
              this.ctx.arc(arr[0] + this.cellWidth, arr[1] + this.cellWidth, this.r, 0, 2 * Math.PI);
              this.ctx.stroke();
              this.ctx.fill();
              this.gameData.gomoku[arr[0] / this.cellWidth][arr[1] / this.cellWidth] = this.roleFlag
              this.roleFlag = 1
            } else {
              this.ctx.fillStyle = "white"
              this.ctx.beginPath();
              this.ctx.arc(arr[0] + this.cellWidth, arr[1] + this.cellWidth, this.r, 0, 2 * Math.PI);
              this.ctx.stroke();
              this.ctx.fill();
              this.gameData.gomoku[arr[0] / this.cellWidth][arr[1] / this.cellWidth] = this.roleFlag
              this.roleFlag = 0
            }
            this.gameData.turn = item.data.turn;
            if (item.data.winner !== -1) {
              this.gameData.winner = item.data.winner;
              this.$message.info("游戏结束，即将与服务器断开连接。");
            }
            // if ()
            break;
          case 2:
            // 对方离开
            this.$message.info("对手已经离开了。");
            this.wsObj.close();
            if (this.gameData.winner === -1) {
              Object.assign(this.$data, this.$options.data());
              Object.assign(this.$refs.pregame.$data, this.$refs.pregame.$options.data());
            }
            break;
          case 3:
            // 接收对手名字和自己的颜色
            this.gameData.opponent = item.opponent;
            this.gameData.roleFlag = item.data.roleFlag;
            this.gameData.turn = item.data.turn;

            setTimeout(() => {
              // 初始化棋盘
              this.drawBoard();
              let board = this.$("#board")[0]
              board.addEventListener("click", this.handleClick)

              // 初始化棋谱
              this.gameData.gomoku = new Array(15)
              for (let i = 0; i < this.gameData.gomoku.length; i++) {
                this.gameData.gomoku[i] = new Array(15)
              }
            }, 1);
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
    gaming() {
      this.createWebSocket();
    },
  },
  computed: {
    noticeState() {
      // 游戏结束
      if (this.gameData.winner !== -1) {
        return 3;
      } else if (this.gameData.turn !== this.gameData.roleFlag) {
        return 2;
      } else {
        return 1;
      }
    }
  },
  beforeCreate() {
    this.$store.state.currentGameTitle = '五子棋'
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
.gobang {
  display: flex;
  align-items: center;
  justify-content: center;
}

#board {
  border: white 5px solid;
  border-radius: 20px;
  background: rgba(215, 176, 128, 0.8);
}

.content_center {
  display: flex;
  justify-content: center;
  text-align: center;
}
</style>
