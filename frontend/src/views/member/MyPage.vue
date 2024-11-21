<script setup>
// import { TabsPaneContext } from 'element-plus';
import MemberEditProfile from "@/components/member/MemberEditProfile.vue";
import MemberIdFindForm from "@/components/member/MemberIdFindForm.vue";
import MemberIdFindResult from "@/components/member/MemberIdFindResult.vue";
import MemberPasswordResetReqForm from "@/components/member/MemberPasswordResetReqForm.vue";
import { ref } from "vue";

const activeName = ref("findId");

const isIdFormLoaded = ref(true);
const isPasswordFormLoaded = ref(true);

const foundId = ref("");

const onFindId = (data) => {
  console.log("onFineId", data);
  isIdFormLoaded.value = false;
  foundId.value = data;
};

const maskId = (id) => {
  const length = id.length;
  const halfLength = Math.ceil(length / 2);
  const visiblePart = id.slice(0, halfLength);
  const maskedPart = "*".repeat(length - halfLength);

  return visiblePart + maskedPart;
};
</script>

<template>
  <div class="main-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="회원정보 수정" name="findId">
        <MemberEditProfile />
      </el-tab-pane>
      <el-tab-pane label="주소지 수정" name="findPassword">
        <MemberPasswordResetReqForm v-if="isPasswordFormLoaded" />
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
