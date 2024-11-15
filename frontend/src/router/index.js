// router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/views/HomePage.vue";
import HouseDealPage from "@/views/HouseDealPage.vue";
import MemberLoginPage from "@/views/MemberLoginPage.vue";
import MemberSignUpPage from "@/views/MemberSignUpPage.vue";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/login',
    name: 'Login',
    component: MemberLoginPage
  },
  {
    path: '/house-deal',
    name: 'HouseDeal',
    component: HouseDealPage,
    props: true
    
  },
  {
    path: '/signup',
    name: 'Signup',
    component: MemberSignUpPage
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
