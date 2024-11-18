<template>
  <div class="question-details">
    <h2>{{ question.title }}</h2>
    <div class="buttons">
      <button @click="editQuestion">수정</button>
      <button @click="deleteQuestion">삭제</button>
    </div>
    <p>{{ question.content }}</p>
    <p>작성자: {{ question.author }}</p>
  </div>
</template>

<script>
import {
  getQuestionDetails,
  updateQuestion,
  deleteQuestion,
} from "@/api/board";
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
    editQuestion() {
      console.log("Edit question");
      this.$router.push({
        name: "edit-question",
        params: { questionId: this.question.id },
      });
    },

    async deleteQuestion() {
      const token = useAuthStore().getAuthToken;

      try {
        await deleteQuestion(
          this.question.id,
          token,
          (response) => {
            console.log("Question deleted:", response);
            this.$router.push({ name: "BoardList" }); // Redirect to the board list or a relevant page after deletion
          },
          (error) => {
            console.error("Error deleting question:", error);
          }
        );
      } catch (error) {
        console.error("Failed to delete question:", error);
      }
    },
  },
};
</script>

<style scoped>
.question-details {
  margin-bottom: 20px;
}

.buttons {
  margin: 10px 0;
}

button {
  margin-right: 10px;
  padding: 5px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
