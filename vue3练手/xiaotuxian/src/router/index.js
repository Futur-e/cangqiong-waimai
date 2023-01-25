import {createRouter, createWebHashHistory} from "vue-router/dist/vue-router";

const routes =[
    // {path:'/',redirect:'/home'},
    // {path:'.home',}
]
const  router = createRouter({
    history:createWebHashHistory(),
    routes
})
export default router

