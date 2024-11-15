<template>
  <div class="search-box">
    <input type="text" placeholder="광주 장덕동" v-model="searchQuery"/>
    <font-awesome-icon :icon="['fas', 'magnifying-glass']" @click='onSearch' class="search-icon"/>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { searchKeyword } from "@/api/search";

const isLoaded = ref(false);
const result = ref();
const searchQuery = ref("");

const onSearch = () => {
  searchQuery
    console.log(searchQuery);
    searchKeyword(
        searchQuery,
        ({ data }) => {
            result.value = data;
            isLoaded.value = true;
        },
        (error) => {
            console.log(error);
        }
    )
}
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
