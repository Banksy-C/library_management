<template>
  <div class="User">

    <!-- 搜索表单 -->
    <div style="margin-top: 10px; margin-bottom: 15px">
      <el-input style="width: 240px" placeholder="请输入名称" v-model="params.name"></el-input>
      <el-input style="width: 240px; margin-left: 5px" placeholder="请输入联系方式" v-model="params.phone"></el-input>

      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置</el-button>
      <el-button style="float: right; margin-right: 70px" type="primary" @click="$router.push('/addUser')" plain><i class="el-icon-plus"></i> 新增</el-button>
    </div>

    <!-- 数据表单 -->
    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号" width="80"></el-table-column>
      <el-table-column prop="userid" label="用户ID" width="120"></el-table-column>
      <el-table-column prop="name" label="名称" width="80"></el-table-column>
      <el-table-column prop="age" label="年龄" width="80"></el-table-column>
      <el-table-column prop="sex" label="性别" width="80"></el-table-column>
      <el-table-column prop="phone" label="联系方式" width="100"></el-table-column>
      <el-table-column prop="address" label="地址" width="200"></el-table-column>

      <el-table-column prop="begindate" label="开始时间"></el-table-column>
<!--      <el-table-column prop="enddate" label="结束时间"></el-table-column>-->
<!--      <el-table-column prop="update" label="更新时间"></el-table-column>-->

      <el-table-column label="操作" ><!--align="right"-->
        <!-- scope.row 当前行数据 -->
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editUser?f_id=' + scope.row.id)">编辑</el-button>
          <el-popconfirm style="margin-left: 5px" icon="el-icon-info" icon-color="red" title="确定删除这条数据吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>

    <!-- 分页 -->
    <div style="margin-top: 20px">
      <el-pagination
          background :current-page="params.pageNum" :page-size="params.pagesize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange" :total=total>
      </el-pagination>
    </div>

  </div>
</template>




<script>
import request from "@/utils/request";

export default {
  name: 'User',
  data() {
    return {
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pagesize: 10,
        name: '',
        phone: ''
      }
    }
  },
  created() {
    this.load()
  },

  methods: {//方法
    del(id) {/** 调用删除API **/ //传递ID给后端，
      request.delete("/user/delete/" + id).then(res => {
        if (res.code === '200') {
          this.$notify.success("删除成功")
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    },
    load() {/** 调用分页API **/ //查询，加载数据，并分页
      request.get('/user/page', {params: this.params}).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    reset() {//重置到第一页数据
      this.params = {
        pageNum: 1,
        pagesize: 10,
        name: '',
        phone: ''
      }
      this.load() //重置后并重新加载
    },
    handleCurrentChange(pageNum) {//触发分页功能
      //点击分页按钮触发分页
      // console.log(pageNum)
      this.params.pageNum = pageNum
      this.load()
    }

  }

}

</script>
