import { createRouter, createWebHistory } from 'vue-router'
import welcome from "../views/welcome/welcome.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'welcome', component: welcome },
    { path: '/login', name: 'userLogin', component: () => import('../views/users/login/login.vue') },
    {
      path: '/product',
      name: 'product',
      component: () =>  import('../views/product/ProductHone.vue'),
      children: [
        {
          path: ':productCode',
          name: 'productList',
          component: () =>  import('../views/product/list/product.vue')
        },
        {
          path: ':productCode/post/:postId',
          name: 'productDetail',
          component: () =>  import('../views/product/detail/ProductDetail.vue')
        }
      ]
    },
  ],
})
export default router
