<template>
  <div class="navbar">
    <!-- Top Navigation Links -->
    <div class="nav-links">
      <p @click="goToHome">
        <font-awesome-icon :icon="['fas', 'house']"/>
      </p>
      <p>
        <font-awesome-icon :icon="['fas', 'star']"/>
      </p>
    </div>

    <div class="bottom-section">
      <div class="profile-section" @click="toggleDropdown">
        <font-awesome-icon :icon="['fas', 'user']"/>
      </div>
      <span><font-awesome-icon :icon="['fas', 'circle-info']"/> </span>
    </div>
  </div>
  <DropdownMenu :showDropdown="showDropdown" />
</template>

<script>
import { useAuthStore } from '@/stores/authStore'; // Pinia store 임포트
import DropdownMenu from '@/components/layout/DropDownMenu.vue'; // DropdownMenu 컴포넌트 임포트

export default {
  name: 'SideNavbar',
  components: {
    DropdownMenu,
  },
  data() {
    return {
      showDropdown: false,
    };
  },
  computed: {
    // Pinia store로부터 로그인 상태와 사용자 정보 가져오기
    isLoggedIn() {
      const authStore = useAuthStore();
      return authStore.isLoggedIn;
    },
  },
  methods: {
    toggleDropdown() {
      if (this.isLoggedIn) {
        this.showDropdown = !this.showDropdown;
      } else {
        // 로그인되지 않은 상태에서 클릭하면 로그인 페이지로 이동
        this.$router.push('/login');
      }
    },
    goToHome() {
      this.$router.push('/'); // 홈 페이지로 이동
    },
  },
};
</script>

<style scoped>
.navbar {
  width: 50px;
  background-color: #d87070;
  height: 100vh;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  position: fixed;
  left: 0;
  top: 0;
}

.nav-links p {
  display: flex;
  align-items: center;
  margin: 10px 0;
  text-align: center;
  cursor: pointer;
  padding: 10px 0;
}

.bottom-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.profile-section {
  padding: 20px 0;
  cursor: pointer;
}
</style>
