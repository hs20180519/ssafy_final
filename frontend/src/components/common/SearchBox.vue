<template>
  <div class="search-box">
    <input
      type="text"
      placeholder="지역, 단지명 또는 매물명을 입력해주세요."
      v-model="houseDealStore.keyword"
      @keyup.enter='$emit("onSearch", searchQuery)'
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
  border: 1px solid #ccc;
  border-radius: 50px;
  padding: 0.5em 1em;
  width: 90%; /* 부모의 width인 50% 안에서 작게 설정 */
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
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
