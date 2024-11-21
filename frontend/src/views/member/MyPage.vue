<script setup>
import MemberEditProfile from "@/components/mypage/MemberEditProfile.vue";
import MemberAddress from "@/components/mypage/MemberAddress.vue";
import { ref } from "vue";

const activeName = ref("findId");
</script>

<template>
  <div class="main-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="회원정보 수정" name="findId">
        <MemberEditProfile />
      </el-tab-pane>
      <el-tab-pane label="주소지 관리" name="findPassword">
        <MemberAddress />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped>
.main-container {
  padding-top: 80px; /* TOOD: 아이디/비밀번호 찾기에도 적용 */
  padding-left: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 60vh;
  width: 100%;
}

.el-tabs {
  width: 500px; /* 탭의 너비 */
  border-radius: 8px;
}

.el-tabs__nav is-top {
  justify-content: center;
}
</style>
