<template>
  <div class="board-list-page">
    <BoardList />
    <router-link to="/board/create"> </router-link>
  </div>
</template>

<script>
import BoardList from "@/components/board/BoardList.vue";

export default {
  name: "BoardListPage",
  components: {
    BoardList,
  },
};
</script>

<style scoped>
.board-list-page {
  padding: 120px;
}
</style>
