<template>
  <div class="question-details">
    <h2>{{ question.title }}</h2>
    <p>{{ question.content }}</p>
    <p>작성자: {{ question.author }}</p>
  </div>
</template>

<script>
import { getQuestionDetails } from "@/api/board";
import { useAuthStore } from "@/stores/authStore";

export default {
  name: "QuestionDetails",
  props: ["questionId"],
  data() {
    return {
      question: {},
    };
  },
  mounted() {
    this.fetchQuestionDetails();
  },
  methods: {
    async fetchQuestionDetails() {
      try {
        const authStore = useAuthStore();
        const token = authStore.getAuthToken;

        const response = await getQuestionDetails(this.questionId, token);
        this.question = response.data;
      } catch (error) {
        console.error("질문 상세 조회 중 오류 발생:", error);
      }
    },
    refreshAnswers() {
      this.fetchAnswers(); // 새로 답변 목록을 불러와 화면에 반영
    },
  },
};
</script>

<style scoped>
.question-details {
  margin-bottom: 20px;
}
</style>
