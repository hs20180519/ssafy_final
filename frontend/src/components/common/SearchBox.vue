<template>
  <div class="search-box">
    <input
      type="text"
      placeholder="지역, 단지명 또는 매물명을 입력해주세요."
      v-model="houseDealStore.keyword"
    />
    <font-awesome-icon
      :icon="['fas', 'magnifying-glass']"
      @click.prevent='$emit("onSearch", searchQuery)'
      class="search-icon"
    />
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {useHouseDealStore} from '@/stores/houseDealStore';

const houseDealStore = useHouseDealStore();
const searchQuery = ref("");
</script>

<style scoped>
.search-box {
  display: flex;
  align-items: center;
  border: 1px solid #ccc; /* 경계선 색상 */
  border-radius: 50px; /* 부드러운 둥근 모서리 */
  padding: 0.5em 1em; /* 좌우 패딩을 조금 더 여유있게 */
  width: 100vh;
  background-color: #fff; /* 배경색 흰색으로 설정 */
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1); /* 미세한 그림자 */

}

input {
  border: none;
  outline: none;
  flex: 1;
  font-size: 1rem; /* 글씨 크기 통일 */
  color: #333; /* 텍스트 색상 */
}

.search-icon {
  cursor: pointer;
  font-size: 1.2em;
  color: #888; /* 아이콘 색상 */
}

.search-box:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2); /* hover 시 그림자 효과 */
}

input::placeholder {
  color: #bbb; /* placeholder 색상 */
}
</style>
