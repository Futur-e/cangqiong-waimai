//home模块的小仓库
import {getLoginOut, getUserMessage, reqPhoneCode, reqUserLogin, reqUserRegister} from "@/api";
import {getToken, removeToken, setToken} from "@/utils/token";

const state = {
    code:"",
    token:getToken(),
    userMessage:{}
}
// mutations是唯一修改state的地方
const mutations = {
    GETPHONECODE(state,phoneCode){
        state.code = phoneCode
    },
    USERLOGIN(state,token){
        state.token = token
    },
    USERMESSAGE(state,userMessage){
        state.userMessage=userMessage
    },
    LOGINOUT(state){
        state.token = '';
        state.userMessage={};
        removeToken();
    }
}
// 用户处理派发action地方的，可以书写异步
const actions = {
    async getPhoneCode({commit}, phone) {
        let result = await reqPhoneCode(phone)
        if (result.code == 200){
            commit('GETPHONECODE',result.data)
            return 'ok'
        }else {
            return Promise.reject(new Error( 'faile'))
        }
    },
    async userRegister({commit}, user) {
        let result = await reqUserRegister(user)
        // console.log(result)
        if (result==200){
            return 'ok'
        }else {
            Promise.reject(new Error('faile'))
        }
    },
    async userLogin({commit},user){
        let result = await reqUserLogin(user)
        if (result.code ==200){
            setToken(result.data.token)
            commit('USERLOGIN',result.data.token)
            return 'ok'
        }else {
            Promise.reject(new Error('faile'))
        }
    },
    async userMessage({commit}){
        let result = await getUserMessage()
            if (result.code == 200){
                commit('USERMESSAGE',result.data)
                // console.log(result)
                return 'ok'
            }else {
                Promise.reject(new Error('faile'))
            }
    },
    async loginOut({commit}){
        let reuslt = await getLoginOut()
        if (reuslt.code == 200){
            commit("LOGINOUT")
            return 'ok'
        }else {
            Promise.reject(new Error('faile'))

        }

    }

} 
const getters = {

}
export default {
    state,
    mutations,
    actions,
    getters
}
