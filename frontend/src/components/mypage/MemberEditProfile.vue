<template>
  <div class="edit-profile-page">
    <form @submit.prevent="handleSubmit">
      <!-- 아이디 -->
      <div class="input-group">
        <label for="id">아이디</label>
        <input
          type="text"
          id="id"
          v-model="form.id"
          placeholder="아이디를 입력하세요"
          required
          readonly
        />
      </div>

      <!-- 이름 -->
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

      <!-- 이메일 -->
      <div class="input-group">
        <label for="email">이메일</label>
        <input
          type="email"
          id="email"
          v-model="form.email"
          placeholder="이메일을 입력하세요"
          required
          readonly
        />
      </div>

      <!-- 비밀번호 -->
      <div class="input-group">
        <label for="password">새 비밀번호</label>
        <input
          type="password"
          id="password"
          v-model="form.password"
          placeholder="새 비밀번호를 입력하세요"
        />
      </div>

      <!-- 비밀번호 확인 -->
      <div class="input-group">
        <label for="confirmPassword">비밀번호 확인</label>
        <input
          type="password"
          id="confirmPassword"
          v-model="form.confirmPassword"
          placeholder="비밀번호를 다시 입력하세요"
        />
      </div>

      <button type="submit">수정 완료</button>

    </form>
  </div>
</template>

<script>
import {getMemberInfo, updateMemberInfo} from "@/api/member"; // 회원 정보 가져오기 및 수정 API
import {useAuthStore} from "@/stores/authStore";
import {toast} from "vue3-toastify";

export default {
  data() {
    return {
      form: {
        id: "",
        name: "",
        email: "",
        password: "", // 새 비밀번호
        confirmPassword: "", // 비밀번호 확인
      },
    };
  },
  async created() {
    try {
      const memberData = await getMemberInfo(); // 회원 정보를 가져오는 API 호출
      this.form.id = memberData.data.id;
      this.form.name = memberData.data.name;
      this.form.email = memberData.data.email;
    } catch (error) {
      toast.error("회원 정보를 불러오는 데 오류가 발생했습니다.");
    }
  },
  methods: {
    async handleSubmit() {

      if (this.form.password !== this.form.confirmPassword) {
        toast.error("비밀번호 확인이 일치하지 않습니다.")
        return;
      }

      try {
        // confirmPassword를 제외한 새로운 객체 생성
        const {confirmPassword, email, id, ...dataToSend} = this.form;

        const response = await updateMemberInfo(dataToSend); // 수정된 정보 서버로 전송
        if (response.status === 200) {

          const authStore = useAuthStore();
          authStore.memberName = this.form.name; // 이름 업데이트
          authStore.saveToSessionStorage(); // 세션 스토리지에 저장

          toast.success("회원 정보가 수정되었습니다.", {autoClose: false});
          setTimeout(() => {
            this.$router.push({name: "Home"});
          }, 1000);
        }
      } catch (error) {
        toast.error("회원 정보 수정 중 오류가 발생했습니다.");
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
