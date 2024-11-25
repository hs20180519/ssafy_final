<script setup>
// import { TabsPaneContext } from 'element-plus';
import MemberIdFindForm from "@/components/memberFind/MemberIdFindForm.vue";
import MemberIdFindResult from "@/components/memberFind/MemberIdFindResult.vue";
import MemberPasswordResetReqForm from "@/components/memberFind/MemberPasswordResetReqForm.vue";
import { ref } from 'vue'

const activeName = ref("findId")

const isIdFormLoaded = ref(true);
const isPasswordFormLoaded = ref(true);

const foundId = ref("");
const errorMessage = ref("");

const onFindId = (data) => {
  console.log("onFineId", data);

  if (data) {
    isIdFormLoaded.value = false;
    foundId.value = data;
    errorMessage.value = "";
  } else {
    errorMessage.value = "해당 정보로 등록된 아이디를 찾을 수 없습니다.";
  }
}

const maskId = (id) => {
  const length = id.length;
  const halfLength = Math.ceil(length / 2);
  const visiblePart = id.slice(0, halfLength);
  const maskedPart = '*'.repeat(length - halfLength);

  return visiblePart + maskedPart;
}

</script>

<template>
  <div class='main-container'>
    <el-tabs v-model="activeName">
      <el-tab-pane label="아이디 찾기" name="findId">
        <!-- ID 찾기 폼 -->
        <MemberIdFindForm 
          v-if="isIdFormLoaded" 
          @onFindId="onFindId" 
        />
        
        <!-- 에러 메시지 -->
        <div v-if="errorMessage" class="error-message">
          {{ errorMessage }}
        </div>
        
        <!-- ID 찾기 결과 -->
        <MemberIdFindResult 
          v-if="!isIdFormLoaded" 
          :foundId="maskId(foundId)" 
          @goBack="isIdFormLoaded = true" 
        />
      </el-tab-pane>
      <el-tab-pane label="비밀번호 찾기" name="findPassword">
        <MemberPasswordResetReqForm v-if='isPasswordFormLoaded'/>
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

.error-message {
  color: red;
  font-size: 0.9em;
  margin-top: 15px;
  padding-left: 50px;
}

</style>
