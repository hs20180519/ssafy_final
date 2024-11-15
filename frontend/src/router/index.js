// router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/views/HomePage.vue";
import HouseDealPage from "@/views/HouseDealPage.vue";
import MemberLoginPage from "@/views/MemberLoginPage.vue";
import MemberSignUpPage from "@/views/MemberSignUpPage.vue";

// board
import BoardAnswersPage from "@/views/board/BoardAnswersPage.vue";
import BoardListPage from "@/views/board/BoardListPage.vue";
import BoardQuestionDetailsPage from "@/views/board/BoardQuestionDetailsPage.vue";
import CreateQuestionPage from "@/views/board/CreateQuestionPage.vue";

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
  },
  {
    path: '/board',
    name: 'BoardList',
    component: BoardListPage
  },
  {
    path: '/board/questions/:id',
    name: 'BoardQuestionDetails',
    component: BoardQuestionDetailsPage,
    props: true, // URL 경로 파라미터를 props로 전달
  },
  {
    path: '/board/create',
    name: 'CreateQuestion',
    component: CreateQuestionPage,
  },
  {
    path: '/board/questions/:questionId/answers',
    name: 'BoardAnswers',
    component: BoardAnswersPage,
    props: true, // URL 경로 파라미터를 props로 전달
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
