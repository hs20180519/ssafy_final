<template>
  <div class="edit-question-page">
    <EditQuestion/>
  </div>
</template>

<script>
import EditQuestion from "@/components/board/question/EditQuestion.vue";

export default {
  name: 'EditQuestionPage',
  components: {
    EditQuestion,
  },
};
</script>

<style scoped>
.edit-question-page {
  padding: 120px;
}
</style>
