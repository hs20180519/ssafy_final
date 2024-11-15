<template>
  <div class="main-content">
    <div class="logo">The Next Home</div>
    <h1>당신의 다음 집을 검색해보세요</h1>
    <!--    <p>FIND YOUR NEXT HOME</p>-->
    <SearchBox/>
    <SearchBarResult v-if="isLoaded" :result="result"/>
    <div class="auth-links">
      <router-link to="/login">로그인</router-link>
      /
      <router-link to="/signup">회원가입</router-link>
    </div>
  </div>
</template>

<script>
import SearchBox from '@/components/common/SearchBox.vue';

export default {
  name: 'MainContent',
  components: {
    SearchBox,
  },
};
</script>

<style scoped>
.main-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  flex: 1;
}

.logo {
  font-size: 2em;
  font-weight: bold;
  color: #d87070;
  letter-spacing: 2px;
  text-transform: uppercase;
}

.auth-links {
  margin-top: 1em;
}

.auth-links a {
  color: #007bff;
  text-decoration: none;
}

.auth-links a:hover {
  text-decoration: underline;
}
</style>
