<template>
  <div class="verify-password-page">
    <MemberPasswordVerify/>
  </div>
</template>

<script>
import MemberPasswordVerify from "@/components/member/MemberPasswordVerify.vue";

export default {
  name: "VerifyPassword",
  components: {
    MemberPasswordVerify,
  },
};
</script>

<style scoped>
.verify-password-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* //padding: 20px; */
  justify-content: center;
  text-align: center;
  min-height: 100vh;
}
</style>
