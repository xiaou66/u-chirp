import {createRouter, createWebHashHistory} from 'vue-router'

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      name: 'managerHome',
      path: '/',
      component: () => import('../views/managerHome/managerHome.vue'),
      children: [
        {
          name: 'overview',
          path: 'overview',
          component: () => import('../views/productOverview/productOverview.vue')
        }
      ]
    }
  ],
})

export default router
