<template>
  <div class="board-list">
    <h2 class="title">문의하기</h2>
    <div class="button-container">
      <router-link to="/board/create">
        <button class="new-question-btn">
          문의하기
          <font-awesome-icon :icon="['fas', 'pen']"/>
        </button>
      </router-link>
    </div>
    <div v-if="questions.length">
      <BoardListItem
        v-for="question in questions"
        :key="question.id"
        :question="question"
      />
    </div>
    <div v-else>
      <p class="no-questions">등록된 질문이 없습니다.</p>
    </div>

  </div>
</template>

<script>
import BoardListItem from "@/components/board/BoardListItem.vue";
import {getQuestions} from "@/api/board";
import {toast} from "vue3-toastify";

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
        toast.error("질문 목록을 가져오는 중 오류 발생");
      }
    },
  },
};
</script>

<style scoped>
.board-list {
  padding-left: 60px;
  //padding: 20px;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f9f9f9;
  max-width: 900px;
  margin: 0 auto;
}

.title {
  font-size: 1.8rem;
  font-weight: bold;
  color: #333;
  text-align: center;
  margin-bottom: 20px;
}

.no-questions {
  text-align: center;
  color: #777;
  font-size: 1.1rem;
}

.button-container {
  display: flex;
  justify-content: flex-end;
  margin: 20px 0;
}

.new-question-btn {
  display: inline-block;
  width: 100px;
  margin-right: 10px;
  padding: 10px;
  background-color: #676565;
  color: white;
  font-size: 0.8rem;
  border: none;
  border-radius: 5px;
  text-align: center;
  //margin-top: 30px;
  cursor: pointer;
  transition: background-color 0.3s;
  text-decoration: none;
}

.new-question-btn:hover {
  background-color: #333;
}


</style>
