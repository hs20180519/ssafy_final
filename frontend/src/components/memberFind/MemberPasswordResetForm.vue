<script setup>
import {ref, onMounted} from 'vue'
import {useRoute, useRouter} from "vue-router";
import { verifyResetUUID, resetPassword } from '@/api/auth'
import {toast} from "vue3-toastify";

const password = ref('');
const confirmPassword = ref('');
const isValidUUID = ref(false);

const route = useRoute();
const router = useRouter();

onMounted(() => {
  console.log(route.query.email, route.query.uuid);

  verifyResetUUID(
    {
      uuid: route.query.uuid
    },
    ({data}) => {
      if (data == true) {
        isValidUUID.value = true;
      }
    },
    (error) => {
      console.log(error);
    }
  )
})

const handleSubmit = () => {
  if (password.value !== confirmPassword.value) {
    toast.error("비밀번호가 일치하지 않습니다.");
  } else {
    resetPassword({
        email: route.query.email,
        newPassword: password.value,
        confirmNewPassword: confirmPassword.value,
        uuid: route.query.uuid
      },
      (response) => {
        console.log(response);
        if (response.status == 200) {
          toast.info("비밀번호가 변경되었습니다. 로그인 화면으로 이동합니다.", { autoClose: false });
          setTimeout(() => {
            this.$router.push({ name: "Home" });
          }, 1000);
          router.push({
            name: 'Login'
          })
        }
      },
      (error) => {
        console.log(error);
      })
  }
}
</script>

<template>
  <div class='reset-password-form' v-if="isValidUUID">
    <h2>비밀번호 재설정</h2>
    <form @submit.prevent="handleSubmit">
      <div class="input-group">
        <label for="password">새 비밀번호</label>
        <input
          type="password"
          id="password"
          v-model="password"
          placeholder="새 비밀번호를 입력하세요"
          required
        />
      </div>
      <div class="input-group">
        <label for="confirmPassword">새 비밀번호 확인</label>
        <input
          type="password"
          id="confirmPassword"
          v-model="confirmPassword"
          placeholder="새 비밀번호를 입력하세요"
          required
        />
      </div>
      <button type="submit">비밀번호 재설정</button>
    </form>
  </div>
  <div v-if="!isValidUUID">
    <h2>유효하지 않은 링크입니다.</h2>
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
  background-color: #4e4e4e;
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
  background-color: #4e4e4e; /* #d87070 색상 적용 */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #333; /* 버튼 hover 색상 */
}
</style>
