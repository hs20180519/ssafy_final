<template>
  <div class="question-item">
    <router-link :to="'/board/questions/' + question.id" class="question-link">
      <h3 class="question-title">{{ question.title }}</h3>
    </router-link>
    <div class="question-meta">
      <p class="question-author">{{ question.author }} · {{ formattedDate }}</p>
      <div class="question-info">
        <span class="views"
          ><font-awesome-icon :icon="['fas', 'eye']" />
          {{ question.viewCount }}</span
        >
        <span class="comments"
          ><font-awesome-icon :icon="['fas', 'comment']" />
          {{ question.answerCount }}</span
        >
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
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");

      return `${year}.${month}.${day} ${hours}:${minutes}`;
    },
  },
};
</script>

<style scoped>
.question-item {
  margin-bottom: 10px; /* 항목 간 간격 축소 */
  padding: 15px; /* 패딩 축소 */
  border: 1px solid #ddd; /* 경계 색상 더 은은하게 */
  border-radius: 6px; /* 둥근 모서리 크기 축소 */
  background-color: #f9f9f9; /* 더 밝고 단순한 배경 */
  transition: transform 0.2s, box-shadow 0.2s; /* 애니메이션 시간 축소 */
}

.question-item:hover {
  transform: translateY(-3px); /* 이동 거리 축소 */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 그림자 크기 축소 */
}

.question-title {
  font-size: 1.1rem; /* 제목 크기 축소 */
  font-weight: 500; /* 더 가벼운 굵기 */
  color: #333; /* 약간 덜 튀는 색상 */
  margin-bottom: 8px;
}

.question-title:hover {
  color: #0056b3; /* 더 차분한 파란색 */
}

.question-link {
  text-decoration: none; /* 링크 밑줄 제거 */
}

.question-meta {
  display: flex;
  justify-content: space-between;
  font-size: 0.8rem; /* 글꼴 크기 축소 */
  color: #666; /* 더 연한 색상 */
  margin-bottom: 8px;
}

.question-author {
  margin-bottom: 2px; /* 작성자와 작성일 간격 최소화 */
}

.question-info {
  display: flex;
  align-items: center;
  gap: 6px; /* 항목 간 간격 축소 */
  font-size: 0.8rem; /* 크기 축소 */
  color: #888; /* 더 은은한 색상 */
}

.tags {
  margin-top: 8px; /* 태그 위 간격 축소 */
}

.tag {
  display: inline-block;
  background-color: #e6e6e6; /* 태그 배경 조금 더 밝게 */
  color: #444; /* 글자 색 약간 어둡게 */
  padding: 4px 8px; /* 태그 패딩 축소 */
  border-radius: 12px; /* 태그 둥근 모서리 축소 */
  font-size: 0.75rem; /* 글꼴 크기 축소 */
  margin-right: 6px; /* 태그 간 간격 축소 */
  margin-top: 4px;
}
</style>
