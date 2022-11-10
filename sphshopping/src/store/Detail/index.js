//home模块的小仓库
import {reqDetailInfo, reqShopCarData} from "@/api";
import {getUUID} from "@/utils/uuid_token"
const state = {
    skuIdList:{},
    uuid_token:getUUID()
}
// mutations是唯一修改state的地方
const mutations = {
    GETSKULDLIST(state,skuIdList){
        state.skuIdList = skuIdList
    }
}
// 用户处理派发action地方的，可以书写异步
const actions = {
    async getSkuIdList({commit},skuId){
        let result = await reqDetailInfo(skuId)
    if(result.code == 200){
        commit('GETSKULDLIST',result.data)
        // console.log( "sssssssss",result.data)
    }
},
    async addIdAndNum({commit},{skuId,skuNum}){
        let result = await reqShopCarData(skuId,skuNum)
        // this.$store.commit('getIdAndNum',result.data)
        if (result.code ==200){
            return 'ok'
        }else {
         return Promise.reject(new Error("faile"))
        }
    }
}
const getters = {
    categoryView(state){
        return state.skuIdList.categoryView || {}
    },
    skuInfo(state){
        return state.skuIdList.skuInfo || {}
    },
    spuSaleAttrList(state){
        return state.skuIdList.spuSaleAttrList || {}
    },
}
export default {
    state,
    mutations,
    actions,
    getters
}
