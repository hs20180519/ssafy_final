<template>
  <div class="edit-profile-page">
    <MemberEditProfile />
  </div>
</template>

<script>
import MemberEditProfile from "@/components/member/MemberEditProfile.vue";

export default {
  name: "EditProfile",
  components: {
    MemberEditProfile,
  },
};
</script>

<style scoped>
.edit-profile-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* //padding: 20px; */
  padding-top: 100px;
}
</style>
