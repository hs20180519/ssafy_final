// router/index.js
import {createRouter, createWebHistory} from "vue-router";
import HomePage from "@/views/HomePage.vue";
import HouseDealPage from "@/views/HouseDealPage.vue";
import MemberLoginPage from "@/views/MemberLoginPage.vue";
import MemberSignUpPage from "@/views/MemberSignUpPage.vue";

// board
import BoardListPage from "@/views/board/BoardListPage.vue";
import BoardQuestionDetailsPage from "@/views/board/BoardQuestionDetailsPage.vue";
import CreateQuestionPage from "@/views/board/CreateQuestionPage.vue";
import EditQuestionPage from "@/views/board/EditQuestionPage.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomePage,
  },
  {
    path: "/login",
    name: "Login",
    component: MemberLoginPage,
  },
  {
    path: "/house-deal",
    name: "HouseDeal",
    component: HouseDealPage,
    props: true,
  },
  {
    path: "/signup",
    name: "Signup",
    component: MemberSignUpPage,
  },
  {
    path: "/board",
    name: "BoardList",
    component: BoardListPage,
  },
  {
    path: "/board/questions/:id",
    name: "BoardQuestionDetails",
    component: BoardQuestionDetailsPage,
    props: true, // URL 경로 파라미터를 props로 전달
  },
  {
    path: "/board/questions/:id/edit",
    name: "EditQuestion",
    component: EditQuestionPage,
    props: true,
  },
  {
    path: "/board/create",
    name: "CreateQuestion",
    component: CreateQuestionPage,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
