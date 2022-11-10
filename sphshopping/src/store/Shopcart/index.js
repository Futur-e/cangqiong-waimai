//home模块的小仓库
import {changeChecked, delShopCartList, reqShopCartList} from "@/api";

const state = {
    shopCartList:[],

}
// mutations是唯一修改state的地方
const mutations = {
    GETSHOPCARTLIST(state,shopCartList){
        state.shopCartList = shopCartList
        // console.log(shopCartList)
    },
}

// 用户处理派发action地方的，可以书写异步
const actions = {
    async getShopCartList({commit}) {
        let result = await reqShopCartList()
        // console.log("aaa",result)
        if (result.code == 200){
            commit("GETSHOPCARTLIST",result.data[0].cartInfoList)
            // console.log("aaaqq",result.data)
        }
    },
    async getDelList({commit},skuId){
        let result = await delShopCartList(skuId)
        if (result.code == 200){
            // console.log(result)
            return 'ok'
        }else {
            return Promise.reject(new Error('faile'))
        }
    },
    async getChecked({commit}, {skuId, isChecked}){
        let result = await changeChecked(skuId, isChecked)
        // console.log(skuId,isChecked)
        if (result.code == 200){
            return 'ok'
        }else {
            return Promise.reject(new Error('faile'))
        }
    },
    getDelAllList({dispatch,state}){
        let PromiseAll = [];
        state.shopCartList.forEach((item)=>{
            let promise =  item.isChecked == 1?dispatch('getDelList',item.skuId):'';
            PromiseAll.push(promise)
        })
        return Promise.all(PromiseAll)
    }

}
const getters = {
    // shopCartList(state){
    //     return state.shopCartList[0]|{}
    // }
}
export default {
    state,
    mutations,
    actions,
    getters
}
