//home模块的小仓库
import {getOrderInfo, getUserAdress} from "@/api";

const state = {
    trade:[],
    order:{}
}
// mutations是唯一修改state的地方
const mutations = {
    GETADERSS(state,trade){
        state.trade=trade
    },
    GETORDER(state,order){
        state.order = order
    }
}
// 用户处理派发action地方的，可以书写异步
const actions = {
    async getAderss({commit}) {
        let result = await getUserAdress()
        if (result.code){
            commit('GETADERSS',result.data)
        }else {
            Promise.reject(new Error('faile'))
        }

    },
    async getOrder({commit}){
        let result = await getOrderInfo()
        if (result.code==200){
            commit('GETORDER',result.data)
        }else {
            Promise.reject(new Error('faile'))
        }
    }
}
const getters = {}
export default {
    state,
    mutations,
    actions,
    getters
}
