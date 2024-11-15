<template>
    <div class="board-question-details-page">
      <BoardQuestionDetails />
    </div>
  </template>
  
  <script>
  import BoardQuestionDetails from '@/components/board/BoardQuestionDetails.vue';
  
  export default {
    name: 'BoardQuestionDetailsPage',
    components: {
      BoardQuestionDetails,
    },
  };
  </script>
  
  <style scoped>
  .board-question-details-page {
    padding: 20px;
  }
  </style>
  