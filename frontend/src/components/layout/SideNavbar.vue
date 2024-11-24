<template>
  <div class="navbar">
    <!-- 상단 섹션 -->
    <div class="nav-links">
      <p
        :class="{ active: activeMenu === 'home' }"
        @click="goToHome"
        title="홈페이지"
      >
        <font-awesome-icon :icon="['fas', 'house']"/>
        <span class="menu-text">홈</span>
      </p>
      <p
        :class="{ active: activeMenu === 'favorite' }"
        @click="goToFavoriteProperty"
        title="관심 매물"
      >
        <font-awesome-icon :icon="['fas', 'heart']"/>
        <span class="menu-text">관심 매물</span>
      </p>
    </div>

    <!-- 하단 섹션 -->
    <div class="bottom-section">
      <div
        class="profile-section"
        :class="{ active: activeMenu === 'profile' }"
        @click="toggleDropdown"
        title="마이페이지"
      >
        <font-awesome-icon :icon="['fas', 'user']"/>
        <span class="menu-text">마이페이지</span>
      </div>
      <div
        class="board-section"
        :class="{ active: activeMenu === 'board' }"
        @click="goToBoard"
        title="Q&A 게시판"
      >
        <font-awesome-icon :icon="['fas', 'circle-info']"/>
        <span class="menu-text">문의하기</span>
      </div>
    </div>
    <DropdownMenu :showDropdown="showDropdown"/>
  </div>
</template>

<script>
import {useAuthStore} from "@/stores/authStore";
import DropdownMenu from "@/components/layout/DropDownMenu.vue";

export default {
  name: "SideNavbar",
  components: {
    DropdownMenu,
  },
  data() {
    return {
      showDropdown: false,
      activeMenu: "", // 현재 선택된 메뉴 상태
    };
  },
  computed: {
    isLoggedIn() {
      const authStore = useAuthStore();
      return authStore.isLoggedIn;
    },
  },
  methods: {
    toggleDropdown() {
      if (this.isLoggedIn) {
        this.activeMenu = "profile"; // 프로필 메뉴 활성화
        this.showDropdown = !this.showDropdown;

        // 5초 후에 드롭다운 메뉴 닫기
        setTimeout(() => {
          this.showDropdown = false;
        }, 3000);
      } else {
        this.$router.push("/login");
      }
    },
    goToHome() {
      this.activeMenu = "home"; // 홈 메뉴 활성화
      this.$router.push("/");
    },
    goToBoard() {
      this.activeMenu = "board"; // 게시판 메뉴 활성화
      this.$router.push("/board");
    },
    goToFavoriteProperty() {
      this.activeMenu = "favorite"; // 즐겨찾기 메뉴 활성화
      this.$router.push({name: "FavoritePropertyPage"});
    },
  },
};
</script>

<style scoped>
.navbar {
  width: 60px;
  background-color: #ffffff;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  position: fixed;
  left: 0;
  top: 0;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border-right: 1px solid #ddd;
  z-index: 1000;
}

.nav-links p,
.bottom-section .board-section {
  display: flex;
  flex-direction: column; /* 아이콘과 텍스트 세로로 배치 */
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: 1.2rem;
  color: #555;
  padding: 10px;
  border-radius: 8px;
  width: 30px;
}

.nav-links p:hover,
.bottom-section .board-section:hover,
.bottom-section .profile-section:hover {
  color: #333;
  width: 30px;
}

/* 활성화된 메뉴 스타일 */
.nav-links p.active,
.bottom-section .board-section.active,
.bottom-section .profile-section.active {
  background-color: #f0f0f0; /* 강조 배경색 */
  color: #333; /* 강조 텍스트 색상 */
  width: 30px;
}

.bottom-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 40px;
}

.profile-section {
  display: flex; /* 아이콘과 텍스트 세로로 배치 */
  flex-direction: column;
  justify-content: center;
  align-items: center; /* 가운데 정렬 */
  padding: 15px;
  cursor: pointer;
  font-size: 1.2rem;
  color: #555;
  border-radius: 8px;
  margin-bottom: 15px;
}

.board-section {
  padding-top: 20px;
  cursor: pointer;
  font-size: 1.2rem;
  color: #555;
  border-radius: 8px;
}

/* 텍스트 설명 스타일 */
.menu-text {
  font-size: 0.8rem; /* 작은 폰트 크기 */
  color: #555;
  margin-top: 5px; /* 아이콘과 텍스트 간의 간격 */
  text-align: center; /* 텍스트를 가운데 정렬 */
  white-space: nowrap; /* 텍스트가 줄 바꿈되지 않도록 */
}

</style>
