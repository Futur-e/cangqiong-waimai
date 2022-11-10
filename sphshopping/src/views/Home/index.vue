<template>
  <div>
    <TypeNav/>
    <ListContainter/>
    <TodayRecommend/>
    <Rank/>
    <Like/>
    <Floor v-for="(floor,index) in floorList" :key="floor.id" :list = "floor"/>
    <brand/>
  </div>
</template>

<script>
import ListContainter from "@/views/Home/ListContainter";
import TodayRecommend from "@/views/Home/TodayRecommend";
import Rank from "@/views/Home/Rank";
import Like from "@/views/Home/Like";
import Floor from "@/views/Home/Floor";
import Brand from "@/views/Home/Brand";
import {mapState} from "vuex";
import Swiper from "swiper";
import store from "@/store";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'home',
  components: {
    ListContainter,
    TodayRecommend,
    Rank,
    Like,
    Floor,
    Brand
  },
  mounted() {
    this.$store.dispatch("getFloorList")
  },
  computed:{
    ...mapState({
      floorList:state => {
        return state.home.floorList
      }
    })
  },


}
</script>

<style scoped>

</style>