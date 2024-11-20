<template>
  <div v-if="isLoggedIn && showDropdown" class="dropdown">
    <!-- 환영 메시지 -->
    <p class="welcome-message">{{ member }}님</p>
    <!-- 마이페이지와 로그아웃은 클릭 가능 -->
    <p @click="goToMyPage">마이페이지</p>
    <p @click="logout">로그아웃</p>
  </div>
</template>

<script>
import { useAuthStore } from "@/stores/authStore";
import { logout } from "@/api/auth.js";

export default {
  props: {
    showDropdown: {
      type: Boolean,
      required: true,
    },
  },
  computed: {
    isLoggedIn() {
      const authStore = useAuthStore();
      return authStore.isLoggedIn;
    },
    member() {
      const authStore = useAuthStore();
      return authStore.memberName || "회원"; // 기본값 '회원'
    },
  },
  methods: {
    goToMyPage() {
      this.$router.push({ name: "VerifyPassword" });
    },
    async logout() {
      // 로그아웃 API 호출
      await logout();

      // 로그아웃 후 상태 변경
      const authStore = useAuthStore();
      authStore.logout();

      alert("로그아웃 되었습니다.");
      this.$router.push("/"); // 메인 페이지로 이동
    },
  },
};
</script>

<style scoped>
.dropdown {
  background-color: #f0f0f0;
  color: black;
  position: fixed;
  bottom: 0px; /* Adjust this to set vertical position */
  left: 50px; /* Position dropdown on the right */
  width: 150px;
  text-align: center;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15);
  z-index: 1;
}

.dropdown p {
  padding: 10px;
  cursor: pointer;
}

/* 환영 메시지는 hover 효과가 없도록 설정 */
.welcome-message {
  font-weight: bold;
  padding: 10px;
  background-color: #d87070;
  color: white;
  border-radius: 5px;
}

/* 기본 p 태그에 hover 스타일이 적용되지 않도록 설정 */
.welcome-message:hover {
  background-color: #d87070 !important; /* 기존 배경색 그대로 유지 */
}

/* 나머지 메뉴 항목에는 hover 효과가 적용됨 */
.dropdown p:hover {
  background-color: #e0e0e0;
}
</style>
