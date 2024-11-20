<template>
  <div class="answers">
    <h3>답변 목록</h3>
    <div v-if="answers.length">
      <div v-for="answer in answers" :key="answer.id" class="answer-item">
        <p v-if="editingAnswerId !== answer.id">{{ answer.content }}</p>
        <div v-else class="edit-container">
          <input v-model="editedContent" @keyup.enter="submitEdit(answer.id)" />
          <button class="submit-edit-btn" @click="submitEdit(answer.id)">
            수정완료
          </button>
        </div>
        <div class="actions">
          <button
            v-if="editingAnswerId !== answer.id"
            @click="startEditing(answer)"
          >
            수정
          </button>
          <button v-else @click="cancelEditing">취소</button>
          <button @click="deleteAnswer(answer.id)">삭제</button>
        </div>
      </div>
    </div>
    <div v-else>
      <p>답변이 없습니다.</p>
    </div>
  </div>
</template>

<script>
import { getAnswers, updateAnswer, deleteAnswer } from "@/api/board";

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
        this.answers = response.data;
      } catch (error) {
        console.error("답변 목록 조회 중 오류 발생:", error);
      }
    },
    async deleteAnswer(answerId) {
      try {
        await deleteAnswer(
          answerId,
          () => {
            console.log("답변 삭제 성공");
            this.refreshAnswers();
          },
          (error) => console.error("답변 삭제 중 오류 발생:", error)
        );
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
        await updateAnswer(
          answerId,
          this.editedContent,
          () => {
            console.log("답변 수정 성공");
            this.editingAnswerId = null;
            this.editedContent = "";
            this.refreshAnswers();
          },
          (error) => console.error("답변 수정 중 오류 발생:", error)
        );
      } catch (error) {
        console.error("답변 수정 중 오류 발생:", error);
      }
    },
    refreshAnswers() {
      this.fetchAnswers();
    },
  },
};
</script>

<style scoped>
.answers {
  margin-top: 20px;
}

.answer-item {
  margin-bottom: 15px;
}

.edit-container {
  display: flex;
  align-items: center;
  gap: 8px;
}

.submit-edit-btn {
  padding: 4px 8px;
  font-size: 12px;
  cursor: pointer;
}

.actions {
  margin-top: 10px;
}

.actions button {
  margin-right: 10px;
}
</style>
