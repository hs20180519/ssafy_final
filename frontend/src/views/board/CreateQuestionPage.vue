<template>
    <div class="create-question-page">
      <CreateQuestion />
    </div>
  </template>
  
  <script>
  import CreateQuestion from '@/components/board/question/CreateQuestion.vue';
  
  export default {
    name: 'CreateQuestionPage',
    components: {
      CreateQuestion,
    },
  };
  </script>
  
  <style scoped>
  .create-question-page {
    padding: 120px;
  }
  </style>
  