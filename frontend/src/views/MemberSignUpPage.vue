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
/* 회원가입 페이지 스타일 */
.signup-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 50px;
}

</style>

