<script setup>
import {findId} from "@/api/auth";
import {ref, defineEmits} from 'vue';

const username = ref('');
const email = ref('');
const emit = defineEmits([
  'onFindId'
])

const handleSubmit = () => {
  findId(
    {
      name: username.value,
      email: email.value
    },
    ({data}) => {
      emit('onFindId', data);
    },
    (error) => {
      console.log(error);
    }
  )
  console.log(username.value);
  console.log(email.value);
}

</script>

<template>
  <div class='find-id-form'>
    <form @submit.prevent="handleSubmit">
      <div class="input-group">
        <label for="username">이름</label>
        <input
          type="text"
          id="username"
          v-model="username"
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
      <button type="submit">아이디 찾기</button>
    </form>
  </div>
</template>

<style scoped>
/* 로그인 폼 스타일 */
.find-id-form {
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
  background-color: #4e4e4e;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.input-group button:hover {
  background-color: #333;
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
  background-color: #333; /* 버튼 hover 색상 */
}
</style>
