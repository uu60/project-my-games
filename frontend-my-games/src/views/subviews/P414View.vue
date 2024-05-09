<template>
  <div class="p414">
    <!--    <PregameComponent ref="pregame"></PregameComponent>-->
    <div id="game_body">
      <el-row style="height: 75%">
        <el-col :span="4" class="content_center" style="height: 100%">
          <!--     上家     -->
          <el-col style="display: block">
            <el-row class="username">MRL</el-row>
            <el-row>
              <el-button style="padding: 5px; font-size: 30px" round disabled>10</el-button>
            </el-row>
          </el-col>
          <el-row>
            <el-button class="card" style="margin: 0; color: black" disabled>
              <span style="color: black;filter: grayscale(100%);">🤡</span>
              <br>
              <span style="font-size: 20px"></span>
            </el-button>
          </el-row>
        </el-col>

        <el-col :span="16" style="height: 100%">
          <!--    对家    -->
          <el-row class="username">MRL</el-row>
          <el-row>
            <el-button  style="padding: 5px; font-size: 30px" round disabled>10</el-button>
          </el-row>
          <el-row>
            <el-button class="card" style="margin: 0; color: black" disabled>
              <span>A</span>
              <br>
              <span style="font-size: 20px">♦</span>
            </el-button>
          </el-row>
          <el-row style="margin-top: 200px">
            <el-button class="card" style="margin: 0; color: black" disabled>
              <span>A</span>
              <br>
              <span style="font-size: 20px">♠️</span>
            </el-button>
          </el-row>
        </el-col>

        <el-col :span="4" class="content_center" style="height: 100%">
          <!--     下家     -->
          <el-row>
            <el-button class="card" style="margin: 0; color: black" disabled>
              <span>A</span>
              <br>
              <span style="font-size: 20px">♦</span>
            </el-button>
          </el-row>
          <el-col style="display: block">
            <el-row class="username">MRL</el-row>
            <el-row>
              <el-button style="padding: 5px; font-size: 30px" round disabled>10</el-button>
            </el-row>
          </el-col>
        </el-col>
      </el-row>

      <!--   操作区   -->
      <el-row style="height: 5%; padding: 0">
        <el-button type="warning">单挑</el-button>
        <el-button type="primary">钩岔</el-button>
        <el-button type="info">跳过</el-button>
        <el-button type="success">出牌</el-button>
      </el-row>

      <!--  手牌区    -->
      <el-row style="height: 20%; margin-left: -60px" class="content_center">
        <el-button
            :id="'holding' + idx"
            class="card"
            v-for="(item, idx) in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K']"
            :key="idx"
            :style="'z-index: ' + idx"
            @click="toggleMoveUpCard(idx)">
          <span>{{ item }}</span>
          <br>
          <span style="font-size: 20px">♠</span>
        </el-button>
      </el-row>
    </div>
  </div>
</template>

<script>

export default {
  // components: {PregameComponent},
  data() {
    return {
      gameData: {
        opponents: [],
        holding: [],
        status: 0,
        /*
        * 当前游戏状态
        * 0-决定是否要单挑
        * 1-等待对手确定单挑
        * 2-是否同意对手单挑
        * 3-该自己出牌
        * 4-该别人出牌
        * 5-本轮结束
        * 6-本局结束
        * */
        turn: 0,
        chosen: [],
        // 上家上一次出的
        last: [],
        // 对家上一次出的
        opposite: [],
        // 下家上一次出的
        next: [],
        playerNum: 1,
        totalPlayerNum: 4
      }
    }
  },
  methods: {
    toggleMoveUpCard(idx) {
      if (this.chosen == null) {
        this.chosen = [];
      }
      let clickedCard = document.getElementById("holding" + idx);
      if (this.chosen.includes(idx)) {
        this.chosen = this.chosen.filter(item => item !== idx);
        clickedCard.classList.remove("move-up");
      } else {
        this.chosen.push(idx);
        clickedCard.classList.add("move-up");
      }
    },
    waitPlayer() {

    }
  },
  beforeCreate() {
    this.$store.state.currentGameTitle = '414';
  }
}
</script>

<style scoped>
.p414 {
  display: flex;
  align-items: center;
  justify-content: center;
}

#game_body {
  width: 100%;
  height: 95%;
  text-align: center;
  border: 5px white solid;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.5);
  padding: 10px;
  margin: 50px;
}

.content_center {
  display: flex;
  justify-content: center;
  align-items: center;
}

.card {
  width: 105px;
  height: 140px;
  margin: 0 -70px 0 0;
  text-align: left;
  justify-content: flex-start;
  align-items: flex-start;
  /* 根据需要调整 */
  /* 根据需要调整 */
  padding: 10px 60px 90px 10px;
  font-size: 30px;
}

.username {
  font-size: 30px;
}

.move-up {
  transform: translateY(-20px); /* 将元素向上移动 20 像素 */
  /* 可选：添加其他样式 */
}

</style>
