module.exports = {
  productionSourceMap:false,
  //关闭eslint校验工具
  lintOnSave:false,
  //命名空间
  // namespace:true,
  //代理跨域
  devServer:{
    proxy:{
      "/api":{
        target:"http://gmall-h5-api.atguigu.cn"
      }
    }
  }
}