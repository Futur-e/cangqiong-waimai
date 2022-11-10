<template>
  <div>
    <Header></Header>
    <!--    路由组件出口-->
    <router-view></router-view>
    <!--    在home、search显示的，在登录注册隐藏-->
    <Footer v-show="$route.meta.show"></Footer>
    <!--<Footer v-show="$route.path==='/home'||$route.path==='/search'"></Footer>-->
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  mounted() {
    this.$store.dispatch('categoryList')
  }

}

</script>

<style scoped>

</style>