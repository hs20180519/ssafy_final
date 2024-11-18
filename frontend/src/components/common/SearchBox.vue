<template>
  <div class="search-box">
    <input type="text" placeholder="지역, 단지명 또는 매물명을 입력해주세요." v-model="houseDealStore.keyword"/>
    <font-awesome-icon :icon="['fas', 'magnifying-glass']" @click.prevent='$emit("onSearch", searchQuery)' class="search-icon"/>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useHouseDealStore } from '@/stores/houseDealStore';

const houseDealStore = useHouseDealStore();
const searchQuery = ref("");

</script>

<style scoped>
.search-box {
  display: flex;
  align-items: center;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 0.5em;
  width: 700px;
}

input {
  border: none;
  outline: none;
  flex: 1;
  padding: 0.5em;
}

.search-icon {
  cursor: pointer;
  margin-left: 10px;
  font-size: 1.2em;
  color: #555;
}

</style>
