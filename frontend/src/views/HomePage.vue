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
  //display: flex;
  //flex-direction: column;
  //align-items: center;
  //height: 100vh;
  background-color: #f8f9fa; /* 메인 배경색 */
}

.main-content-wrapper {
  flex: 1;
  background-color: #f8f9fa; /* 메인 배경색 */
  //display: flex;
  //align-items: center;
  //justify-content: center;
}


</style>
