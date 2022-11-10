import Vue from 'vue';
import VueRouter from "vue-router";
// 引入路由组件
import Login from "@/views/Login";
import Register from "@/views/Register";
import shopCart from "@/views/ShopCart";
import store from "@/store";
import trade from "@/views/Trade";
import pay from "@/views/Pay";
import paySuccess from "@/views/PaySuccess";
import center from "@/views/Center";
import myOrder from "@/views/Center/myOrder";
import groupOrder from "@/views/Center/groupOrder";

// 使用插件
Vue.use(VueRouter);
// 先把vuerouter的原型对象push，先保存一下
let originPush = VueRouter.prototype.push;
// 重写push | replace
VueRouter.prototype.push = function (location, resolve, reject) {
    if (resolve && reject) {
        originPush.call(this, location, resolve, reject);
    } else {
        originPush.call(this, location, () => {
        }, () => {
        })
    }
}

//配置路由
let router = new VueRouter({
    routes: [
        {
            path: '/home',
            component: ()=>import('@/views/Home'),
            meta: {show: true}
        },
        {
            path: '/search/:keyword?',
            component: ()=>import('@/views/Search'),
            meta: {show: true},
            name: 'search'

        },
        {
            path: '/detail/:skuId',
            component: ()=>import('@/views/Detail'),
            meta: {show: true},
            name: 'detail'
        },
        {
            path: '/AddCartSuccess',
            component: ()=>import('@/views/AddCartSuccess'),
            meta: {show: true},
            name: 'AddCartSuccess'
        },
        {
            path: '/shopcart',
            component: shopCart,
            meta: {show: true}
        },
        {
            path: '/trade',
            component: trade,
            meta: {show: true},
            beforeEnter:(to,from,next)=>{
                if (from.path == '/shopcart'){
                    next()
                }else {
                    next(false)
                }
            }
        },

        {
            path: '/pay',
            component: pay,
            meta: {show: true},
            beforeEnter:(to,from,next)=>{
                if (from.path == '/trade'){
                    next()
                }else {
                    next(false)
                }
            }
        },
        {
            path:'/paysuccess',
            component:paySuccess,
            meta:{show:true},
            beforeEnter:(to,from,next)=>{
                if (from.path == '/pay'){
                    next()
                }else {
                    next(false)
                }
            }
        },
        {
            path:'/center',
            component:center,
            meta:{show: true},
            children:[
                {
                    path:'myorder' ,
                    component:myOrder,
                    meta:{show:true}
                },
                {
                    path:'grouporder' ,
                    component:groupOrder,
                    meta:{show:true}
                },
                {
                    path:'/center',
                    redirect:'/center/myorder'
                }
            ]
        },

        {
            path: '/login',
            component: ()=>import('@/views/Login'),
            meta: {show: false}
        },
        {
            path: '/register',
            component: ()=>import('@/views/Register'),
            meta: {show: false}

        },
// 重定向，在项目启动的时候，访问/，立马定向到首页
        {
            path: '*',
            redirect: '/home'
        }
    ],

    scrollBehavior() {
        // return 期望滚动到哪个的位置
        return {y: 0}
    }
});
router.beforeEach(async (to, from, next) => {
    //用户登录了就会有token
    let token = store.state.user.token
    let name = store.state.user.userMessage.name

    if (token) {
        // store.dispatch('userMessage')
        if (to.path == '/login') {
            next('home')
        } else {
            if (name) {
                next()
            } else {
                try {
                    await store.dispatch('userMessage')
                    next()
                } catch (e) {
                    store.dispatch('loginOut')
                    next('/login')
                }
            }
        }
    } else {
        let topath = to.path
        if (topath.indexOf('/trade')!=-1|| topath.indexOf('/pay')!=-1||topath.indexOf('/center')!=-1){
            alert('请先登录')
            next('/login?redirect='+topath)
        }else{
            next()
        }
    }
})

export default router;


