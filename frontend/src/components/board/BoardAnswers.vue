<template>
    <div class="board-answers">
      <h2>답변 관리</h2>
      <div v-if="answers.length">
        <div v-for="answer in answers" :key="answer.id">
          <p>{{ answer.content }}</p>
          <button @click="deleteAnswer(answer.id)">삭제</button>
        </div>
      </div>
      <div v-else>
        <p>답변이 없습니다.</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'BoardAnswers',
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
          const response = await axios.get(`http://localhost:80/api/board/questions/${this.$route.params.questionId}/answers`);
          this.answers = response.data;
        } catch (error) {
          console.error('답변 목록 조회 중 오류 발생:', error);
        }
      },
      async deleteAnswer(answerId) {
        try {
          await axios.delete(`/api/board/answers/${answerId}`);
          this.fetchAnswers(); // 삭제 후 답변 목록 갱신
        } catch (error) {
          console.error('답변 삭제 중 오류 발생:', error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .board-answers {
    padding: 20px;
  }
  </style>
  