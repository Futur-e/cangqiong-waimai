//当前的这个模块：API接口同意的管理
import requests from "./ajax";
import mockRequests from "./mockAjax"
//三级联动接口
//  /api/product/getBaseCategoryList  get 参数
//发请求:axios发送请求返回结果Promise
export  const reqCategoryList = ()=>requests.get('/product/getBaseCategoryList');
export const reqGetBannerList = ()=>mockRequests.get('/banner')
export const reqGetFloorList = ()=>mockRequests.get('/floor')

//搜索模块接口数据
export const reqGetSearchInfo = (params)=>requests({url:'/list',method:"POST",data:params})
//获取详情页数据
export const reqDetailInfo = (skuId) => requests.get(`/item/${skuId}`)
//获取购物车的id和数量num
export const reqShopCarData = (skuId,skuNum)=>requests.post(`/cart/addToCart/${ skuId }/${ skuNum }`)
// 获取购物车列表
export const reqShopCartList = ()=>requests.get("/cart/cartList")
//删除
export const delShopCartList = (skuId)=>requests.delete(`/cart/deleteCart/${skuId}`)
//改变状态
export const changeChecked = (skuID,isChecked)=>requests.get(`/cart/checkCart/${skuID}/${isChecked}`)
//获取验证码
export const reqPhoneCode = (phone)=>requests.get(`/user/passport/sendCode/${phone}`)
//注册请求
export const reqUserRegister = (data)=>requests.post("/user/passport/register", data)
//登录
export const reqUserLogin = (data)=>requests.post("/user/passport/login",data)
//主页获取用户信息
export const getUserMessage = ()=>requests.get("/user/passport/auth/getUserInfo")
//退出登陆
export const getLoginOut = ()=>requests.get("/user/passport/logout")
//获取
export const getUserAdress=()=>requests.get("/user/userAddress/auth/findUserAddressList")
//获取商品订单
export const getOrderInfo=()=>requests.get('/order/auth/trade')
//提交订单
export const reqOrderList=(tradeNo,data)=>requests.post(`/order/auth/submitOrder?tradeNo=${tradeNo}`,data)
//获取支付信息
export const getOrderList=(orderId)=>requests.get(`/payment/weixin/createNative/${orderId}`)
//获取支付状态
export const getPayState = (orderId)=>requests.get(`/payment/weixin/queryPayStatus/${orderId}`)
//获取订单列表
export const getPayList = (page, limit)=>requests.get(`/order/auth/${page}/${limit}`)