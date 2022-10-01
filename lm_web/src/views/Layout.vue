<template>
  <div id="layout">

    <!-- 头部区域 -->
    <div style="height: 60px; line-height: 60px; background-color: white; margin-bottom: 2px" >
      <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px; left: 20px">
      <span style="margin-left: 30px; font-size: 24px;">图书管理系统</span>
    </div>

    <!-- 侧边栏和主题 -->
    <div style="display: flex">

      <!-- 侧边导航栏 -->
      <div style="width: 200px; min-height: calc(100vh - 62px); overflow: hidden; margin-right: 2px; background-color: white">
        <el-menu :default-active="$route.path" router class="el-menu-demo" >
          <!-- $route.path 高亮 -->
          <el-menu-item index="/"> <i class="el-icon-eleme"></i> <span>首页</span> </el-menu-item>

          <el-submenu index="user">
            <template slot="title"> <i class="el-icon-s-help"></i> <span>管理</span> </template>
            <el-menu-item index="/userList">会员管理</el-menu-item>
            <el-menu-item index="/adminList">管理员管理</el-menu-item>
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
export default {
  name: "Layout"
}
</script>

<style scoped>

</style>