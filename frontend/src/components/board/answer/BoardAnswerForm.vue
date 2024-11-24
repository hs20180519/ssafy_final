<template>
  <div v-if="isAdmin" class="answer-form">
    <textarea v-model="newAnswer" placeholder="답변을 작성하세요..." class="answer-input" rows="4"></textarea>
    <div class="buttons">
      <button @click="submitAnswer" class="submit-btn">
        <font-awesome-icon :icon="['fas', 'check']"/>
      </button>
    </div>
  </div>
</template>

<script>
import {createAnswer} from "@/api/board";
import {useAuthStore} from "@/stores/authStore";

export default {
  name: "AnswerForm",
  props: ["questionId"],
  data() {
    return {
      newAnswer: "",
    };
  },
  computed: {
    isAdmin() {
      const authStore = useAuthStore();
      return authStore.getMemberRole === "admin"; // admin 역할 확인
    },
  },
  methods: {
    async submitAnswer() {
      if (this.newAnswer.trim() === "") return;

      try {
        await createAnswer(this.questionId, this.newAnswer);
        this.newAnswer = "";
        this.$emit("answerSubmitted");
      } catch (error) {
        console.error("답변 등록 중 오류 발생:", error);
      }
    },
  },
};
</script>

<style scoped>
.answer-form {
  margin-top: 15px;
  padding: 15px;
  background-color: #fafafa;
  border-radius: 6px;
  border: 1px solid #ddd;
}

.answer-input {
  width: 97%;
  padding: 10px;
  font-size: 0.9rem; /* 크기 축소 */
  border-radius: 4px;
  border: 1px solid #ccc;
  margin-bottom: 10px; /* 여백 축소 */
  resize: none;
}

.buttons {
  display: flex;
  justify-content: flex-end;
}

.submit-btn {
  padding: 8px 12px; /* 버튼 크기 축소 */
  background-color: #e0f7fa;
  color: #00796b;
  border: 1px solid #b2dfdb;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9rem;
}

.submit-btn:hover {
  background-color: #b2dfdb;
}
</style>
