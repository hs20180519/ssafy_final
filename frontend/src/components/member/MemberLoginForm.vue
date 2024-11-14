<template>
  <div class="login-form">
    <h2>로그인</h2>
    <form @submit.prevent="handleLogin">
      <div class="input-group">
        <label for="username">아이디</label>
        <input
          type="text"
          id="username"
          v-model="username"
          placeholder="아이디를 입력하세요"
          required
        />
      </div>
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
      <button type="submit">로그인</button>
    </form>

    <div class="auth-links">
      <router-link to="/forgot-password-id">Forgot password / ID?</router-link>
      <router-link to="/signup">아직 회원이 아니세요?</router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    handleLogin() {
      console.log('로그인 시도:', this.username, this.password);
      this.$emit('login', {username: this.username, password: this.password});
    }
  }
};
</script>

<style scoped>
/* 로그인 폼 스타일 */
.login-form {
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
}

.input-group {
  margin-bottom: 15px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.auth-links {
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
  font-size: 0.9em;
}

.auth-links a {
  color: #007bff;
  text-decoration: none;
}

.auth-links a:hover {
  text-decoration: underline;
}
</style>
