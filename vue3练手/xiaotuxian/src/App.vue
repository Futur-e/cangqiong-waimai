<template>
  APP <button @click="f">测试发请求</button>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'App',
  setup(){
    const f = () => {
      request('/member/profile','get',{a:10})
    }
    return{
      f
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
