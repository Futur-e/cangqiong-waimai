import {getUserMessage, reqCategoryList, reqGetBannerList, reqGetFloorList} from '@/api'
//home模块的小仓库
const state = {
    //state中数据默认初始值别瞎写，服务器返回对象用{}，服务器返回数组用[]。【根据接口返回值初始化的】
    categoryList: [],
    getBannerList: [],
    floorList:[]
}
// mutations是唯一修改state的地方
const mutations = {
    CATEGORYLIST(state, categoryList) {
        state.categoryList = categoryList
    },
    GETBANNERLIST(state, getBannerList) {
        state.getBannerList = getBannerList
    },
    GETFLOORLIST(state,floorList){
      state. floorList= floorList
    }
}
// 用户处理派发action地方的，可以书写异步
const actions = {
    async categoryList({commit}) {
        let result = await reqCategoryList()
        // console.log(result)
        if (result.code == 200) {
            commit('CATEGORYLIST', result.data)
        }
    },
    // 获取轮播图的数据
    async getBannerList({commit}) {
        let result = await reqGetBannerList()
        if (result.code === 200) {
            commit('GETBANNERLIST', result.data)
        }
    },
    async getFloorList({commit}) {
        let result = await reqGetFloorList()
        if (result.code === 200) {
            commit('GETFLOORLIST', result.data)
        }
    },
}
const getters = {}
export default {
    state,
    mutations,
    actions,
    getters
}
