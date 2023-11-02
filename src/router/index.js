import { createRouter, createWebHistory } from 'vue-router'
import AuthLayout from '../layouts/AuthLayout.vue'
import DashboardLayout from '../layouts/DashboardLayout.vue'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: AuthLayout,
      children: [{
        path: '',
        component: () => import("@/views/LoginView.vue")
      }]
    },
    {
      path: '/home',
      name: 'home',
      component: DashboardLayout,
      children: [{
        path: '',
        component: HomeView
      }]
    },
    {
      path: '/about',
      name: 'about',
      component: DashboardLayout,
      children: [{
        path: '',
        component: () => import('../views/AboutView.vue')
      }]
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      
    }
  ]
})

export default router
