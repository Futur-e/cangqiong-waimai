<template>
  <el-row>
    <el-col :span="2"></el-col>
    <el-col :span="7">
      <XchatPhone></XchatPhone>
    </el-col>
    <el-col :span="2"></el-col>
    <el-col :span="12">
      <XcharDetails></XcharDetails>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  setup(){
    function handleOpen(key, keyPath) {
      console.log(key, keyPath);
    }
    function handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
    return{
      handleOpen,
      handleClose
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/*h3 {*/
/*  margin: 40px 0 0;*/
/*}*/
/*ul {*/
/*  list-style-type: none;*/
/*  padding: 0;*/
/*}*/
/*li {*/
/*  display: inline-block;*/
/*  margin: 0 10px;*/
/*}*/
/*a {*/
/*  color: #42b983;*/
/*}*/
</style>
