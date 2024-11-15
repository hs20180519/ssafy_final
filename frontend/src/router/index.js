// router/index.js
import HomePage from "@/views/HomePage.vue";
import HouseDealPage from "@/views/HouseDealPage.vue";
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: HomePage,
  },
  {
    path: '/house-deal',
    name: 'HouseDeal',
    component: HouseDealPage,
    props: true
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
