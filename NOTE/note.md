知识：
javaSE、servlet、JDBC、Maven、Mybatis、SpringMVC、SSM（了解）、Springboot
Mysql数据库简单的SQL语法
HTML、CSS、JS、vue2相关语法

软件：
idea（写代码软件）、nodejs（前端开发环境）、jdk（后端开发环境）、mysql5.7\8.0（数据库）、navicat（数据库可视化）、
notepad++（文本编辑器，可选）、postman（接口调试工具，可选）

### 创建vue工程
`npm config set registry https://registry.npm.taobao.org`
安装 vue/cli：https://cli.vuejs.org/zh/guide/installation.html

### 使用elementUI
官网：https://element.eleme.cn/
安装 ElementUI
npm i element-ui -S

```
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
```

### 登录和数据安全

js-cookie的使用

```
npm i js-cookie -S

// 导入使用

import Cookies from 'js-cookie'

Cookies.set('user', obj)  // 默认失效时间为该网站关闭时
Cookies.set('user', obj, { expires: 1 })  // 1天过期

Cookies.get('user')  // 获取cookie数据

Cookies.remove('user')  // 删除cookie数据
```


前端运行命令：
npm run serve


axios插件，去请求后台的数据，去处理前后端交互的数据
安装命令：npm i axios -S



20221016
会员管理与管理员管理做好，正在修改userid
后面做登陆，然后搞数据安全，最后做设置和验证



会员管理模块：

管理员管理模块：


数据安全性：
前端使用cookie，但若篡改cookie，还是会进去，所以就需要后端安全
后端不能用接口直接访问数据，解决办法：
集成JWT(JSON Web Token)，