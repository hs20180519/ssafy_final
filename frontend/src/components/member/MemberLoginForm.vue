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
import {login} from "@/api/auth";
import {useAuthStore} from "@/stores/authStore";

export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await login({
          id: this.username,
          password: this.password,
        });
        // TODO : username -> id

        const token = response.headers["authorization"];
        const memberName = response.data.name;
        const memberId = response.data.id;

        // 로그인 성공 시 처리
        if (token) {
          const accessToken = token.replace("Bearer ", "");
          const refreshToken = response.data.refreshToken;

          const authStore = useAuthStore();
          authStore.login(accessToken, memberName, memberId);
          document.cookie = `refreshToken=${refreshToken}; path=/; HttpOnly`;
          alert("로그인 성공");
          this.$router.push({name: "Home"}); // 로그인 후 리디렉션
        }
      } catch (error) {
        // 로그인 실패 시 처리
        if (error.response && error.response.status === 401) {
          alert("로그인 실패: 아이디나 비밀번호가 잘못되었습니다.");
        } else {
          alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
        }
      }
    },
  },
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

.auth-links {
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
  font-size: 0.9em;
}

.auth-links a {
  color: #d87070;
  text-decoration: none;
}

.auth-links a:hover {
  text-decoration: underline;
}
</style>
