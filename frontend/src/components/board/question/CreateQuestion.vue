<template>
  <div class="create-question">
    <h2>새 질문 등록</h2>
    <form @submit.prevent="submitQuestion">
      <input
        v-model="title"
        placeholder="제목"
        required
        class="input-field"
      />
      <textarea
        v-model="content"
        placeholder="내용"
        required
        class="input-field"
      ></textarea>
      <button type="submit" class="submit-btn">질문 등록</button>
    </form>
  </div>
</template>

<script>
import {createQuestion} from "@/api/board";
import {toast} from "vue3-toastify";

export default {
  name: "CreateQuestion",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    async submitQuestion() {
      const body = {title: this.title, content: this.content};
      console.log(body);
      await createQuestion(
        body,
        () => {
          toast.success("질문이 성공적으로 등록되었습니다.", {
            autoClose: 3000, // 3000ms 동안 토스트 메시지가 표시됨
          });
          this.$router.push("/board"); // 성공 시 페이지 이동
        },
        (error) => {
          console.error("질문 등록 중 오류 발생:", error);
        }
      );
    },
  },
};
</script>

<style scoped>
.create-question {
  //width: 100%;
  max-width: 600px;
  margin: 40px auto;
  padding: 30px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

h2 {
  font-size: 2rem;
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.input-field {
  width: 100%;
  padding: 12px;
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
  box-sizing: border-box;
}

.input-field:focus {
  border-color: #4e4e4e;
  outline: none;
}

.submit-btn {
  width: 100%;
  padding: 12px;
  background-color: #4e4e4e;
  color: white;
  font-size: 1.2rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-btn:hover {
  background-color: #333;
}
</style>
