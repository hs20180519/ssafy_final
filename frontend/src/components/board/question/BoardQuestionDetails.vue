<template>
  <div class="question-details">
    <h2 class="question-title">{{ question.title }}</h2>
    <div class="question-meta">
      <p class="question-author">작성자: {{ question.author }}</p>
      <p class="question-date">{{ formattedDate }}</p>
    </div>
    <p class="question-content">{{ question.content }}</p>
    <div class="buttons" v-if="canEditOrDelete">
      <button @click="editQuestion" class="edit-btn">수정</button>
      <button @click="deleteQuestion" class="delete-btn">삭제</button>
    </div>
  </div>
</template>

<script>
import {getQuestionDetails, deleteQuestion} from "@/api/board";
import {useAuthStore} from "@/stores/authStore";

export default {
  name: "QuestionDetails",
  props: ["questionId"],
  data() {
    return {
      question: {},
    };
  },
  computed: {
    // 날짜 포맷을 변경하여 출력
    formattedDate() {
      const date = new Date(this.question.createdAt);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      return `${year}.${month}.${day} ${hours}:${minutes}`;
    },
    // 현재 사용자가 작성자와 일치하면 수정/삭제 버튼을 보여준다.
    canEditOrDelete() {
      const authStore = useAuthStore();
      return authStore.memberId === this.question.author;
    },
  },
  mounted() {
    this.fetchQuestionDetails();
  },
  methods: {
    async fetchQuestionDetails() {
      try {
        const response = await getQuestionDetails(this.questionId);
        this.question = response.data;
      } catch (error) {
        console.error("질문 상세 조회 중 오류 발생:", error);
      }
    },
    editQuestion() {
      this.$router.push({
        name: "EditQuestion",
        params: {questionId: this.question.id},
      });
    },
    async deleteQuestion() {
      try {
        await deleteQuestion(this.question.id, () => {
          this.$router.push({name: "BoardList"}); // 삭제 후 게시판 목록으로 이동
        });
      } catch (error) {
        console.error("삭제 실패", error);
      }
    },
  },
};
</script>

<style scoped>
.question-details {

  margin: 80px auto;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.question-title {
  font-size: 2rem;
  font-weight: 600;
  color: #333;
  margin-bottom: 15px;
}

.question-meta {
  font-size: 0.9rem;
  color: #777;
  margin-bottom: 20px;
}

.question-author,
.question-date {
  margin-bottom: 5px;
}

.question-content {
  font-size: 1.1rem;
  color: #555;
  line-height: 1.6;
  margin-bottom: 30px;
}

.buttons {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

button {
  padding: 8px 16px;
  border-radius: 5px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

.edit-btn {
  background-color: #70abd8;
  color: white;
  border: none;
}

.edit-btn:hover {
  background-color: #0056b3;
}

.delete-btn {
  background-color: #d26464;
  color: white;
  border: none;
}

.delete-btn:hover {
  background-color: #e60000;
}
</style>
