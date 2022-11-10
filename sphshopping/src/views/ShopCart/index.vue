<template>
  <div class="cart">
    <h4>全部商品</h4>
    <div class="cart-main">
      <div class="cart-th">
        <div class="cart-th1">全部</div>
        <div class="cart-th2">商品</div>
        <div class="cart-th3">单价（元）</div>
        <div class="cart-th4">数量</div>
        <div class="cart-th5">小计（元）</div>
        <div class="cart-th6">操作</div>
      </div>
      <div class="cart-body">
        <ul class="cart-list" v-for="(cart,index) in shopCartList" :key="cart.id">
          <li class="cart-list-con1">
            <input type="checkbox" name="chk_list" :checked="cart.isChecked == 1" @change="getChecked(cart,$event)">
          </li>
          <li class="cart-list-con2">
            <img :src="cart.imgUrl">
            <div class="item-msg">{{ cart.skuName }}</div>
          </li>
          <li class="cart-list-con4">
            <span class="price">{{ cart.skuPrice }}</span>
          </li>
          <li class="cart-list-con5">
            <a href="javascript:void(0)" class="mins" @click="handle(down,-1,cart)">-</a>
            <input autocomplete="off" type="text" minnum="1" class="itxt" :value="cart.skuNum"
                   @change="handle(change,$event.target.value*1,cart)">
            <a href="javascript:void(0)" class="plus" @click="handle(down,+1,cart)">+</a>
          </li>
          <li class="cart-list-con6">
            <span class="sum">{{ cart.skuNum * cart.skuPrice }}</span>
          </li>
          <li class="cart-list-con7">
            <a  class="sindelet" @click="delCartById(cart)">删除</a>
            <br>
            <a href="#none">移到收藏</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="cart-tool">
      <div class="select-all">
        <input class="chooseAll" type="checkbox" :checked="isAllCheck&&shopCartList.length>0" @change="changeAll">
        <span>全选</span>
      </div>
      <div class="option">
        <a   @click="delAll()" >删除选中的商品</a>
        <a href="#none">移到我的关注</a>
        <a href="#none">清除下柜商品</a>
      </div>
      <div class="money-box">
        <div class="chosed">已选择
          <span>0</span>件商品
        </div>
        <div class="sumprice">
          <em>总价（不含运费） ：</em>
          <i class="summoney">{{ totalPrice }}</i>
        </div>
        <div class="sumbtn">
          <router-link to=/trade>结算</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import login from "@/views/Login";

export default {
  name: 'ShopCart',
  mounted() {
    this.getData()
    // console.log(this.CartList)
  },
  methods: {
    getData() {
      this.$store.dispatch("getShopCartList")
    },
    async handle(type, disNum, cart) {
      // console.log(type)
      switch (type) {
        case "add":
          disNum = 1;
          break
        case "down":
          if (disNum > 1){
            disNum=-1
          }else {
            disNum = 0
          }
          break
        case "change":
          disNum = parseInt(disNum) - cart.skuNum;
          break

      }
      /*if (type == 'add') {
        disNum = 1;
      }
      else if(type =='change'){
        disNum = parseInt(disNum) - cart.skuNum
      }
      else if (type == 'down') {
        disNum = cart.skuNum > 1 ? -1 : 0;
      }*/
      try {
        await this.$store.dispatch("addIdAndNum",
            {
              skuId: cart.skuId, skuNum: disNum
            });
        this.getData()
      } catch (e) {
        alert("请刷新重试")
      }
    },
    async delCartById(cart) {
      try {
        await this.$store.dispatch("getDelList",cart.skuId)
        this.getData()
      } catch (error) {
        alert(error.message)
      }
    },
    async getChecked(cart,event){
      console.log(event.target.checked)
      let isChecked = event.target.checked?'1':'0'
      try {
        await this.$store.dispatch('getChecked',{skuId:cart.skuId,isChecked})
        this.getData()
      }catch (error){
        alert(error.messages)
      }
    },
    //全选（取消与确定）
    async changeAll(event){
      let isChecked = event.target.checked?'1':'0'
      await this.shopCartList.every(async (item) =>
          await this.$store.dispatch('getChecked', {skuId: item.skuId, isChecked},this.getData()))
    },
    async delAll(){
       try {
         await this.$store.dispatch("getDelAllList");
         this.getData()
       }catch (error){
         alert(error.messages)
       }
    }
  },
  computed: {
    ...mapState({
      shopCartList: state => {
        return state.shopcart.shopCartList
      }
    }),

    totalPrice() {
      let sum = 0
      this.shopCartList.forEach((item) => {
        sum += item.skuNum * item.skuPrice
      })
      return sum
    },
    isAllCheck() {
      let result =this.shopCartList.every((item) => item.isChecked == 1)
      return result
    }
  }

}
</script>

