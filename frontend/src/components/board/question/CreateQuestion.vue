<template>
  <div class="create-question">
    <h2>새 질문 등록</h2>
    <form @submit.prevent="submitQuestion">
      <input v-model="title" placeholder="제목" required />
      <textarea v-model="content" placeholder="내용" required></textarea>
      <button type="submit">질문 등록</button>
    </form>
  </div>
</template>

<script>
import { createQuestion } from "@/api/board";

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
      const body = { title: this.title, content: this.content };
      console.log(body);
      createQuestion(
        body,
        () => {
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
  padding: 20px;
}
</style>
