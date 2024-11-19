<template>
  <div class="question-item">
    <router-link :to="'/board/questions/' + question.id">
      <h3>{{ question.title }}</h3>
    </router-link>
    <p>{{ question.content }}</p>
    <p>작성자: {{ question.author }}</p>
  </div>
</template>

<script>
export default {
  name: "BoardListItem",
  props: {
    question: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.question-item {
  margin-bottom: 15px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}
</style>
