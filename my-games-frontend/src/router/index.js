import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "../views/LoginView.vue";
import GameView from '../views/GameView.vue'
import OddEvenView from '../views/subviews/OddEvenView.vue'
import ErrorView from '../views/ErrorView.vue'
import ChatRoomView from "../views/subviews/ChatRoomView.vue";
import GobangView from "../views/subviews/GobangView.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView,
    meta: {
      notNeedLogin: true
    }
  }, {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      notNeedLogin: true
    }
  },
  {
    path: '/game',
    name: 'game',
    component: GameView,
    children: [
      {
        path: 'odd_even',
        name: 'odd_even',
        component: OddEvenView
      },
      {
        path: 'gobang',
        name: 'gobang',
        component: GobangView
      },
      {
        path: 'chat_room',
        name: 'chat_room',
        component: ChatRoomView
      }
    ]
  },
  {
    path: '/error',
    name: 'error',
    component: ErrorView,
    meta: {
      notNeedLogin: true
    }
  }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

// 验证登陆
router.beforeEach(function (to, from, next) {
  if (to.meta.notNeedLogin) {
    //表示不需要登录
    next(); //继续往后走
  } else {
    //页面是否登录
    if (localStorage.getItem("token")) {
      next(); //表示已经登录
    } else {
      //next可以传递一个路由对象作为参数 表示需要跳转到的页面
      next({
        name: 'login',
        params: {
          notice: true
        }
      });
    }
  }
});

export default router
