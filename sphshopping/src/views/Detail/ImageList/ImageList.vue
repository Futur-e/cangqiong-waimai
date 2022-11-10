<template>

  <div class="swiper-container" ref="mySwiper">
    <div class="swiper-wrapper">
      <div class="swiper-slide" v-for="(slide,index) in skuImageList"
           :key="slide.id">
        <img :src="slide.imgUrl" :class="{active:currentIndex == index}" @click="changeIndex(index)"/>
      </div>
    </div>
    <!-- 如果需要导航按钮 -->
    <div class="swiper-button-prev"></div>
    <div class="swiper-button-next"></div>
  </div>
</template>

<script>

import Swiper from 'swiper'

export default {
  name: "ImageList",
  data(){
    return {
      currentIndex: 0
    }
  },
  props: ['skuImageList'],
  watch: {
    skuImageList(newValue, oldValue) {
      // 通过watch监听bannerList属性的属性值得变化。
      // 如果执行了handker方法，代表组件实例身上的这个属性已经有了数据变化
      // 由于v-for执行完毕之后才会有结构，但是watch当中没办法保证执行完毕。
      // nextTick：在下次DOM更新  循环结束之后   执行延迟回调   在修改数据之后  立即使用这个方法  获取更新后的DOM
      this.$nextTick(() => {
        new Swiper(this.$refs.mySwiper, {
          direction: 'vertical', // 垂直切换选项
          slidesPerView : 3,
          slidesPerGroup : 3,
          // 如果需要前进后退按钮
          navigation:{
            nextEl:".swiper-button-next",
            prevEl:".swiper-button-prev"
          },
        })
      })
    }
  },
  methods:{
    changeIndex(index){
      this.currentIndex = index
      this.$bus.$emit('getIndex',index)
    }
  },



}
</script>

<style lang="less" scoped>
.swiper-container {
  height: 56px;
  width: 412px;
  box-sizing: border-box;
  padding: 0 12px;

  .swiper-slide {
    width: 56px;
    height: 56px;

    img {
      width: 100%;
      height: 100%;
      border: 1px solid #ccc;
      padding: 2px;
      width: 50px;
      height: 50px;
      display: block;

      &.active {
        border: 2px solid #f60;
        padding: 1px;
      }

    }
  }

  .swiper-button-next {
    left: auto;
    right: 0;
  }

  .swiper-button-prev {
    left: 0;
    right: auto;
  }

  .swiper-button-next,
  .swiper-button-prev {
    box-sizing: border-box;
    width: 12px;
    height: 56px;
    background: rgb(235, 235, 235);
    border: 1px solid rgb(204, 204, 204);
    top: 0;
    margin-top: 0;

    &::after {
      font-size: 12px;
    }
  }
}
</style>