<template>
  <div class="edit-profile-page">
    <h1>회원 정보 수정</h1>
    <form @submit.prevent="handleSubmit">
      <div class="input-group">
        <label for="name">이름</label>
        <input
          type="text"
          id="name"
          v-model="form.name"
          placeholder="이름을 입력하세요"
          required
        />
      </div>

      <div class="input-group">
        <label for="email">이메일</label>
        <input
          type="email"
          id="email"
          v-model="form.email"
          placeholder="이메일을 입력하세요"
          required
        />
      </div>

      <div class="input-group">
        <label for="phone">전화번호</label>
        <input
          type="text"
          id="phone"
          v-model="form.phone"
          placeholder="전화번호를 입력하세요"
        />
      </div>

      <div class="input-group">
        <label for="address">주소</label>
        <input
          type="text"
          id="address"
          v-model="form.address"
          placeholder="주소를 입력하세요"
        />
      </div>

      <button type="submit">수정 완료</button>
    </form>
  </div>
</template>

<script>
import { getMemberInfo, updateMemberInfo } from "@/api/member"; // 회원 정보 가져오기 및 수정 API

export default {
  data() {
    return {
      form: {
        name: "",
        email: "",
        phone: "",
        address: "",
      },
      errorMessage: "",
    };
  },
  async created() {
    try {
      const memberData = await getMemberInfo(); // 회원 정보를 가져오는 API 호출
      this.form.name = memberData.name;
      this.form.email = memberData.email;
      this.form.phone = memberData.phone;
      this.form.address = memberData.address;
    } catch (error) {
      this.errorMessage = "회원 정보를 불러오는 데 오류가 발생했습니다.";
    }
  },
  methods: {
    async handleSubmit() {
      try {
        const response = await updateMemberInfo(this.form); // 수정된 정보 서버로 전송
        if (response.status === 200) {
          alert("회원 정보가 성공적으로 수정되었습니다.");
          this.$router.push({ name: "Home" }); // 홈 페이지로 이동
        }
      } catch (error) {
        this.errorMessage = "회원 정보 수정 중 오류가 발생했습니다.";
      }
    },
  },
};
</script>

<style scoped>
.edit-profile-page {
  width: 100%;
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
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

.error-message {
  color: red;
  font-size: 0.9em;
}
</style>
