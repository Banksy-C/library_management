import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'


Vue.use(VueRouter)

//路由
const routes = [

  //  ====== 登录  =====
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/Login.vue'),
  },
  //  ====== 主页  =====
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/home', //通过"/"重定向至首页
    children: [ // 子路由
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/home/HomeView.vue'),
      },
      // ========== user ==========
      {
        path: 'userList',
        name: 'UserList',
        component: () => import('@/views/user/User.vue')
      },
      {
        path: 'addUser',
        name: 'AddUser',
        component: () => import('@/views/user/AddUser.vue')
      },
      {
        path: 'editUser',
        name: 'EditUser',
        component: () => import('@/views/user/EditUser.vue'),
      },
      //  ====  Admin  ====
      {
        path: 'adminList',
        name: 'AdminList',
        component: () => import('@/views/admin/Admin.vue')
      },
      {
        path: 'addAdmin',
        name: 'AddAdmin',
        component: () => import('@/views/admin/AddAdmin.vue')
      },
      {
        path: 'editAdmin',
        name: 'EditAdmin',
        component: () => import('@/views/admin/EditAdmin.vue'),
      },
    ]
  },
  {//当上面的路由都没有，就会到这里的404页面
    path: "*",
    component:() => import('@/views/404')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫


export default router
