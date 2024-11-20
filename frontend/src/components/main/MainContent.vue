<template>
  <div class="main-content">
    <div class="logo">The Next Home</div>
    <h1>당신의 다음 집을 검색해보세요</h1>
    <!--    <p>FIND YOUR NEXT HOME</p>-->
    <div class="search-container">
      <SearchBox @onSearch="onSearch" />
      <SearchBoxResult
        v-if="isLoaded"
        :result="result"
        class="search-box-result"
      />
    </div>
    <div class="auth-links" v-if="!authStore.isLoggedIn">
      <router-link to="/login">로그인</router-link>
      /
      <router-link to="/signup">회원가입</router-link>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import SearchBox from "@/components/common/SearchBox.vue";
import SearchBoxResult from "@/components/common/SearchBoxResult.vue";
import { useAuthStore } from "@/stores/authStore";

export default {
  name: "MainContent",
  components: {
    SearchBox,
  },
};
</script>

<script setup>
import { defineEmits, onMounted } from "vue";
import { searchKeyword } from "@/api/search";
import { useHouseDealStore } from "@/stores/houseDealStore";

defineEmits(["onSearch"]);

const houseDealStore = useHouseDealStore();
const authStore = useAuthStore();

const isLoaded = ref(false);
const result = ref();

onMounted(() => {
  houseDealStore.setKeyword("");
});

const onSearch = () => {
  searchKeyword(
    houseDealStore.keyword,
    ({ data }) => {
      result.value = data;
      isLoaded.value = true;
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<style scoped>
.main-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  flex: 1;
  position: relative; /* SearchBoxResult와 auth-links의 z-index 기준 */
}

.search-container {
  position: relative; /* SearchBoxResult의 절대 위치 기준 */
  width: 150%;
}

.logo {
  font-size: 2em;
  font-weight: bold;
  color: #d87070;
  letter-spacing: 2px;
  text-transform: uppercase;
}

.auth-links {
  margin-top: 1em;
}

.auth-links a {
  color: #007bff;
  text-decoration: none;
}

.auth-links a:hover {
  text-decoration: underline;
}
</style>
