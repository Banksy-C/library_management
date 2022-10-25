<template>
  <div id="layout">

    <!-- 头部区域 -->
    <div style="height: 60px; line-height: 60px; background-color: white; margin-bottom: 2px; display: flex" >
      <div style="width: 300px">
        <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px; left: 20px">
        <span style="margin-left: 30px; font-size: 24px;">什么都有的管理系统</span>
      </div>
      <div style="flex: 1; text-align: right; padding-right: 20px">
        <el-dropdown size="medium">
          <span class="el-dropdown-link" style="cursor: pointer">
<!--            {{ admin.adminId }}-->
            管理员<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="margin-top: -5px">
            <el-dropdown-item>
              <div style="width: 50px; text-align: center;" @click="logout">退出</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <!-- 侧边栏和主题 -->
    <div style="display: flex">

      <!-- 侧边导航栏 -->
      <div style="width: 200px; min-height: calc(100vh - 62px); overflow: hidden; margin-right: 2px; background-color: white">
        <el-menu :default-active="$route.path" router class="el-menu-demo" >
          <!-- $route.path 高亮 -->
          <el-menu-item index="/"> <i class="el-icon-eleme"></i> <span>首页</span> </el-menu-item>

          <el-submenu index="algorithm">
            <template slot="title"> <i class="el-icon-edit-outline"></i> <span>demo</span> </template>
            <el-menu-item index="">图书管理</el-menu-item>
          </el-submenu>

          <el-submenu index="algorithm">
            <template slot="title"> <i class="el-icon-edit-outline"></i> <span>算法示例练习</span> </template>
            <el-menu-item index="">数据结构</el-menu-item>
          </el-submenu>

          <el-submenu index="hadoop">
            <template slot="title"> <i class="el-icon-s-marketing"></i> <span>大数据管理</span> </template>
            <el-menu-item index="/hdfsList">HDFS文件管理</el-menu-item>
          </el-submenu>

          <el-submenu index="user">
            <template slot="title"> <i class="el-icon-s-help"></i> <span>系统管理</span> </template>
            <el-menu-item index="/userList">会员管理</el-menu-item>
            <el-menu-item index="/adminList">管理员管理</el-menu-item>
          </el-submenu>

          <el-submenu index="manual">
            <template slot="title"> <i class="el-icon-document"></i> <span>项目手册</span> </template>
            <el-menu-item index="">模块说明</el-menu-item>
          </el-submenu>

        </el-menu>
      </div>

      <!-- 主题数据-->
      <div style="flex: 1; background-color: white; padding: 10px">
        <router-view/>  <!-- 二级路由 -->
      </div>

    </div>

  </div>
</template>

<script>
import Cookies from 'js-cookie'

export default {
  name: "Layout",
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}//得到
    }
  },
  methods: {
    logout() {//退出
      // 清除浏览器用户数据
      Cookies.remove('admin')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>