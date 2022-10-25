<template>
  <div class="Hdfs">
      <h2>Browse Directory</h2>


    <!-- 搜索表单 -->
    <div style="margin-top: 10px; margin-bottom: 15px; height: 80px">
      <el-input style="width: 700px" placeholder="请输入地址" v-model="params.path"></el-input>
      <el-button style="margin-left: 5px" size="mini" type="info" @click="load"><i class="el-icon-search"></i> 搜索</el-button>

      <el-button-group style="margin-left: 40px">
        <el-tooltip class="item" effect="dark" content="创建文件夹" placement="top">
          <el-button icon="el-icon-folder"> </el-button>
        </el-tooltip>

        <el-tooltip class="item" effect="dark" content="上传文件" placement="top">
          <el-button icon="el-icon-upload"> </el-button>
        </el-tooltip>

        <el-tooltip class="item" effect="dark" content="Cut & Paste" placement="top">
          <el-button icon="el-icon-document-copy"> </el-button>
        </el-tooltip>

        <el-tooltip class="item" effect="dark" content="返回上一层" placement="top">
          <el-button icon="el-icon-s-unfold"> </el-button>
        </el-tooltip>
      </el-button-group>



      <!--      <el-input style="width: 240px" placeholder="请输入名称" v-model="params.name"></el-input>-->
<!--      <el-input style="width: 240px; margin-left: 5px" placeholder="请输入联系方式" v-model="params.phone"></el-input>-->
<!--      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>-->
<!--      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置</el-button>-->
<!--      <el-button style="float: right; margin-right: 70px" type="primary" @click="$router.push('/addUser')" plain><i class="el-icon-plus"></i> 新增</el-button>-->
    </div>


      <!-- 数据表单 -->
      <el-table :data="tableData" stripe>
        <el-table-column prop="permission" label="Permission" width="100" align="center"></el-table-column>
        <el-table-column prop="owner" label="Owner" width="100" align="center"></el-table-column>
        <el-table-column prop="group" label="Group" width="100" align="center"></el-table-column>

        <el-table-column prop="len" label="Size" width="100" align="center"></el-table-column>
        <el-table-column prop="modificationTime" label="modificationTime" width="150" align="center"></el-table-column>
        <el-table-column prop="replication" label="replication" width="100" align="center"></el-table-column>
        <el-table-column prop="blockSize" label="blockSize" width="100" align="center"></el-table-column>
        <el-table-column prop="name" label="Name" width="150" align="center"></el-table-column>

        <el-table-column label="操作" align="center"><!--align="right"-->
          <!-- scope.row 当前行数据 -->
          <template v-slot="scope">
            <el-popconfirm style="margin-left: 5px" icon="el-icon-info" icon-color="red" title="确定删除这条数据吗？" @confirm="del(scope.row.userid)">
              <el-button type="danger" slot="reference" icon="el-icon-delete-solid" plain></el-button>
<!--              <el-button type="text" disabled>文字按钮</el-button>-->

            </el-popconfirm>
          </template>
        </el-table-column>

      </el-table>


    <!-- 分页 -->
<!--    <div style="margin-top: 20px">-->
<!--      <el-pagination-->
<!--          background :current-page="params.pageNum" :page-size="params.pagesize"-->
<!--          layout="prev, pager, next"-->
<!--          @current-change="handleCurrentChange" :total=total>-->
<!--      </el-pagination>-->
<!--    </div>-->

  </div>
</template>




<script>
import request from "@/utils/request";

export default {
  name: 'Hdfs',
  data() {
    return {
      tableData: [],
      // total: 0,
      params: {
      //   pageNum: 1,
      //   pagesize: 10,
      //   name: '',
      //   phone: ''
        path: '/'
      }
    }
  },
  created() {
    this.load()
  },

  methods: {//方法

    load() {/** 调用分页API **/ //查询，加载数据，并分页
    // request.get('/hdfs/detailFile?path=test/createFile', {params: this.params}).then(res => {
    //   if (res.code === '200') {
    //     this.tableData = res.data.list
    //     this.total = res.data.total
    //   }
    // })
      ///hdfs/detailFile?path=test/createFile
    request.get('/hdfs/detailFile', {params: this.params}).then(res => {
      // fetch('/api/hdfs/detailFile?path=test/createFile').then(res => res.json()).then(res =>{
        console.log(res)
        if (res.code === '200') {
              this.tableData = res.data
            }
      })


    }
    // reset() {//重置到第一页数据
    //   this.params = {
    //     pageNum: 1,
    //     pagesize: 10,
    //     name: '',
    //     phone: ''
    //   }
    //   this.load() //重置后并重新加载
    // },
    // handleCurrentChange(pageNum) {//触发分页功能
    //   //点击分页按钮触发分页
    //   // console.log(pageNum)
    //   this.params.pageNum = pageNum
    //   this.load()
    // }

  }

}

</script>
