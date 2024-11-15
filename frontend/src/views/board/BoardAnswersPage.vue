<template>
    <div class="board-answers-page">
      <BoardAnswers />
    </div>
  </template>
  
  <script>
  import BoardAnswers from '@/components/board/BoardAnswers.vue';
  
  export default {
    name: 'BoardAnswersPage',
    components: {
      BoardAnswers,
    },
  };
  </script>
  
  <style scoped>
  .board-answers-page {
    padding: 20px;
  }
  </style>
  