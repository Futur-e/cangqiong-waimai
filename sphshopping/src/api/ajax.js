// 进行二次封装axios
import axios from "axios";
//二次封装的目的使用到其中的请求和拦截器

// 引入进度条插件
import nprogress from "nprogress"
import "nprogress/nprogress.css"
import store from "@/store";


// 1、利用axios对象的方法create，区创建一个axios实例对象
const ajax = axios.create({
    //配置对象
    // 基础路径，发请求的时候，路径当中会出现api
    baseURL: "/api",
    timeout: 5000,
})
//请求拦截器：在发送请求的时候，请求拦截器可以检测到，可以在请求发出之前做一下事情
ajax.interceptors.request.use((config) => {
    //config:配置对象，对象里面有一个属性很重要，header请求头
    if (localStorage.getItem('UUIDTOKEN')) {
        config.headers.userTempId = localStorage.getItem('UUIDTOKEN')
    }
    if (store.state.user.token){
        config.headers.token = localStorage.getItem('user_token')
    }

    //进度条开始动
    nprogress.start()
    return config
})
//拦截响应器
ajax.interceptors.response.use(
    (res) => {
        //服务器成功的函数：服务器响应数据回来以后，响应拦截器可以检测到，可以做一些事情
        nprogress.done()
        return res.data
    }, (error) => {
        return Promise.reject(new error('faile'))
    })
export default ajax;