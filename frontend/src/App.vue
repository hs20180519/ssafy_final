<template>
  <Navbar />
  <router-view />
  <Chatbot />
</template>

<script>
import Navbar from "@/components/layout/SideNavbar.vue";
import "vue3-toastify/dist/index.css";
import Chatbot from "./components/common/Chatbot.vue";

export default {
  name: "App",
  components: {
    Navbar,
    Chatbot,
  },
};
</script>

<style>
html,
body {
  height: 100%;
  margin: 0; /* 기본 마진 제거 */
  background-color: #f8f9fa; /* 메인 배경색 */
  font-family: "Noto Sans KR";
}
</style>
