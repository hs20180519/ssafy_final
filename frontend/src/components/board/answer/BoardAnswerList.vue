<template>
  <div class="answers">
    <h3>답변 목록</h3>
    <div v-if="answers.length">
      <div v-for="answer in answers" :key="answer.id">
        <p>{{ answer.content }}</p>
      </div>
    </div>
    <div v-else>
      <p>답변이 없습니다.</p>
    </div>
  </div>
</template>

<script>
import { getAnswers } from "@/api/board";
import { useAuthStore } from "@/stores/authStore";

export default {
  name: "AnswerList",
  props: ["questionId"],
  data() {
    return {
      answers: [],
    };
  },
  mounted() {
    this.fetchAnswers();
  },
  methods: {
    async fetchAnswers() {
      try {
        const authStore = useAuthStore();
        const token = authStore.getAuthToken;

        const response = await getAnswers(this.questionId, token);
        this.answers = response.data;
      } catch (error) {
        console.error("답변 목록 조회 중 오류 발생:", error);
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
</style>
