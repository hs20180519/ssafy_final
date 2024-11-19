<template>
  <div class="answer-form">
    <textarea v-model="newAnswer" placeholder="답변을 작성하세요..."></textarea>
    <button @click="submitAnswer">답변 등록</button>
  </div>
</template>

<script>
import { createAnswer } from "@/api/board";
import { useAuthStore } from "@/stores/authStore";

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
      console.log("submitAnswer 호출됨");
      if (this.newAnswer.trim() === "") return;

      const authStore = useAuthStore();
      const token = authStore.getAuthToken;

      try {
        await createAnswer(this.questionId, this.newAnswer, token);
        // 새로운 답변이 등록되었으므로 입력창 초기화
        this.newAnswer = "";
        // 부모 컴포넌트에 'answerSubmitted' 이벤트 전달
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
}
</style>
