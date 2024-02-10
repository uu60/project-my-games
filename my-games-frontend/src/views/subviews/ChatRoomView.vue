<template>
  <div id="chat_room">
    <div id="reminder_container">
      <p>聊天室只会展示近3天的记录（除非你不退出）</p>
    </div>
    <div id="record_container">
      <div id="record_area" style="display: inline-block; width: 60%">
        <div v-for="(item, index) in records" :key="index" style="margin-bottom: 10px">
          <span v-cloak style="color: deepskyblue; margin-right: 20px">{{ item.username }}</span>
          <span v-cloak style="color: gray;">{{ item.createTime }}</span>
          <br>
          <span v-cloak style="width: 100%; font-size: 20px; word-wrap: break-word;
	word-break: break-all;" v-html="item.content"></span>
        </div>
      </div>
      <div id="users_container">
        <h3>在线用户列表</h3>
        <div style="overflow: auto">
          <p v-for="(item, index) in onlineUsers" v-cloak :key="index">{{ item }}</p>
        </div>
      </div>
    </div>
    <div id="send_container">
      <textarea
        id="input_area"
        v-model="toSend"
        :disabled="inputDisabled"
        :placeholder="inputPlaceholder"
        maxlength="500"
        @keydown.ctrl.enter.exact="keyCtrlEnter"
        @keydown.enter.exact="keyEnter"></textarea>
      <el-button id="btn_send" :disabled="isBtnDisabled" :style=" btnStyle " type="success" @click="send">发送消息
      </el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChatRoomView",
  data() {
    return {
      wsObj: null,
      toSend: "",
      records: [],
      isAltEnter: false,
      onlineUsers: [],
      inputPlaceholder: "请输入内容（enter发送，ctrl+enter换行）",
      inputDisabled: false
    }
  },
  methods: {
    createWebSocket() {
      let wsUri = "ws://" + this.$store.state.backURL.split("http://")[1] + "/game/chat_room/chatting?login-token="
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
        console.log(evt);
        this.$message({
          type: "success",
          message: "与聊天室成功连接。"
        })
      };
      this.wsObj.onmessage = (evt) => {
        let item = JSON.parse(evt.data);
        switch (item.opt) {
          // 接收在线用户
          case 1:
            for (let username in item.onlineUsers) {
              this.onlineUsers = item.onlineUsers;
            }
            break;
          case 2:
            item.message.content = item.message.content.replace(/\n/g, '<br/>');
            this.records.push(item.message);
            setTimeout(() => {
              this.scrollToBottom();
            }, 1);
        }
      };
      this.wsObj.onclose = (evt) => {
        this.$message.info("已与聊天室断开连接。");
        this.inputDisabled = true;
        this.inputPlaceholder = "离线状态，请刷新。";
        this.onlineUsers = [];
      }
    },
    send() {
      this.wsObj.send(this.toSend);
      this.toSend = "";
      this.$("#input_area").focus();
    },
    scrollToBottom() {
      let recordArea = document.getElementById("record_area");
      recordArea.scrollTop = recordArea.scrollHeight + 1000;
    },
    keyCtrlEnter(event) {
      let area = this.$("#input_area")[0];
      let insert = area.selectionStart;
      // 拼接字符串的形式来得到需要的内容
      area.value = area.value.substr(0, insert) + content + area.value.substr(insert);
    },
    keyEnter(event) {
      event.preventDefault()
      if (!this.isBtnDisabled) {
        this.send()
      }
    },
  },
  computed: {
    btnStyle() {
      return this.isBtnDisabled ? "cursor: not-allowed" : "cursor: pointer";
    },
    isBtnDisabled() {
      return this.toSend === '' || this.toSend == null;
    }
  },
  beforeCreate() {
    this.$store.state.currentGameTitle = '聊天室'
  },
  mounted() {
    this.$axios.get(this.$store.state.backURL + "/chat_room/get_history")
      .then((res) => {
        if (res.data.code === 200) {
          let history = res.data.historyRecords;
          for (let i = 0; i < history.length; i++) {
            history[i].content = history[i].content.replace(/\n/g, '<br/>');
          }
          this.records = history;
          setTimeout(() => {
            this.scrollToBottom();
          }, 1);
        } else {
          this.$message.error("获取历史聊天记录失败，刷新试试。")
        }
      })
    this.createWebSocket();
  },
  beforeDestroy() {
    if (this.wsObj != null) {
      this.wsObj.close();
    }
  }
}
</script>

<style scoped>
#reminder_container {
  display: flex;
  justify-content: center;
}

#record_container {
  height: 70%;
  display: flex;
  justify-content: center;
}

#record_area {
  background: rgba(255, 255, 255, 0.6);
  border: black 1px solid;
  border-radius: 10px;
  width: 80%;
  overflow: auto;
  margin-bottom: 0;
  padding: 20px;
  margin-right: 20px;
}

#users_container {
  display: inline-block;
  width: calc(20% - 20px);
  text-align: center;
  background: rgba(255, 255, 255, 0.6);
  border: black 1px solid;
  border-radius: 10px;
}

#send_container {
  height: 20%;
  display: flex;
  justify-content: center;
}

#input_area {
  width: calc(70% - 20px);
  font-size: 20px;
  margin-right: 20px;
  margin-top: 20px;
  background: white;
  border: skyblue 2px solid;
  border-radius: 10px;
  overflow: auto;
  padding: 20px;
  resize: none;
}

#btn_send {
  width: 10%;
  margin-top: 20px;
}
</style>
