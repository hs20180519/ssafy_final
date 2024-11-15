<template>
  <Navbar/>
  <router-view/>
</template>

<script>
import Navbar from "@/components/layout/SideNavbar.vue";

export default {
  name: 'App',
  components: {
    Navbar
  }
};
</script>

<style>
/* 스타일 추가 */
</style>
