<template>
  <div style="padding: 20px">
    <el-table :data="orderList" border stripe style="width: 100%">
      <el-table-column prop="orderNo" label="订单编号" width="180" />
      <el-table-column prop="userName" label="用户名" width="80" />
      <el-table-column prop="phone" label="手机号" width="120" />
      <el-table-column prop="productName" label="商品" />
      <el-table-column prop="quantity" label="数量" width="60" />
      <el-table-column prop="totalAmount" label="金额(元)" width="100" />
      <el-table-column label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="statusType(row.status)">
            {{ statusLabel(row.status) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="地址" />
      <el-table-column label="操作" width="200">
        <template #default="{ row }">
          <el-select
            v-model="row.status"
            size="small"
            style="width: 100px; margin-right: 8px"
            @change="handleStatusChange(row)"
          >
            <el-option label="待付款" :value="0" />
            <el-option label="待发货" :value="1" />
            <el-option label="已发货" :value="2" />
            <el-option label="已完成" :value="3" />
            <el-option label="已取消" :value="4" />
          </el-select>
          <el-button size="small" type="danger"
            @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'

const orderList = ref([])
const BASE_URL = 'http://localhost:8080'

const statusLabel = (s) => ['待付款','待发货','已发货','已完成','已取消'][s] ?? '未知'
const statusType = (s) => ['warning','primary','info','success','danger'][s] ?? ''

const loadList = async () => {
  const res = await axios.get(`${BASE_URL}/order/list`)
  orderList.value = res.data.data
}

const handleStatusChange = async (row) => {
  await axios.put(`${BASE_URL}/order/status/${row.id}/${row.status}`)
  ElMessage.success('状态更新成功')
}

const handleDelete = async (id) => {
  await ElMessageBox.confirm('确定要删除这条订单吗？', '提示', { type: 'warning' })
  await axios.delete(`${BASE_URL}/order/delete/${id}`)
  ElMessage.success('删除成功')
  loadList()
}

onMounted(() => loadList())
</script>