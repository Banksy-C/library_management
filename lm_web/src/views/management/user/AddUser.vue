<template>
  <div style="width: 80%">
    <h2>新增用户</h2>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">

      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item label="性别">
        <el-radio v-model="form.sex" label="男">男</el-radio>
        <el-radio v-model="form.sex" label="女">女</el-radio>
      </el-form-item>

      <el-form-item label="年龄" prop="age">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>

      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式" ></el-input>
      </el-form-item>

      <el-form-item label="地址">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
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
  name: 'AddUser',
  data() {

    return {
      form: {sex: '男'//默认sex是男
      },
      // 表单校验
      rules: {
        name: [{required: true, message: '请输入姓名', trigger: 'blur'},
               {min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur'}],
        phone: [{required: true, validator: formCheck.FormValidate.Form().validatePhone, trigger: 'blur'}],
        age: [{required: true, validator: formCheck.FormValidate.Form().validateNumber, trigger: 'blur'}]
      }
    }
  },

  methods: {
    save() {/** 调用新增API **/
      request.post('/user/save', this.form).then(res => {
        if (res.code === '200'){
          this.$notify.success('新增成功')
          this.form = {}//清空表单里的数据
        }else {
          this.$notify.error(res.msg)
        }
      })
    }

  }

}
</script>

