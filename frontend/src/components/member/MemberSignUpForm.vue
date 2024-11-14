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
import axios from 'axios';

export default {
  data() {
    return {
      id: '',
      name: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
  },
  methods: {
    handleSignup() {
      // 비밀번호 확인 로직
      if (this.password !== this.confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      // 회원가입 정보 전송
      const memberData = {
        id: this.id,
        name: this.name,
        email: this.email,
        password: this.password
      };

      axios.post('http://localhost:80/api/auth/signup', memberData)
        .then(response => {
          console.log('회원가입 성공:', response.data);
          alert('회원가입이 완료되었습니다.');
          this.$router.push('/login');  // 회원가입 후 로그인 페이지로 리다이렉트
        })
        .catch(error => {
          console.error('회원가입 오류:', error);
          alert('회원가입 중 오류가 발생했습니다.');
        });
    },
    sendVerificationCode() {
      axios.post('http://localhost:80/api/auth/signup-mail', {mail: this.email})
        .then(response => {
          console.log('인증번호 전송 성공:', response.data);
          alert('인증번호가 이메일로 전송되었습니다.');
        })
        .catch(error => {
          console.error('인증번호 전송 오류:', error);
          alert('인증번호 전송 중 오류가 발생했습니다.');
        });
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
  background-color: #70abd8;
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
  background-color: #d87070;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #773d3d;
}
</style>
