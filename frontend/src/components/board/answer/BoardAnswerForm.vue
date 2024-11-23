<template>
  <div class="answer-form">
    <textarea v-model="newAnswer" placeholder="답변을 작성하세요..." class="answer-input" rows="4"></textarea>
    <div class="buttons">
      <button @click="submitAnswer" class="submit-btn">답변 등록</button>
    </div>
  </div>
</template>

<script>
import {createAnswer} from "@/api/board";

export default {
  name: "AnswerForm",
  props: ["questionId"],
  data() {
    return {
      newAnswer: "",
    };
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
  margin-top: 20px;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.answer-input {
  width: 97%;
  padding: 12px;
  font-size: 1rem;
  border-radius: 6px;
  border: 1px solid #ddd;
  margin-bottom: 15px;
  resize: none;
}

.buttons {
  display: flex;
  justify-content: flex-end; /* 버튼을 오른쪽에 배치 */
}

.submit-btn {
  padding: 10px 20px;
  background-color: #70abd8;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.submit-btn:hover {
  background-color: #0056b3;
  transform: translateY(-2px);
}

.submit-btn:active {
  background-color: #004085;
  transform: translateY(0);
}
</style>
