<template>

  <el-main>

    <el-header id="login-header">
      <h1>Login</h1>
    </el-header>

    <el-form :model="loginForm" ref="loginForm" id="login-form" label-width="">

      <el-form-item label="" prop="username" :rules="[{ required: true, message: '用户名必填' }]">
        <el-input v-model.trim="loginForm.username" :prefix-icon="User" placeholder="用户名"/>
      </el-form-item>

      <el-form-item label="" prop="password" :rules="[{ required: true, message: '密码必填' }]">
        <el-input v-model.trim="loginForm.password" :prefix-icon="Key" placeholder="密码" :type="passwordType">
          <template #suffix>
            <el-icon class="el-input__icon">
              <Unlock v-if="passwordType==='password'" @click="passwordTypeClick"/>
              <Lock v-else @click="passwordTypeClick"/>
            </el-icon>
          </template>
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="submitForm('loginForm')" id="submit-form">登录</el-button>
        <!--<el-button @click="resetForm('loginForm')">重置</el-button>-->
      </el-form-item>

    </el-form>

  </el-main>

</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      passwordType: "password",
      loginForm: {
        username: '',
        password: '',
      },
    }
  },
  methods: {
    passwordTypeClick() {
      this.passwordType = this.passwordType === 'password' ? 'text' : 'password'
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let username = this.loginForm.username
          let password = this.loginForm.password
          this.$user.login(username, password).then(response => {
            console.log(response)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
  }
}
</script>

<script setup>
import {User, Key, Lock, Unlock} from '@element-plus/icons'
</script>

<style scoped>

#login-header {
  margin: 20px;
  text-align: center;
}

#login-form {
  padding: 20px;
  max-width: 335px;
  margin-left: auto;
  margin-right: auto;
}

#submit-form {
  width: 100%;
}

</style>
