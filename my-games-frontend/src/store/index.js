import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    currentGameTitle: '',
    backURL: 'http://172.20.10.2:6660',
    serverErrMsg: "服务器有点问题，稍后再试试。"
  },
  getters: {},
  mutations: {},
  actions: {},
  modules: {}
})
