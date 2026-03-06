<template>
  <el-container style="height: 100vh">
    <!-- 侧边栏 -->
    <el-aside width="220px" style="background: #1a237e">
      <div class="logo">睿数电商管理系统</div>
      <el-menu
        :default-active="$route.path"
        router
        background-color="#1a237e"
        text-color="#fff"
        active-text-color="#409eff"
      >
        <el-menu-item index="/dashboard">
          <el-icon><DataLine /></el-icon>
          <span>销售看板</span>
        </el-menu-item>
        <el-menu-item index="/product">
          <el-icon><Goods /></el-icon>
          <span>商品管理</span>
        </el-menu-item>
        <el-menu-item index="/order">
          <el-icon><List /></el-icon>
          <span>订单管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <!-- 顶部栏 -->
      <el-header style="background: #fff; display: flex; align-items: center;
        justify-content: space-between; border-bottom: 1px solid #eee">
        <span style="font-size: 16px; font-weight: bold; color: #333">
          {{ currentTitle }}
        </span>
        <el-button type="danger" size="small" @click="handleLogout">退出登录</el-button>
      </el-header>

      <!-- 内容区 -->
      <el-main style="background: #f5f7fa">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { DataLine, Goods, List } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()

const titleMap = {
  '/dashboard': '销售看板',
  '/product': '商品管理',
  '/order': '订单管理'
}

const currentTitle = computed(() => titleMap[route.path] || '首页')

const handleLogout = () => {
  ElMessage.success('已退出登录')
  router.push('/login')
}
</script>

<style scoped>
.logo {
  color: white;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  padding: 20px 10px;
  border-bottom: 1px solid rgba(255,255,255,0.1);
}
</style>