<template>
  <div class="game">
    <div id="game_header">
      <el-button plain style="margin-left: 20px" type="success" @click="returnHome"><i class="el-icon-back"></i>&nbsp;返回
      </el-button>
      <span v-cloak style="margin-left: 20px; font-size: 20px">{{ this.$store.state.currentGameTitle }}</span>
    </div>
    <router-view id="router_view"></router-view>
  </div>
</template>

<script>
export default {
  methods: {
    returnHome() {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
.game {
  height: 100%;
}

#game_header {
  background: rgba(210, 238, 199, 0.5);
  width: 100%;
  height: 50px;
  display: flex;
  align-items: center;
}

#router_view {
  height: calc(100% - 50px);
}
</style>
