import { createRouter, createWebHistory, createWebHashHistory } from 'vue-router'
import welcome from "../views/welcome/welcome.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'welcome', component: welcome },
    { path: '/login', name: 'userLogin', component: () => import('../views/users/login/login.vue') },
    { path: '/product', name: 'product', component: () =>  import('../views/product/product.vue')}
  ],
})
export default router
