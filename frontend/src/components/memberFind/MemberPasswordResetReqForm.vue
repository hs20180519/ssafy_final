<script setup>
import {ref} from 'vue';
import { sendPasswordResetEmail } from "@/api/auth";
import {toast} from "vue3-toastify";

const userId = ref("");
const email = ref("");
const errorMessage = ref("");

const handleSubmit = () => {
  sendPasswordResetEmail(
    {
      email: email.value,
      id: userId.value
    },
    ({ data }) => {
      console.log(data);
      toast.info("이메일 전송이 완료되었습니다.")
    },
    (error) => {
      console.log(error.status);
      if (error.status === 404) {
        errorMessage.value = "입력하신 정보로 등록된 계정을 찾을 수 없습니다.";
      } else {
        errorMessage.value = "알 수 없는 오류가 발생했습니다. 다시 시도해주세요.";
      }
    }
  )
}
</script>

<template>
  <div class='reset-password-form'>
    <form @submit.prevent="handleSubmit">
      <div class="input-group">
        <label for="userId">아이디</label>
        <input
          type="text"
          id="userId"
          v-model="userId"
          placeholder="이름을 입력하세요"
          required
        />
      </div>
      <div class="input-group">
        <label for="email">이메일</label>
        <input
          type="email"
          id="email"
          v-model="email"
          placeholder="비밀번호를 입력하세요"
          required
        />
      </div>
      <button type="submit">인증번호 받기</button>
    </form>

    <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
    </div>
  </div>
</template>

<style scoped>
/* 로그인 폼 스타일 */
.reset-password-form {
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
}

.input-group {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

.input-group label {
  margin-bottom: 5px;
}

.input-group input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.input-group button {
  margin-top: 10px;
  padding: 10px;
  background-color: #4e4e4e; /* #d87070 색상 적용 */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.input-group button:hover {
  background-color: #333; /* 버튼 hover 색상 */
}

button[type="submit"] {
  width: 100%;
  padding: 10px;
  background-color: #4e4e4e;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #333 /* 버튼 hover 색상 */
}

/* 에러 메시지 스타일 */
.error-message {
  color: red; /* 빨간색으로 강조 */
  font-size: 0.9rem; /* 글씨 크기 */
  margin-top: 10px; /* 위쪽 여백 */
}
</style>
