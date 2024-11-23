<template>
  <div v-if="isLoggedIn && showDropdown" class="dropdown">
    <!-- 환영 메시지 -->
    <p class="welcome-message">{{ member }}님</p>
    <!-- 마이페이지와 로그아웃 -->
    <p @click="goToMyPage">마이페이지</p>
    <p @click="logout">로그아웃</p>
  </div>
</template>

<script>
import {useAuthStore} from "@/stores/authStore";
import {logout} from "@/api/auth.js";

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
      this.$emit("closeDropdown"); // 드롭다운 닫기
      this.$router.push({name: "VerifyPassword"});
    },
    async logout() {
      // 로그아웃 API 호출
      await logout();

      // 로그아웃 후 상태 변경
      const authStore = useAuthStore();
      authStore.logout();

      alert("로그아웃 되었습니다.");
      this.$emit("closeDropdown"); // 드롭다운 닫기
      this.$router.push({name: "Home"}); // 메인 페이지로 이동
    },
    handleOutsideClick(event) {
      const dropdownElement = this.$el; // 현재 컴포넌트의 루트 엘리먼트
      if (!dropdownElement.contains(event.target)) {
        this.$emit("closeDropdown");
      }
    },
  },
  mounted() {
    document.addEventListener("click", this.handleOutsideClick);
  },
  beforeUnmount() {
    document.removeEventListener("click", this.handleOutsideClick);
  },
};
</script>

<style scoped>
.dropdown {
  background-color: #ffffff;
  color: #333;
  position: absolute;
  bottom: 1px;
  left: 60px;
  width: 140px;
  text-align: center;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

.dropdown p {
  padding: 12px;
  font-size: 14px;
  color: #555;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.dropdown p:hover {
  background-color: #f4f4f4;
}

.welcome-message {
  font-size: 14px;
  font-weight: 600;
  color: #666;
  background-color: transparent;
  margin-bottom: 5px;
  padding: 10px 0;
  border-bottom: 1px solid #ddd;
}
</style>