<style lang="less" scoped>
.cart {
  width: 1200px;
  margin: 0 auto;

  h4 {
    margin: 9px 0;
    font-size: 14px;
    line-height: 21px;
  }

  .cart-main {
    .cart-th {
      background: #f5f5f5;
      border: 1px solid #ddd;
      padding: 10px;
      overflow: hidden;

      & > div {
        float: left;
      }

      .cart-th1 {
        width: 25%;

        input {
          vertical-align: middle;
        }

        span {
          vertical-align: middle;
        }
      }

      .cart-th2 {
        width: 25%;
      }

      .cart-th3,
      .cart-th4,
      .cart-th5,
      .cart-th6 {
        width: 12.5%;

      }
    }

    .cart-body {
      margin: 15px 0;
      border: 1px solid #ddd;

      .cart-list {
        padding: 10px;
        border-bottom: 1px solid #ddd;
        overflow: hidden;

        & > li {
          float: left;
        }

        .cart-list-con1 {
          width: 15%;
        }

        .cart-list-con2 {
          width: 35%;

          img {
            width: 82px;
            height: 82px;
            float: left;
          }

          .item-msg {
            float: left;
            width: 150px;
            margin: 0 10px;
            line-height: 18px;
          }
        }

        .cart-list-con3 {
          width: 0;

          .item-txt {
            text-align: center;
          }
        }

        .cart-list-con4 {
          width: 10%;

        }

        .cart-list-con5 {
          width: 17%;

          .mins {
            border: 1px solid #ddd;
            border-right: 0;
            float: left;
            color: #666;
            width: 6px;
            text-align: center;
            padding: 8px;
          }

          input {
            border: 1px solid #ddd;
            width: 40px;
            height: 33px;
            float: left;
            text-align: center;
            font-size: 14px;
          }

          .plus {
            border: 1px solid #ddd;
            border-left: 0;
            float: left;
            color: #666;
            width: 6px;
            text-align: center;
            padding: 8px;
          }
        }

        .cart-list-con6 {
          width: 10%;

          .sum {
            font-size: 16px;
          }
        }

        .cart-list-con7 {
          width: 13%;

          a {
            color: #666;
          }
        }
      }
    }
  }

  .cart-tool {
    overflow: hidden;
    border: 1px solid #ddd;

    .select-all {
      padding: 10px;
      overflow: hidden;
      float: left;

      span {
        vertical-align: middle;
      }

      input {
        vertical-align: middle;
      }
    }

    .option {
      padding: 10px;
      overflow: hidden;
      float: left;

      a {
        float: left;
        padding: 0 10px;
        color: #666;
      }
    }

    .money-box {
      float: right;

      .chosed {
        line-height: 26px;
        float: left;
        padding: 0 10px;
      }

      .sumprice {
        width: 200px;
        line-height: 22px;
        float: left;
        padding: 0 10px;

        .summoney {
          color: #c81623;
          font-size: 16px;
        }
      }

      .sumbtn {
        float: right;

        a {
          display: block;
          position: relative;
          width: 96px;
          height: 52px;
          line-height: 52px;
          color: #fff;
          text-align: center;
          font-size: 18px;
          font-family: "Microsoft YaHei";
          background: #e1251b;
          overflow: hidden;
        }
      }
    }
  }
}
</style>