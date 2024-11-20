<template>
  <div class="signup-form">
    <h2>회원가입</h2>
    <form @submit.prevent="handleSignup">
      <div class="input-group">
        <label for="id">아이디</label>
        <input
          type="text"
          id="id"
          v-model="id"
          placeholder="아이디를 입력하세요"
          required
        />
      </div>

      <div class="input-group">
        <label for="name">이름</label>
        <input
          type="text"
          id="name"
          v-model="name"
          placeholder="이름을 입력하세요"
          required
        />
      </div>

      <!-- 주소 검색 컴포넌트 -->
      <AddressSearch v-model="address"/>

      <div class="input-group">
        <label for="detailAddress">상세주소</label>
        <input
          type="text"
          id="detailAddress"
          v-model="detailAddress"
          placeholder="상세주소를 입력하세요"
          required
        />
      </div>

      <div class="input-group">
        <label for="email">이메일</label>
        <input
          type="email"
          id="email"
          v-model="email"
          placeholder="이메일을 입력하세요"
          required
        />
        <button type="button" @click="sendVerificationCode">
          인증번호 전송
        </button>
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

      <div class="input-group">
        <label for="confirmPassword">비밀번호 확인</label>
        <input
          type="password"
          id="confirmPassword"
          v-model="confirmPassword"
          placeholder="비밀번호를 다시 입력하세요"
          required
        />
      </div>

      <button type="submit">회원가입</button>
    </form>
  </div>
</template>

<script>
import {signup} from "@/api/auth.js";
import AddressSearch from "@/components/AddressSearch.vue";

export default {
  components: {AddressSearch},
  data() {
    return {
      id: "",
      name: "",
      address: {address: ""},  // 주소 객체
      detailAddress: "",
      email: "",
      password: "",
      confirmPassword: "",
    };
  },
  methods: {
    async handleSignup() {
      if (this.password !== this.confirmPassword) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return;
      }

      try {
        // 회원가입 데이터 객체 생성
        const memberData = {
          id: this.id,
          name: this.name,
          email: this.email,
          password: this.password,
          address: this.address.address,  // 주소
          detailAddress: this.detailAddress,  // 상세 주소
        };

        // 회원가입 요청
        const response = await signup(memberData);
        if (response.status === 201) {
          alert("회원가입이 완료되었습니다.");
          this.$router.push({name: "Login"});  // 회원가입 후 로그인 페이지로 리다이렉트
        }
      } catch (error) {
        alert("회원가입 중 오류가 발생했습니다.");
      }
    },
    // 인증번호 전송 (추후 수정 필요)
    sendVerificationCode() {
      console.log("이메일 인증번호 전송");
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
  background-color: #b73d3d;
}
</style>
