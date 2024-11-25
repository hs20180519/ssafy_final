// router/index.js
import { createRouter, createWebHistory } from "vue-router";
import HomePage from "@/views/HomePage.vue";
import HouseDealPage from "@/views/housedeal/HouseDealPage.vue";
import MemberLoginPage from "@/views/member/MemberLoginPage.vue";
import MemberSignUpPage from "@/views/member/MemberSignUpPage.vue";

// board
import BoardListPage from "@/views/board/BoardListPage.vue";
import BoardQuestionDetailsPage from "@/views/board/BoardQuestionDetailsPage.vue";
import CreateQuestionPage from "@/views/board/CreateQuestionPage.vue";
import EditQuestionPage from "@/views/board/EditQuestionPage.vue";
import MemberFindPage from "@/views/member/MemberFindPage.vue";

// member
import MemberPasswordResetPage from "@/views/member/MemberPasswordResetPage.vue";
import VerifyPasswordPage from "@/views/member/MemberVerifyPasswordPage.vue";
import MyPage from "@/views/member/MyPage.vue";

import FavoritePropertyPage from "@/views/favoriteProperty/FavoritePropertyPage.vue";
import { useAuthStore } from "@/stores/authStore";

import { toast } from "vue3-toastify";

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
  {
    path: "/forgot-password-id",
    name: "MemberFind",
    component: MemberFindPage,
  },
  {
    path: "/member/verify-password",
    name: "VerifyPassword",
    component: VerifyPasswordPage,
  },
  {
    path: "/auth/password-reset-verification",
    name: "PasswordReset",
    component: MemberPasswordResetPage,
    props: true,
  },
  {
    path: "/member/edit-profile",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/favorite-property",
    name: "FavoritePropertyPage",
    component: FavoritePropertyPage,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  const isAuthenticated = authStore.getAuthToken;
  // const alertStore = useAlertStore();

  if (
    to.name === "Home" ||
    to.name === "Signup" ||
    to.name === "HouseDeal" ||
    to.name === "MemberFind" ||
    to.name === "PasswordReset"
  ) {
    return next();
  }

  if (!isAuthenticated && to.name !== "Login") {
    toast.error("권한이 없습니다. 로그인 해주세요.");
    next({ name: "Login" });
  } else {
    next();
  }
});

export default router;
