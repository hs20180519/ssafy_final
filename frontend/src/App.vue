<template>
  <Navbar/>
  <router-view/>
</template>

<script>
import Navbar from "@/components/layout/SideNavbar.vue";
import {ToastifyContainer} from "vue3-toastify";
import 'vue3-toastify/dist/index.css';

export default {
  name: 'App',
  components: {
    Navbar,
  }
};
</script>

<style>
html,
body {
  height: 100%;
  margin: 0; /* 기본 마진 제거 */
  //overflow: hidden; /* 강제로 스크롤 제거 */
  background-color: #f8f9fa; /* 메인 배경색 */
}

</style>
