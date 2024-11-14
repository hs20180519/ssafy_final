<template>
  <div class="navbar">
    <!-- Top Navigation Links -->
    <div class="nav-links">
      <p>
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

      <div v-if="isLoggedIn && showDropdown" class="dropdown">
        <p @click="goToMyPage">마이페이지</p>
        <p @click="logout">로그아웃</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {
      isLoggedIn: false,
      showDropdown: false,
    };
  },
  methods: {
    toggleDropdown() {
      if (this.isLoggedIn) {
        this.showDropdown = !this.showDropdown;
      }
    },
    goToMyPage() {
      this.$router.push('/my-page');
      this.showDropdown = false;
    },
    logout() {
      this.isLoggedIn = false;
      this.showDropdown = false;
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

.dropdown {
  background-color: #f0f0f0;
  color: black;
  position: absolute;
  bottom: 70px;
  width: 100px;
  text-align: center;
  border-radius: 5px;
}

.dropdown p {
  padding: 10px;
  cursor: pointer;
}

.dropdown p:hover {
  background-color: #e0e0e0;
}
</style>
