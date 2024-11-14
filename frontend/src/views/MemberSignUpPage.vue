<template>
  <div class="signup-page">
    <MemberSignUpForm/>
  </div>
</template>

<script>
import MemberSignUpForm from "@/components/member/MemberSignUpForm.vue";

export default {
  name: 'Signup',
  components: {
    MemberSignUpForm
  }
};
</script>

<style scoped>
/* 로그인 페이지 스타일 */
.signup-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

</style>

