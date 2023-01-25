import {createStore} from "vuex";
import user from "@/store/moudel/user";
import cart from "@/store/moudel/cart";
import {createVuexPersistedState} from "vue-persistedstate/src/main";
import category from "@/store/moudel/category";

export default createStore({
    modules:{
        user,
        cart,
        category
    },
    // 数据持久化
    plugins:[
        //默认是存储在localstorage中（storage:window.localStorage,）
        createVuexPersistedState({
            key:'userId',
            //要持久化存储的文件
            path:[user,cart]
        })
    ]
})
