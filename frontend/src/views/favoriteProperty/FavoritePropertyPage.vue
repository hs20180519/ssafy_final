<template>
  <div class="favorite-property-page">
    <FavoritePropertyList />
  </div>
</template>

<script setup>
import FavoritePropertyList from "@/components/favoriteProperty/FavoritePropertyList.vue";
</script>

<style scoped>
.favorite-property-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
}
</style>
