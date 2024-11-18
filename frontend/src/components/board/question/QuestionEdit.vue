<template>
  <div class="question-edit">
    <h2>질문 수정</h2>

    <!-- Form for editing question -->
    <form @submit.prevent="submitEdit">
      <div class="form-group">
        <label for="title">제목</label>
        <input
          type="text"
          id="title"
          v-model="question.title"
          placeholder="질문 제목을 입력하세요"
          required
        />
      </div>

      <div class="form-group">
        <label for="content">내용</label>
        <textarea
          id="content"
          v-model="question.content"
          placeholder="질문 내용을 입력하세요"
          required
        ></textarea>
      </div>

      <button type="submit">수정 완료</button>
    </form>
  </div>
</template>

<script>
import {getQuestionDetails, updateQuestion} from "@/api/board"; // Import the functions
import {useAuthStore} from "@/stores/authStore";

export default {
  name: "QuestionEdit",
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
    // Fetch question details to pre-fill the form
    async fetchQuestionDetails() {
      const questionId = this.$route.params.id;
      const authStore = useAuthStore();
      const token = authStore.getAuthToken;

      try {
        const response = await getQuestionDetails(questionId, token);
        this.question = response.data;
      } catch (error) {
        console.error("질문 상세 조회 중 오류 발생:", error);
      }
    },

    // Submit the edited question
    async submitEdit() {
      const questionId = this.$route.params.id;
      const authStore = useAuthStore();
      const token = authStore.getAuthToken;

      try {
        await updateQuestion(
          questionId,
          this.question,
          token,
          (response) => {
            console.log("질문이 수정되었습니다:", response);
            this.$router.push({name: "BoardQuestionDetails", params: {questionId: questionId}}); // Redirect to board list or appropriate page
          },
          (error) => {
            console.error("질문 수정 중 오류 발생:", error);
          }
        );
      } catch (error) {
        console.error("수정 실패:", error);
      }
    },
  },
};
</script>

<style scoped>
.question-edit {
  margin: 20px;
  padding: 120px;
}

h2 {
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

input,
textarea {
  width: 100%;
  padding: 8px;
  font-size: 16px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

button:hover {
  background-color: #0056b3;
}
</style>
