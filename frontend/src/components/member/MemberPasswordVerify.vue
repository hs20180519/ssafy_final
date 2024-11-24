<template>
  <div class="password-check-form">
    <h1>비밀번호 확인</h1>
    <form @submit.prevent="handleSubmit">
      <div class="input-group">
        <label for="password">본인 확인을 위해 비밀번호를 입력해주세요</label>
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
import {checkPassword} from "@/api/member";
import {toast} from "vue3-toastify"; // 서버에서 비밀번호를 확인하는 API

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
          toast.success("비밀번호가 확인되었습니다.", {autoClose: false});
          setTimeout(() => {
            this.$router.push({name: "MyPage"});
          }, 1000);
        } else {
          toast.error("비밀번호가 틀렸습니다.");
        }

      } catch (error) {
        toast.error("비밀번호가 틀렸습니다.");
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
  padding-bottom: 50px;
}

.input-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
  color: gray;
}

.input-group label {
  margin-bottom: 15px;
}

.input-group input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
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
  background-color: #333;
}
</style>
