<template>
  <div style="padding: 20px">
    <div style="margin-bottom: 16px">
      <el-button type="primary" @click="openAdd">+ 新增商品</el-button>
    </div>

    <!-- 商品列表 -->
    <el-table :data="productList" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="name" label="商品名称" />
      <el-table-column prop="category" label="分类" width="100" />
      <el-table-column prop="price" label="价格(元)" width="100" />
      <el-table-column prop="stock" label="库存" width="80" />
      <el-table-column label="状态" width="80">
        <template #default="{ row }">
          <el-tag :type="row.status === 1 ? 'success' : 'danger'">
            {{ row.status === 1 ? '上架' : '下架' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述" />
      <el-table-column label="操作" width="160">
        <template #default="{ row }">
          <el-button size="small" type="primary" @click="openEdit(row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增/编辑弹窗 -->
    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑商品' : '新增商品'" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="form.name" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="分类">
          <el-input v-model="form.category" placeholder="请输入分类" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" placeholder="请输入价格" type="number" />
        </el-form-item>
        <el-form-item label="库存">
          <el-input v-model="form.stock" placeholder="请输入库存" type="number" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status">
            <el-option label="上架" :value="1" />
            <el-option label="下架" :value="0" />
          </el-select>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" type="textarea" placeholder="请输入描述" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'

const productList = ref([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const form = ref({})

const BASE_URL = 'http://localhost:8080'

// 加载商品列表
const loadList = async () => {
  const res = await axios.get(`${BASE_URL}/product/list`)
  productList.value = res.data.data
}

// 打开新增弹窗
const openAdd = () => {
  isEdit.value = false
  form.value = { status: 1 }
  dialogVisible.value = true
}

// 打开编辑弹窗
const openEdit = (row) => {
  isEdit.value = true
  form.value = { ...row }
  dialogVisible.value = true
}

// 提交表单
const handleSubmit = async () => {
  if (isEdit.value) {
    await axios.put(`${BASE_URL}/product/update`, form.value)
    ElMessage.success('更新成功')
  } else {
    await axios.post(`${BASE_URL}/product/add`, form.value)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  loadList()
}

// 删除商品
const handleDelete = async (id) => {
  await ElMessageBox.confirm('确定要删除这个商品吗？', '提示', { type: 'warning' })
  await axios.delete(`${BASE_URL}/product/delete/${id}`)
  ElMessage.success('删除成功')
  loadList()
}

onMounted(() => loadList())
</script>