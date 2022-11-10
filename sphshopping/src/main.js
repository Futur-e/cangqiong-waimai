import Vue from 'vue'
import App from './App.vue'
import { Button,MessageBox} from 'element-ui';

//引入路由
import router from "@/router";
// 引入全局组件
import TypeNav from "@/components/type-nav";
import Pagination from "@/components/Pagination"
//全局组件：第一个参数 组建名字  第二个参数：那个组件
Vue.component(TypeNav.name,TypeNav)
Vue.component(Pagination.name,Pagination)
Vue.component(Button.name, Button);

//引入mockServe模块
import "@/mock/mockServe"

//引入swiper样式
import "swiper/css/swiper.min.css"

import {reqGetSearchInfo} from "@/api";
// console.log(reqGetSearchInfo({}))

//引入正则表达式验证方式
import "@/plugins/validata"

//引入仓库
import store from '@/store'


import * as API from '@/api'
Vue.config.productionTip = false
new Vue({
  render: h => h(App),
  //全局事件总线￥bus配置
  beforeCreate() {
    // this就相当于vm
    Vue.prototype.$bus = this
    Vue.prototype.$API = API
    Vue.prototype.$msgbox = MessageBox;
    Vue.prototype.$alert = MessageBox.alert;
  },
  //注册路由
  router,
  //注册仓库，组件实例身上会多一个属性$store
  store
}).$mount('#app')
