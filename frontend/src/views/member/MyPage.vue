<script setup>
import MemberEditProfile from "@/components/mypage/MemberEditProfile.vue";
import MemberAddress from "@/components/mypage/MemberAddress.vue";
import {ref, watch} from "vue";
import {useRoute} from "vue-router";

const activeName = ref("editProfile");
const route = useRoute();

// 라우트 쿼리 파라미터로 탭을 활성화
watch(
  () => route.query.tab,
  (newTab) => {
    if (newTab === "editAddress") {
      activeName.value = "editAddress"; // 주소지 관리 탭 활성화
    } else {
      activeName.value = "editProfile"; // 기본 탭은 회원정보 수정
    }
  },
  {immediate: true} // 컴포넌트가 처음 렌더링될 때도 실행
);
</script>

<template>
  <div class="main-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="회원정보 수정" name="editProfile">
        <MemberEditProfile/>
      </el-tab-pane>
      <el-tab-pane label="주소지 관리" name="editAddress">
        <MemberAddress/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped>
.main-container {
  padding-left: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
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
