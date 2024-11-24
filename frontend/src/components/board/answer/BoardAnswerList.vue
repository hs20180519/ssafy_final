<template>
  <div class="answers">
    <h3>답변 목록</h3>
    <div v-if="answers.length">
      <div v-for="answer in answers" :key="answer.id" class="answer-item">
        <p v-if="editingAnswerId !== answer.id">{{ answer.content }}</p>

        <div v-else class="edit-container">
          <input v-model="editedContent" @keyup.enter="submitEdit(answer.id)" class="edit-input"/>
          <button class="submit-edit-btn" @click="submitEdit(answer.id)">
            <font-awesome-icon :icon="['fas', 'check']"/>
          </button>
        </div>

        <!-- 답변 작성자와 작성일자 표시 -->
        <div class="answer-author">
          <span>작성자: {{ answer.author }}</span>
        </div>
        <div class="answer-date">
          <span>답변일자: {{ formatDate(answer.createdAt) }}</span>
        </div>

        <div class="actions" v-if="isAdmin">
          <button v-if="editingAnswerId !== answer.id" @click="startEditing(answer)" class="edit-btn">
            <font-awesome-icon :icon="['fas', 'pen']"/>
          </button>
          <button v-else @click="cancelEditing" class="cancel-btn">
            <font-awesome-icon :icon="['fas', 'ban']"/>
          </button>
          <button @click="deleteAnswer(answer.id)" class="delete-btn">
            <font-awesome-icon :icon="['fas', 'trash']"/>
          </button>
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
import {useAuthStore} from "@/stores/authStore";

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
  computed: {
    isAdmin() {
      const authStore = useAuthStore();
      return authStore.memberRole === "admin"; // admin 역할 확인
    },
  },
  mounted() {
    this.fetchAnswers();
  },
  methods: {
    async fetchAnswers() {
      try {
        const response = await getAnswers(this.questionId);
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
  margin-top: 15px;
}

.answer-item {
  background-color: #f9f9f9;
  padding: 12px;
  border-radius: 6px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
}

.edit-container {
  display: flex;
  align-items: center;
  gap: 8px;
}

.edit-input {
  width: 100%;
  padding: 8px;
  font-size: 0.85rem; /* 크기 축소 */
  border-radius: 4px;
  border: 1px solid #ccc;
}

.answer-author,
.answer-date {
  font-size: 0.85rem; /* 크기 축소 */
  color: #666;
  margin-top: 8px;
}

.actions {
  display: flex;
  justify-content: flex-end;
  gap: 8px; /* 버튼 간 간격 축소 */
}

button {
  padding: 6px 10px; /* 버튼 크기 축소 */
  font-size: 0.85rem;
  border-radius: 4px;
  border: 1px solid transparent;
  cursor: pointer;
}

.submit-edit-btn {
  //background-color: #e0f7fa;
  color: #00796b;
}

.submit-edit-btn:hover {
  background-color: #b2dfdb;
}

.edit-btn {
  //background-color: #e0f7fa;
  color: #00796b;
}

.edit-btn:hover {
  background-color: #b2dfdb;
}

.cancel-btn {
  background-color: #fbe9e7;
  color: #d84315;
}

.cancel-btn:hover {
  background-color: #ffccbc;
}

.delete-btn {
  background-color: #ffebee;
  color: #c62828;
}

.delete-btn:hover {
  background-color: #ef9a9a;
}
</style>
