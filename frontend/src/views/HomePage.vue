<template>
  <div class="home">
    <div class="main-content-wrapper">
      <MainContent/>
    </div>
  </div>
</template>

<script>
import MainContent from '@/components/main/MainContent.vue';

export default {
  name: 'Home',
  components: {
    MainContent,
  },
};
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100vh;
}

.main-content-wrapper {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-bottom: 100px;
}

</style>
