# xiaotuxian


### Lints and fixes files

###用到的技术有
    vue3.0,vue-li,axios.vue-router,xuex,cuex-persistdstate(vuex数据持久化)
    @vueuse(组合api常用工具库)，算法Power Set
    dayjs(日期处理)，vee-validate（表单验证）
##vuex的持久化（vuex-persistedstate）
    npm i vuex-persistedstate
plugins:[
//默认是存储在localstorage中（storage:window.localStorage,）
createVuexPersistedState({
key:'userId',
//要持久化存储的文件
path:[user,cart]
})
]

###拦截器的配置（request.js）
初始化axios实例
请求拦截器，携带token
响应拦截器，拿出响应数据，拦截token失效
定义一个函数使用配置好的axios请求


#配置如下
const baseURL = "http://pcapi-xiaotuxian-front-devtest.itheima.net/"
const instance = axios.create({
// axios的一些实例，baseURL，timeout
baseURL,
timeout:5000
})
//拦截请求器
instance.interceptors.request.use((config)=>{
//拦截业务逻辑,进行请求配置的修改，本地有的话（vuex持久化）

    //获取用户信息对象
    const profile = store.state.user.profile
    //判断是够有token
    if (profile.token){
        // 设置token
            config.headers.Authorization = `Bearer${profile.token}`
    }
    return config
},error => {
return Promise.reject(error)
})
// 响应拦截器
instance.interceptors.response.use(res=>res.data,error=>{
//401状态码进入该状态
if (error.response && error.response.status===401){
//清空登录状态
//跳转地址
//携带当前路由地址
store.commit('user/setUser',{})
//获取当前路由地址
/*
* 1、在组件中，使用$store.path===/user,获取带参的，$store.fullpath===/user?a=10
* 2、在js中需要获取的话使用
* */
const fullPath = encodeURIComponent(router.currentRoute.value.fullPath)
router.push('/login?redirectUrl=' +fullPath)
}
return Promise.reject(error)
})
  //封装请求工具
  export  default (url,method,subminDate)=>{
  return instance({
  url,
  method,
  // 不同的方法传递的参数又说不同
  [method.toLowerCase()==='get'?'parmas':'data']:subminDate
  })
  }
