<template>
  <div class="board-question-details-page">
    <BoardQuestionDetails :questionId="questionId"/>
    <BoardAnswerList ref="answerList" :questionId="questionId"/>
    <BoardAnswerForm
      :questionId="questionId"
      @answerSubmitted="refreshAnswers"
    />
  </div>
</template>

<script>
import BoardQuestionDetails from "@/components/board/question/BoardQuestionDetails.vue";
import BoardAnswerForm from "@/components/board/answer/BoardAnswerForm.vue";
import BoardAnswerList from "@/components/board/answer/BoardAnswerList.vue";

export default {
  name: "BoardQuestionDetailsPage",
  components: {
    BoardQuestionDetails,
    BoardAnswerList,
    BoardAnswerForm,
  },
  data() {
    return {
      questionId: this.$route.params.id,
    };
  },
  methods: {
    async refreshAnswers() {
      await this.$refs.answerList.fetchAnswers();
    },
  },
};
</script>

<style scoped>
.board-question-details-page {
  padding-left: 200px;
  padding-right: 140px;
}
</style>
