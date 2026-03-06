<template>
  <div class="login-wrap">
    <div class="login-box">
      <h2>睿数电商管理系统</h2>
      <el-form :model="form" style="margin-top: 30px">
        <el-form-item>
          <el-input
            v-model="form.username"
            placeholder="请输入用户名"
            size="large"
            prefix-icon="User"
          />
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="form.password"
            type="password"
            placeholder="请输入密码"
            size="large"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            size="large"
            style="width: 100%"
            :loading="loading"
            @click="handleLogin"
          >
            登 录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
const router = useRouter()

const form = ref({ username: '', password: '' })
const loading = ref(false)

const handleLogin = async () => {
  if (!form.value.username || !form.value.password) {
    ElMessage.warning('请输入用户名和密码')
    return
  }
  loading.value = true
  try {
    const res = await axios.post(
      `http://localhost:8080/auth/login`,
      null,
      { params: { username: form.value.username, password: form.value.password } }
    )
    if (res.data.code === 200) {
      ElMessage.success(res.data.data)
      router.push('/dashboard')
    } else {
      ElMessage.error(res.data.message)
    }
  } catch (e) {
    ElMessage.error('请求失败，请检查后端服务')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-wrap {
  height: 100vh;
  background: linear-gradient(135deg, #1a237e, #0d47a1);
  display: flex;
  align-items: center;
  justify-content: center;
}
.login-box {
  background: white;
  padding: 40px;
  border-radius: 12px;
  width: 380px;
  box-shadow: 0 8px 32px rgba(0,0,0,0.3);
}
h2 {
  text-align: center;
  color: #1a237e;
  margin: 0;
}
</style>