import Vue from 'vue'
import Vuex from 'vuex'
import home from "./Home";
import search from "./Search";
import datail from "./Detail";
import shopcart from "@/store/Shopcart";
import user from "@/store/User";
import trade from "@/store/Trade";
//需要使用插件一次
Vue.use(Vuex);

// 对外暴露Store类的一个实例
export default new Vuex.Store({
   modules:{
       home,
       search,
       datail,
       shopcart,
       user,
       trade
   }
})