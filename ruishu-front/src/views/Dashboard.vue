<template>
  <div style="padding: 20px">
    <!-- 顶部数据卡片 -->
    <el-row :gutter="20" style="margin-bottom: 20px">
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="card-item">
            <div class="card-title">总销售额（元）</div>
            <div class="card-value" style="color: #409eff">{{ overview.totalAmount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="card-item">
            <div class="card-title">总订单数</div>
            <div class="card-value" style="color: #67c23a">{{ overview.totalOrders }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="card-item">
            <div class="card-title">商品总数</div>
            <div class="card-value" style="color: #e6a23c">{{ overview.totalProducts }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="card-item">
            <div class="card-title">待处理订单</div>
            <div class="card-value" style="color: #f56c6c">{{ overview.pendingOrders }}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 图表区域 -->
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>订单状态分布</template>
          <div ref="orderChartRef" style="height: 300px"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>商品分类分布</template>
          <div ref="categoryChartRef" style="height: 300px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import * as echarts from 'echarts'

const BASE_URL = 'http://localhost:8080'
const overview = ref({})
const orderChartRef = ref(null)
const categoryChartRef = ref(null)

const statusLabel = ['待付款', '待发货', '已发货', '已完成', '已取消']

const loadOverview = async () => {
  const res = await axios.get(`${BASE_URL}/dashboard/overview`)
  overview.value = res.data.data
}

const loadOrderChart = async () => {
  const res = await axios.get(`${BASE_URL}/dashboard/order-status`)
  const data = res.data.data.map(item => ({
    name: statusLabel[item.status],
    value: item.count
  }))
  const chart = echarts.init(orderChartRef.value)
  chart.setOption({
    tooltip: { trigger: 'item' },
    legend: { bottom: 0 },
    series: [{
      type: 'pie',
      radius: '60%',
      data,
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0,0,0,0.5)'
        }
      }
    }]
  })
}

const loadCategoryChart = async () => {
  const res = await axios.get(`${BASE_URL}/dashboard/product-category`)
  const categories = res.data.data.map(item => item.category)
  const counts = res.data.data.map(item => item.count)
  const chart = echarts.init(categoryChartRef.value)
  chart.setOption({
    tooltip: { trigger: 'axis' },
    xAxis: { type: 'category', data: categories },
    yAxis: { type: 'value' },
    series: [{
      type: 'bar',
      data: counts,
      itemStyle: { color: '#409eff' },
      barMaxWidth: 60
    }]
  })
}

onMounted(async () => {
  await loadOverview()
  await loadOrderChart()
  await loadCategoryChart()
})
</script>

<style scoped>
.card-item {
  text-align: center;
  padding: 10px 0;
}
.card-title {
  font-size: 14px;
  color: #999;
  margin-bottom: 10px;
}
.card-value {
  font-size: 32px;
  font-weight: bold;
}
</style>
```

---

保存后浏览器访问：
```
http://localhost:5173/dashboard