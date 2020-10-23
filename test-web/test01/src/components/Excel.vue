<template>
<div>
   <!-- <el-button type="primary">导出</el-button> -->
   <el-popover
    placement="right"
    width="400"
    trigger="click">
    <!-- <el-table :data="gridData">
      <el-table-column width="150" property="date" label="日期"></el-table-column>
      <el-table-column width="100" property="name" label="姓名"></el-table-column>
      <el-table-column width="300" property="address" label="地址"></el-table-column>
    </el-table> -->
    <el-select v-model="selectFields" multiple  placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.value"
        :value="item.name">
      </el-option>
    </el-select>
    <el-button type="primary" @click="exportExcel">导出</el-button>
    <el-button slot="reference">导出</el-button>
  </el-popover>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="id"
      label="ID"
      width="80">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="value"
      label="值">
    </el-table-column>
    <el-table-column
      prop="code"
      label="CODE">
    </el-table-column>
    <el-table-column
      prop="info"
      label="详情">
    </el-table-column>
    <el-table-column
      prop="createTime"
      label="创建时间">
    </el-table-column>
    <el-table-column
      prop="updateTime"
      label="更新时间">
    </el-table-column>
    <el-table-column
      prop="optUser"
      label="操作人">
    </el-table-column>
    <el-table-column
      prop="desc"
      label="描述">
    </el-table-column>
    <el-table-column
      prop="col1"
      label="备用字段1">
    </el-table-column>
    <el-table-column
      prop="col2"
      label="备用字段2">
    </el-table-column>
  </el-table>
   <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagseNum"
        :page-sizes="[5,10, 20, 30, 50, 100]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total" class="fy">
      </el-pagination>
  </div>
  
</template>

<script>
export default {
  data () {
    return {
      pagseNum: 1,
      pageSize: 5,
      total:2,
      tableData: [],
      options:[],
      selectFields:[],
      params:{}
    }
  },
  created(){
    // initData();
  },
  mounted(){
    this.initData();
    this.initOptions();
  },

 
   methods: {
     exportExcel(){
      console.log(this.selectFields);
      window.open('http://127.0.0.1:8181/info/download?fields='+this.selectFields.join(','));
     },

     initOptions(){
       let that = this;
        this.$http.get('http://127.0.0.1:8181/info/getAllFields').then(
            response => {
               that.options = response.body.data;
             
            },
            response => {
                alert('请求失败');
            },
        );
     },
     initData(){
        const url = 'http://127.0.0.1:8181/info/pageData';
        let that = this;
        this.params.pageNum = this.pagseNum;
        this.params.pageSize = this.pageSize;
        this.$http.get(url,{params:this.params}).then(
            response => {
               that.tableData = response.body.data.content;
              that.total = response.body.data.totalElements;
            },
            response => {
                alert('请求失败');
            },
        );
     },

    handleSizeChange(val) {
      this.pageSize = val;
      console.log(val);
      this.initData();
      
    },
    handleCurrentChange(val) {
      this.pagseNum = val;
      console.log(val);
      this.initData();
    }
  }
}
</script>
