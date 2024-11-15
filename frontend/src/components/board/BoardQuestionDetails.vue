<template>
    <div class="question-details">
      <h2>{{ question.title }}</h2>
      <p>{{ question.content }}</p>
      <p>작성자: {{ question.author }}</p>
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
        <div>
          <textarea v-model="newAnswer" placeholder="답변을 작성하세요..."></textarea>
          <button @click="submitAnswer">답변 등록</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'BoardQuestionDetails',
    data() {
      return {
        question: {},
        answers: [],
        newAnswer: '',
      };
    },
    mounted() {
      this.fetchQuestionDetails();
      this.fetchAnswers();
    },
    methods: {
      async fetchQuestionDetails() {
        try {
          const response = await axios.get(`http://localhost:80/api/board/questions/${this.$route.params.id}`);
          this.question = response.data;
        } catch (error) {
          console.error('질문 상세 조회 중 오류 발생:', error);
        }
      },
      async fetchAnswers() {
        try {
          const response = await axios.get(`http://localhost:80/api/board/questions/${this.$route.params.id}/answers`);
          this.answers = response.data;
        } catch (error) {
          console.error('답변 목록 조회 중 오류 발생:', error);
        }
      },
      async submitAnswer() {
        if (this.newAnswer.trim() === '') return;
        try {
          await axios.post(`http://localhost:80/api/board/questions/${this.$route.params.id}/answers`, {
            content: this.newAnswer,
          });
          this.newAnswer = '';
          this.fetchAnswers();  // 답변 갱신
        } catch (error) {
          console.error('답변 등록 중 오류 발생:', error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .question-details {
    padding: 20px;
  }
  .answers {
    margin-top: 20px;
  }
  </style>
  