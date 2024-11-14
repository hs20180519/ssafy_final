<template>
  <div class="signup-form">
    <h2>회원가입</h2>
    <form @submit.prevent="handleSignup">
      <div class="input-group">
        <label for="id">아이디</label>
        <input type="text" id="id" v-model="id" placeholder="아이디를 입력하세요" required/>
      </div>

      <div class="input-group">
        <label for="name">이름</label>
        <input type="text" id="name" v-model="name" placeholder="이름을 입력하세요" required/>
      </div>

      <div class="input-group">
        <label for="email">이메일</label>
        <input type="email" id="email" v-model="email" placeholder="이메일을 입력하세요" required/>
        <button type="button" @click="sendVerificationCode">인증번호 전송</button>
      </div>

      <div class="input-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" v-model="password" placeholder="비밀번호를 입력하세요" required/>
      </div>

      <div class="input-group">
        <label for="confirmPassword">비밀번호 확인</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" placeholder="비밀번호를 다시 입력하세요" required/>
      </div>

      <button type="submit">회원가입</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: '', // 'id'로 변경
      name: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
  },
  methods: {
    handleSignup() {
      // 회원가입 처리 로직 추가
      console.log("회원가입 정보:", this.id, this.name, this.email, this.password, this.confirmPassword);
    },
    sendVerificationCode() {
      // 이메일 인증번호 전송 로직 추가
      console.log("이메일 인증번호 전송:", this.email);
    }
  }
};
</script>

<style scoped>
.signup-form {
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
  padding: 8px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.input-group button:hover {
  background-color: #0056b3;
}

button[type="submit"] {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #218838;
}
</style>
