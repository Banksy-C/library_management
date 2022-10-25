import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'
import Cookies from "js-cookie";

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
        component: () => import('@/views/management/user/User.vue')
      },
      {
        path: 'addUser',
        name: 'AddUser',
        component: () => import('@/views/management/user/AddUser.vue')
      },
      {
        path: 'editUser',
        name: 'EditUser',
        component: () => import('@/views/management/user/EditUser.vue'),
      },
      //  ====  Admin  ====
      {
        path: 'adminList',
        name: 'AdminList',
        component: () => import('@/views/management/admin/Admin.vue')
      },
      {
        path: 'addAdmin',
        name: 'AddAdmin',
        component: () => import('@/views/management/admin/AddAdmin.vue')
      },
      {
        path: 'editAdmin',
        name: 'EditAdmin',
        component: () => import('@/views/management/admin/EditAdmin.vue'),
      },
      //  ====  Admin  ====
      {
        path: 'hdfsList',
        name: 'HdfsList',
        component: () => import('@/views/bigdata/hdfs/Hdfs.vue'),
      },

    ]
  },
  {//当访问路径都不是上面的路由，就会转到404页面
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
router.beforeEach((to, from, next) => {
  if (to.path === '/login') next() //若登陆login，直接放行
  const admin = Cookies.get("admin") //登陆后获取cookie里的数据
  if (!admin && to.path !== '/login') return next("/login")  // 若cookie强里没有数据，或者路径不是login，则制退回到登录页面
  next()  // 若访问 /home 的时候，并且cookie里面存在数据，这个时候我就直接放行
})

export default router
