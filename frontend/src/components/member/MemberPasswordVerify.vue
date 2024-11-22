<template>
  <div class="password-check-form">
    <h2>비밀번호 확인</h2>
    <form @submit.prevent="handleSubmit">
      <div class="input-group">
        <label for="password">비밀번호</label>
        <input
          type="password"
          id="password"
          v-model="password"
          placeholder="비밀번호를 입력하세요"
          required
        />
      </div>
      <button type="submit">확인</button>
    </form>
  </div>
</template>

<script>
import { checkPassword } from "@/api/member"; // 서버에서 비밀번호를 확인하는 API

export default {
  data() {
    return {
      password: "", // 사용자가 입력한 비밀번호
      errorMessage: "", // 에러 메시지
    };
  },
  methods: {
    async handleSubmit() {
      try {
        const isPasswordCorrect = await checkPassword({
          password: this.password,
        });

        if (isPasswordCorrect) {
          this.$router.push({ name: "MyPage" }); // 마이페이지로 이동
        } else {
          this.errorMessage = "비밀번호가 틀렸습니다.";
        }
      } catch (error) {
        this.errorMessage = "비밀번호 확인 중 오류가 발생했습니다.";
      }
    },
  },
};
</script>

<style scoped>
.password-check-form {
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

button[type="submit"] {
  width: 100%;
  padding: 10px;
  background-color: #d87070;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #b85b5b;
}
</style>
