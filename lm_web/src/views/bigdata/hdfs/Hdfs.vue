<template>
  <div class="Hdfs">
      <h2>Browse Directory</h2>

    <!-- 搜索栏 -->
    <div style="margin-top: 10px; margin-bottom: 15px; height: 80px">
      <el-input style="width: 700px" placeholder="请输入地址" v-model="params.path"></el-input>
      <el-button style="margin-left: 5px" size="mini" type="info" @click="load"><i class="el-icon-search"></i> 搜索</el-button>

      <el-button-group style="margin-left: 40px">
        <el-tooltip class="item" effect="dark" content="创建文件夹" placement="top">
          <el-button icon="el-icon-folder" @click="openCreateDir"> </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="上传文件" placement="top">
          <el-button icon="el-icon-upload" @click="uploadFileForm = true"> </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="Cut & Paste" placement="top">
          <el-button icon="el-icon-document-copy"> </el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="返回上一层" placement="top">
          <el-button icon="el-icon-s-unfold" @click="backToLevel"> </el-button>
        </el-tooltip>
      </el-button-group>

      <!-- 创建文件目录小弹框 -->
      <el-dialog title="创建文件目录" :visible.sync="dialogFormVisible" width=40%>
        <div style="margin-top: 10px">
          <el-input  placeholder="请输入路径" v-model="createPath" class="input-with-select">
            <el-select v-model="selectPath" slot="prepend" placeholder="请选择" @change="switchPath">
              <el-option v-for="item in statusLists"
                         :key="item.key"
                         :label="item.status_name"
                         :value="item.id" />
            </el-select>
          </el-input>
        </div>
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



    </div>


      <!-- 数据表单 -->
      <el-table :data="tableData" stripe>
        <el-table-column prop="type" label="Type" width="100" align="center"></el-table-column>
        <el-table-column prop="permission" label="Permission" width="100" align="center"></el-table-column>
        <el-table-column prop="owner" label="Owner" width="100" align="center"></el-table-column>
        <el-table-column prop="group" label="Group" width="100" align="center"></el-table-column>
        <el-table-column prop="len" label="Size" width="100" align="center"></el-table-column>
        <el-table-column prop="modificationTime" label="modificationTime" width="150" align="center"></el-table-column>
        <el-table-column prop="replication" label="replication" width="100" align="center"></el-table-column>
        <el-table-column prop="blockSize" label="blockSize" width="100" align="center"></el-table-column>
        <el-table-column prop="name" label="Name" width="150" align="center">
          <template v-slot = "scope">
            <el-link @click="loadRowName(scope.row.type, scope.row.name)" :underline="false">{{scope.row.name}}</el-link>
          </template>
        </el-table-column>


        <el-table-column label="操作" align="center">
          <!-- scope.row 当前行数据 -->
          <template v-slot="scope">
            <el-popconfirm style="margin-left: 5px" icon="el-icon-info" icon-color="red" title="确定删除这条数据吗？" @confirm="del(scope.row.path)">
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

<style> /* scoped 关键字使失效*/
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>


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
      // rootPath: '/',
      formLabelWidth: '70px',
      nowPath: '',//当前目录
      createPath: '',//创建文件目录路径
      dialogFormVisible: false,//创建文件目录小弹框
      selectPath: '',//下拉框中路径值
      statusLists:[],
      uploadFileForm: false,//上传小弹框
      previousLevel: '/',//上级目录
      test: ''
    }
  },
  created() {
    this.load()
    this.nowPath = this.params.path//加载页面后，将路径存入nowPath

  },

  methods: {
    /** 调用分页API,加载数据 **/
    load() {
      this.nowPath = this.params.path//进入新路径时，存入nowPath
      request.get('/hdfs/detailPathDir', {params: this.params}).then(res => {
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
    },
    /** 删除文件及文件夹 **/
    del(path) {/** 调用删除API **/ //传递ID给后端，
      request.post("/hdfs/delFile", path).then(res => {
          // console.log(this.params.path)
        if (res.code === '200') {
          this.$notify.success("删除成功")
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    },

    /** 点击创建文件夹按钮触发事件 **/
    openCreateDir(){
      //打开弹窗
      this.dialogFormVisible = true
      //刷新数组
        if(this.nowPath === "/"){
          this.statusLists = [{id:'/', status_name:'根目录'}]
        } else {
          this.statusLists = [{id: this.nowPath, status_name:'当前目录'},
                              {id:'/', status_name:'根目录'}]
        }
      //默认下拉框选项为第一个
      this.selectPath = this.statusLists[0].id
      //将value值赋到 输入框中
      this.createPath = this.selectPath
    },

    /** 点击下拉框时触发：将value值赋到 输入框中 **/
    switchPath(){
      this.createPath = this.selectPath
    },

    /** 返回上一层按钮 **/
    backToLevel(){
      //获取当前目录
      this.previousLevel = this.nowPath
      //获取上一层目录
      let lastValue = this.previousLevel.lastIndexOf('/')//最后一个/的位置
      this.previousLevel = this.previousLevel.substring(0,lastValue)
      //转到上一层目录
      if (this.previousLevel === '') {
        this.previousLevel = '/'
      }
      this.params.path = this.previousLevel
      this.load()
    },
    /** 跳转表单中目录或文件 **/
    loadRowName(type, name){
      // console.log(type)
      if (type === "directory") {
        // console.log(name)
        if (this.params.path === '/') {
          this.params.path = this.params.path + name
        } else {
          this.params.path = this.params.path + '/' + name
        }
        // console.log(this.params.path)
        this.load()
      }
      // if (dir === false) {
      //   //若不是文件夹，则查看文件详细内容
      // }
    }


  }

}

</script>
