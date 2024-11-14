<template>
  <div class="login-page">
    <member-login-form @login="handleLogin"/>
    <div class="links">
      <!--      <router-link to="/forgot-password-id">Forgot password / ID?</router-link>-->
      <router-link to="/signup">아직 회원이 아니세요?</router-link>
    </div>
  </div>
</template>

<script>
import MemberLoginForm from '@/components/member/MemberLoginForm';

export default {
  components: {
    MemberLoginForm
  },
  methods: {
    handleLogin(credentials) {
      console.log('로그인 시도:', credentials);
      // 로그인 처리 로직을 여기에 추가
    }
  }
};
</script>

<style scoped>
/* 로그인 페이지 스타일 */
.login-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.links {
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
}
</style>

