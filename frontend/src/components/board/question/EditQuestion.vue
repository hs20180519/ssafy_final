<template>
  <div class="edit-question">
    <h2>질문 수정</h2>
    <form @submit.prevent="submitEdit">
      <input
        v-model="question.title"
        placeholder="제목"
        required
        class="input"
      />
      <textarea
        v-model="question.content"
        placeholder="내용"
        required
        class="input"
      ></textarea>
      <button type="submit" class="btn">수정 완료</button>
    </form>
  </div>
</template>

<script>
import {getQuestionDetails, updateQuestion} from "@/api/board";

export default {
  name: "EditQuestion",
  data() {
    return {
      question: {
        title: "",
        content: "",
      },
    };
  },
  mounted() {
    this.fetchQuestionDetails();
  },
  methods: {
    async fetchQuestionDetails() {
      const questionId = this.$route.params.id;
      try {
        const response = await getQuestionDetails(questionId);
        this.question = response.data;
      } catch (error) {
        console.error("질문 상세 조회 중 오류 발생:", error);
      }
    },
    async submitEdit() {
      const questionId = this.$route.params.id;
      try {
        await updateQuestion(questionId, this.question);
        this.$router.push({name: "BoardQuestionDetails", params: {questionId}});
      } catch (error) {
        console.error("질문 수정 중 오류 발생:", error);
      }
    },
  },
};
</script>

<style scoped>
.edit-question {
  max-width: 600px;
  margin: 20px auto;
}

h2 {
  font-size: 1.5rem;
  text-align: center;
  margin-bottom: 20px;
}

.input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.btn {
  width: 100%;
  padding: 10px;
  background: #4e4e4e;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover {
  background: #333;
}
</style>
