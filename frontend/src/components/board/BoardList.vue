<template>
  <div class="board-list">
    <h2>질문 목록</h2>
    <div v-if="questions.length">
      <BoardListItem
        v-for="question in questions"
        :key="question.id"
        :question="question"
      />
    </div>
    <div v-else>
      <p>등록된 질문이 없습니다.</p>
    </div>
    <router-link to="/board/create">
      <button>새 질문 등록</button>
    </router-link>
  </div>
</template>

<script>
import BoardListItem from "@/components/board/BoardListItem.vue";
import { getQuestions } from "@/api/board";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      questions: [],
    };
  },
  mounted() {
    this.fetchQuestions();
  },
  methods: {
    async fetchQuestions() {
      try {
        const response = await getQuestions();
        this.questions = response.data;
      } catch (error) {
        console.error("질문 목록을 가져오는 중 오류 발생:", error);
      }
    },
  },
};
</script>

<style scoped>
.board-list {
  padding: 20px;
}
</style>
