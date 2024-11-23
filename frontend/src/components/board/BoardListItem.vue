<template>
  <div class="question-item">
    <router-link :to="'/board/questions/' + question.id" class="question-link">
      <h3 class="question-title">{{ question.title }}</h3>
    </router-link>
    <div class="question-meta">
      <!-- 작성자 정보와 조회수, 답변을 세로로 배치 -->
      <p class="question-author">작성자 {{ question.author }} · {{ formattedDate }}</p>
      <div class="question-info">
        <span class="views"><font-awesome-icon :icon="['fas', 'eye']"/> {{ question.viewCount }}</span>
        <span class="comments"><font-awesome-icon :icon="['fas', 'comment']"/>{{ question.commentsCount }}</span>
      </div>
    </div>

    <div class="tags">
      <span v-for="tag in question.tags" :key="tag" class="tag">{{ tag }}</span>
    </div>
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
  computed: {
    // 작성일 포맷 변경
    formattedDate() {
      const date = new Date(this.question.createdAt);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');

      return `${year}.${month}.${day} ${hours}:${minutes}`;
    },
  },
};
</script>

<style scoped>
.question-item {
  margin-bottom: 20px;
  padding: 20px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  background-color: #ffffff;
  transition: transform 0.3s, box-shadow 0.3s;
}

.question-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.question-title {
  font-size: 1.4rem;
  font-weight: 600;
  margin-bottom: 10px;
  text-decoration: none; /* 여기서 적용 */
}

.question-title:hover {
  color: #007BFF;
}

.question-link {
  text-decoration: none; /* router-link에도 추가 */
}

.question-meta {
  display: flex;
  justify-content: space-between;
  font-size: 0.9rem;
  color: #777;
  margin-bottom: 10px;
}

.question-author {
  margin-bottom: 5px;
}

.question-info {
  display: flex;
  //flex-direction: column; /* 세로로 정렬 */
  align-items: flex-end; /* 오른쪽 정렬 */
  gap: 10px; /* 항목 간 간격 */
  font-size: 0.9rem;
  color: #888;
}

.tags {
  margin-top: 10px;
}

.tag {
  display: inline-block;
  background-color: #e0e0e0;
  color: #555;
  padding: 5px 10px;
  border-radius: 15px;
  font-size: 0.85rem;
  margin-right: 8px;
  margin-top: 5px;
}
</style>
