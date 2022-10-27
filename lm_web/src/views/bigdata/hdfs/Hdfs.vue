<template>
  <div class="Hdfs">
      <h2>Browse Directory</h2>

    <!-- 搜索表单 -->
    <div style="margin-top: 10px; margin-bottom: 15px; height: 80px">
      <el-input style="width: 700px" placeholder="请输入地址" v-model="params.path"></el-input>
      <el-button style="margin-left: 5px" size="mini" type="info" @click="load"><i class="el-icon-search"></i> 搜索</el-button>

      <el-button-group style="margin-left: 40px">
        <el-tooltip class="item" effect="dark" content="创建文件夹" placement="top">
          <el-button icon="el-icon-folder" @click="dialogFormVisible = true"> </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="上传文件" placement="top">
          <el-button icon="el-icon-upload" @click="uploadFileForm = true"> </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="Cut & Paste" placement="top">
          <el-button icon="el-icon-document-copy"> </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="返回上一层" placement="top">
          <el-button icon="el-icon-s-unfold"> </el-button>
        </el-tooltip>
      </el-button-group>

      <!-- 创建文件小框框 -->
      <el-dialog title="创建文件目录" :visible.sync="dialogFormVisible" width=40%>
<!--        <el-form :model="form" style="margin-left: 10px">-->
<!--          <el-form-item label="路径" >-->
            <el-input v-model="createPath" placeholder="请输入内容" style="width: 450px">
              <template slot="prepend">/</template> <!-- 这里更新当前路径 -->
            </el-input>
<!--          </el-form-item>-->
<!--        </el-form>-->
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="createFileForm">创 建</el-button>
        </div>
      </el-dialog>

      <!-- 上传文件小框框 -->
<!--      <el-dialog title="上传文件" :visible.sync="uploadFileForm" width=40%>-->
<!--        <el-upload-->
<!--            class="upload-demo"-->
<!--            ref="upload"-->
<!--            action="https://jsonplaceholder.typicode.com/posts/"-->
<!--            :on-preview="handlePreview"-->
<!--            :on-remove="handleRemove"-->
<!--            :file-list="fileList"-->
<!--            :auto-upload="false">-->
<!--          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>-->
<!--          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>-->
<!--&lt;!&ndash;          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>&ndash;&gt;-->
<!--        </el-upload>-->

<!--        <div slot="footer" class="dialog-footer">-->
<!--          <el-button @click="uploadFileForm = false">取 消</el-button>-->
<!--          <el-button type="success" @click="uploadFileForm = false">上 传</el-button>-->
<!--        </div>-->
<!--      </el-dialog>-->






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

        <el-table-column label="操作" align="center">
          <!-- scope.row 当前行数据 -->
          <template v-slot="scope">
            <el-popconfirm style="margin-left: 5px" icon="el-icon-info" icon-color="red" title="确定删除这条数据吗？" @confirm="del(scope.row.userid)">
              <el-button type="danger" slot="reference" icon="el-icon-delete-solid" plain></el-button>
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
  name: 'Hdfs',
  data() {
    return {
      form: {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pagesize: 10,

        path: '/'
      },
      createPath: '',
      dialogFormVisible: false,
      // createFileForm: false,
      uploadFileForm: false,
      fileList: [],
      formLabelWidth: '70px'
    }
  },
  created() {
    this.load()
  },

  methods: {//方法
    /** 调用分页API,加载数据 **/
    load() {
      request.get('/hdfs/detailFile', {params: this.params}).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    /** 重置到第一页数据 **/
    reset() {
      this.params = {
        pageNum: 1,
        pagesize: 10,
        path: ''
      }
      this.load() //重置后并重新加载
    },
    /** 触发分页功能 **/
    handleCurrentChange(pageNum) {
      //点击分页按钮触发分页
      // console.log(pageNum)
      this.params.pageNum = pageNum
      this.load()
    },
    /** 创建文件夹 **/
    createFileForm(){
      request.post('/hdfs/createFile', this.createPath).then(res => {
        if (res.code === '200') {
          this.$notify.success('创建成功')
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    }

  }

}

</script>
