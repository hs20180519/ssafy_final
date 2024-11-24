<template>
  <div class="question-details">
    <h2 class="question-title">{{ question.title }}</h2>
    <div class="question-meta">
      <p class="question-author">작성자: {{ question.author }}</p>
      <p class="question-date">{{ formattedDate }}</p>
    </div>
    <p class="question-content">{{ question.content }}</p>
    <div class="buttons" v-if="canEditOrDelete">
      <button @click="editQuestion" class="edit-btn">
        <font-awesome-icon :icon="['fas', 'pen']"/>
      </button>
      <button @click="deleteQuestion" class="delete-btn">
        <font-awesome-icon :icon="['fas', 'trash']"/>
      </button>
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
  margin: 40px auto;
  padding: 15px;
  background-color: #f9f9f9;
  border-radius: 6px;
  border: 1px solid #ddd;
}

.question-title {
  font-size: 1.5rem; /* 제목 크기 축소 */
  font-weight: 500; /* 가벼운 굵기 */
  color: #333;
  margin-bottom: 10px;
}

.question-meta {
  font-size: 0.85rem; /* 크기 축소 */
  color: #666;
  margin-bottom: 15px;
}

.question-author,
.question-date {
  margin-bottom: 2px; /* 간격 축소 */
}

.question-content {
  font-size: 1rem; /* 크기 축소 */
  color: #444;
  line-height: 1.5; /* 줄 간격 축소 */
  margin-bottom: 20px;
}

.buttons {
  display: flex;
  justify-content: flex-end;
  gap: 8px; /* 버튼 간 간격 축소 */
}

button {
  padding: 6px 12px; /* 버튼 크기 축소 */
  font-size: 0.9rem;
  border-radius: 4px;
  border: 1px solid transparent;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.edit-btn {
  color: #00796b;
}

.edit-btn:hover {
  background-color: #b2dfdb;
}

.delete-btn {
  color: #c62828;
}

.delete-btn:hover {
  background-color: #ef9a9a;
}
</style>
