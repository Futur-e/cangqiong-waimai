//先引入mock模块
import Mock from 'mockjs';
//引入json文件
import banner from './banner.json'
import floor from './floor.json'
//mock数据 有两个参数（请求地址，请求数据）
Mock.mock("/mock/banner",{code:200,data:banner})
Mock.mock("/mock/floor",{code:200,data:floor})