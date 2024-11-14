<template>
  <div class="home">
    <Navbar/>
    <div class="main-content-wrapper">
      <MainContent/>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/layout/SideNavbar.vue'
import MainContent from '@/components/main/MainContent.vue';

export default {
  name: 'Home',
  components: {
    Navbar,
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
