<template>
  <div class="answers">
    <h3>답변 목록</h3>
    <div v-if="answers.length">
      <div v-for="answer in answers" :key="answer.id" class="answer-item">
        <p v-if="editingAnswerId !== answer.id">{{ answer.content }}</p>

        <div v-else class="edit-container">
          <input v-model="editedContent" @keyup.enter="submitEdit(answer.id)" class="edit-input"/>
          <button class="submit-edit-btn" @click="submitEdit(answer.id)">수정완료</button>
        </div>

        <!-- 답변 작성자와 작성일자 표시 -->
        <div class="answer-author">
          <span>작성자: {{ answer.author }}</span>
        </div>
        <div class="answer-date">
          <span>답변일자: {{ formatDate(answer.createdAt) }}</span>
        </div>

        <div class="actions">
          <button v-if="editingAnswerId !== answer.id" @click="startEditing(answer)" class="edit-btn">수정</button>
          <button v-else @click="cancelEditing" class="cancel-btn">취소</button>
          <button @click="deleteAnswer(answer.id)" class="delete-btn">삭제</button>
        </div>
      </div>
    </div>
    <div v-else>
      <p>답변이 없습니다.</p>
    </div>
  </div>
</template>

<script>
import {getAnswers, updateAnswer, deleteAnswer} from "@/api/board";

export default {
  name: "AnswerList",
  props: ["questionId"],
  data() {
    return {
      answers: [],
      editingAnswerId: null,
      editedContent: "",
    };
  },
  mounted() {
    this.fetchAnswers();
  },
  methods: {
    async fetchAnswers() {
      try {
        const response = await getAnswers(this.questionId);
        console.log(response);
        this.answers = response.data;
      } catch (error) {
        console.error("답변 목록 조회 중 오류 발생:", error);
      }
    },
    async deleteAnswer(answerId) {
      try {
        await deleteAnswer(answerId);
        this.fetchAnswers(); // 답변 삭제 후 목록 새로고침
      } catch (error) {
        console.error("답변 삭제 중 오류 발생:", error);
      }
    },
    startEditing(answer) {
      this.editingAnswerId = answer.id;
      this.editedContent = answer.content;
    },
    cancelEditing() {
      this.editingAnswerId = null;
      this.editedContent = "";
    },
    async submitEdit(answerId) {
      try {
        await updateAnswer(answerId, this.editedContent);
        this.editingAnswerId = null;
        this.editedContent = "";
        this.fetchAnswers();
      } catch (error) {
        console.error("답변 수정 중 오류 발생:", error);
      }
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");
      return `${year}-${month}-${day} ${hours}:${minutes}`;
    },
  },
};
</script>

<style scoped>
.answers {
  margin-top: 20px;
}

.answer-item {
  background-color: white;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.edit-container {
  display: flex;
  align-items: center;
  gap: 10px;
}

.edit-input {
  width: 80%;
  padding: 8px;
  font-size: 1rem;
  border-radius: 6px;
  border: 1px solid #ddd;
}

.answer-author,
.answer-date {
  font-size: 0.9rem;
  color: #555;
  margin-top: 10px;
}

.actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.submit-edit-btn,
.edit-btn,
.cancel-btn,
.delete-btn {
  padding: 8px 16px;
  font-size: 0.9rem;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.submit-edit-btn {
  background-color: #007bff;
  color: white;
}

.submit-edit-btn:hover {
  background-color: #0056b3;
  transform: translateY(-2px);
}

.submit-edit-btn:active {
  background-color: #004085;
  transform: translateY(0);
}

.edit-btn {
  background-color: #70abd8;
  color: white;
}

.edit-btn:hover {
  background-color: #004085;
}

.cancel-btn {
  background-color: #6c757d;
  color: white;
}

.cancel-btn:hover {
  background-color: #5a6268;
}

.delete-btn {
  background-color: #d26464;
  color: white;
}

.delete-btn:hover {
  background-color: #c82333;
}
</style>
