<script setup>
import { ref } from 'vue';
import { sendPasswordResetEmail } from "@/api/auth";

const userId = ref("");
const email = ref("");

const handleSubmit = () => {
    sendPasswordResetEmail(
        {
            email: email.value,
            id: userId.value
        },
        ({ data }) => {
            console.log(data);
        },
        (error) => {
            console.log(error);
        }
    )
}
</script>

<template>
    <div class='reset-password-form'>
        <h2>비밀번호 찾기 </h2>
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
  background-color: #d87070; /* #d87070 색상 적용 */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.input-group button:hover {
  background-color: #b85b5b; /* 버튼 hover 색상 */
}

button[type="submit"] {
  width: 100%;
  padding: 10px;
  background-color: #d87070; /* #d87070 색상 적용 */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #b85b5b; /* 버튼 hover 색상 */
}
</style>