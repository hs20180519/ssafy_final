<template>
  <div class="search-box">
    <input type="text" placeholder="광주 장덕동" v-model="searchQuery"/>
    <font-awesome-icon :icon="['fas', 'magnifying-glass']" @click="search" class="search-icon"/>
  </div>
</template>

<script>
export default {
  name: 'SearchBox',
  data() {
    return {
      searchQuery: '',
    };
  },
  methods: {
    search() {
      // Implement search logic here
      console.log('Searching for:', this.searchQuery);
    },
  },
};
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
