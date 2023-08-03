<template>
  <el-row style="background-color: #f0f0f0;padding: 10px">
    <el-col :span="2"></el-col>
    <el-col :span="7">
      <XtransferPhone></XtransferPhone>
    </el-col>
    <el-col :span="2"></el-col>
    <el-col :span="12">
      <XtransferDetails></XtransferDetails>
    </el-col>
  </el-row>
</template>

<script>
import XtransferPhone from "@/components/library/X_transferPhone";
import XtransferDetails from "@/components/library/X_transferDetails";
export default {
name:'weChat',
  components: {XtransferDetails, XtransferPhone},

}
</script>

<style scoped>

</style>
