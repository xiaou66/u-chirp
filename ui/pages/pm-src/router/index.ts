import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      name: 'managerHome',
      path: '/pm',
      component: () => import('../views/managerHome/managerHome.vue')
    }
  ],
})

export default router
