import {createRouter, createWebHistory} from 'vue-router';

import HomePage from "@/views/HomePage.vue";
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
