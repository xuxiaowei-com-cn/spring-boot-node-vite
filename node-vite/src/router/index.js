import { createRouter, createWebHistory } from 'vue-router'
import request from "../utils/request";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/login',
    name: 'Login',
    meta: {
      auth: true,
      authority: [],
        role: []
    },
    component: () => import('../views/Login.vue')
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(((to, from, next) => {

  if (import.meta.env.MODE === 'development') {
      return request.get('/csrf').then(response => {
          next()
      })
  } else {
      next()
  }

}))

export default router
