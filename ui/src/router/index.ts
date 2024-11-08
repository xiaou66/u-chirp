import { createRouter, createWebHistory, createWebHashHistory } from 'vue-router'
import welcome from "@/views/welcome/welcome.vue";

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', name: 'welcome', component: welcome},
    {path: '/login', name: 'userLogin', component: () => import('@/views/users/login/login.vue')}
  ],
})
console.log('111')
export default router
