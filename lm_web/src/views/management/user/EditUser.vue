<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">编辑用户</div>
    <el-form :inline="true" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="用户ID">
        <el-input v-model="form.userid" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别">
<!--        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>-->
        <el-radio v-model="form.sex" label="男">男</el-radio>
        <el-radio v-model="form.sex" label="女">女</el-radio>
      </el-form-item>
      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
      </el-form-item>

      <el-form-item label="有效开始时间">
        <el-input v-model="form.beginDate" disabled></el-input>
      </el-form-item>
      <el-form-item label="有效结束时间">
        <el-input v-model="form.endDate" disabled></el-input>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input v-model="form.createDate" disabled></el-input>
      </el-form-item>
      <el-form-item label="更新时间">
        <el-input v-model="form.updateTime" disabled></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save">提交</el-button>
      <el-button type="primary" @click="$router.push('/userList')">返回</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import formCheck from "@/utils/formCheck";


export default {
  name: 'EditUser',
  data() {

    return {
      form: {},
      rules: {
        name: [{required: true, message: '请输入姓名', trigger: 'blur'},
          {min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur'}],
        phone: [{required: true, validator: formCheck.FormValidate.Form().validatePhone, trigger: 'blur'}],
        age: [{required: true, validator: formCheck.FormValidate.Form().validateNumber, trigger: 'blur'}]
      }
    }
  },
  created() {
    const userid = this.$route.query.userid
    request.get("/user/" + userid).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('/user/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/userList")
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }

}
</script>