<template>
  <div class="pagination">
    <button
        :disabled="pageNo ==1"
        @click="$emit('getPage',pageNo-1)">
      上一页</button>
    <button
        v-if="startAndEnd.start > 1 "
        @click="$emit('getPage',1)"
        :class="{active:pageNo == 1}">
      1</button>
    <button v-if="startAndEnd.start > 2">···</button>
    <button
        v-for="(number,index) in startAndEnd.end "
        :key="index"
        v-if="number>= startAndEnd.start"
        @click="$emit('getPage',number)"
        :class="{active:pageNo == number}">
      {{number}}</button>
    <button v-if="startAndEnd.end < totalPage-1">···</button>
    <button
        v-if="startAndEnd.end < totalPage "
        @click="$emit('getPage',totalPage)"
        :class="{active:pageNo == totalPage}">
      {{totalPage}}</button>
    <button :disabled="pageNo == totalPage" @click="$emit('getPage',pageNo+1)">下一页</button>
    <button style="margin-left: 30px">共 {{this.total}} 条
    </button>
<!--    <p>{{startAndEnd}},{{this.totalPage}}</p>-->
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Pagination",
  props: ['pageNo', 'pageSize', 'total', 'continues'],
  computed: {
    //计算出来呗=所有数据被分成多少页
    totalPage() {
      return Math.ceil(this.total / this.pageSize)
    },
    startAndEnd() {
      let start = 0
      let end = 0
      const {pageNo, totalPage, continues} = this
      if (continues > totalPage) {
        start = 1
        end = totalPage
      }else {
        start = pageNo - parseInt(continues / 2)
        end = pageNo + parseInt(continues / 2)
        if (pageNo == 1 || pageNo == 2 ){
          start = 1
          end = continues
        }
        if (end > totalPage){
          end = totalPage
          start = totalPage - continues + 1
        }

      }
      return {start,end}
    }
  },

}
</script>

<style lang="less" scoped>
.pagination {
  text-align: center;

  button {
    margin: 0 5px;
    background-color: #f4f4f5;
    color: #606266;
    outline: none;
    border-radius: 2px;
    padding: 0 4px;
    vertical-align: top;
    display: inline-block;
    font-size: 13px;
    min-width: 35.5px;
    height: 28px;
    line-height: 28px;
    cursor: pointer;
    box-sizing: border-box;
    text-align: center;
    border: 0;

    &[disabled] {
      color: #c0c4cc;
      cursor: not-allowed;
    }

    &.active {
      cursor: not-allowed;
      background-color: #409eff;
      color: #fff;
    }
  }
}

</style>