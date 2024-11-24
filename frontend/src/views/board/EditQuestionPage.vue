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
  padding: 160px 90px 120px 150px;
}
</style>
