import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Layout from '../views/Layout.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: 'dashboard',
          component: () => import('../views/Dashboard.vue')
        },
        {
          path: 'product',
          component: () => import('../views/Product.vue')
        },
        {
          path: 'order',
          component: () => import('../views/Order.vue')
        }
      ]
    }
  ]
})

export default router