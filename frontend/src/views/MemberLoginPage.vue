<template>
  <div class="login-page">
    <MemberLoginForm/>
  </div>
</template>

<script>
import MemberLoginForm from "@/components/member/MemberLoginForm.vue"

export default {
  name: 'Login',
  components: {
    MemberLoginForm,
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

