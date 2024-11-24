<template>
  <div class="main-content">
    <!-- The Next Home 강조 텍스트 -->
    <h1 class="main-title animated-title">The Next Home</h1>
    <p class="sub-title animated-subtitle">당신의 다음 집을 찾아보세요</p>

    <!-- 검색 영역 -->
    <div class="search-container animated-search">
      <SearchBox @onSearch="onSearch"/>
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
import {ref} from "vue";
import SearchBox from "@/components/common/SearchBox.vue";

export default {
  name: "MainContent",
  components: {
    SearchBox,
  },
};
</script>

<script setup>
import {defineEmits, onMounted} from "vue";
import {searchKeyword} from "@/api/search";
import {useHouseDealStore} from "@/stores/houseDealStore";
import {useAuthStore} from "@/stores/authStore";
import SearchBoxResult from "@/components/common/SearchBoxResult.vue";

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
    {
      sidoCode: "",
      gugunCode: "",
      dongCode: "",
      keyword: houseDealStore.keyword
    },
    ({data}) => {
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
  min-height: 100vh;
  height: 100%;
  width: 100%;
  background-color: #f8f9fa;
  color: #333;
}

/* 제목 스타일 */
.main-title {
  font-size: 3.5rem;
  font-weight: 800;
  margin-bottom: 0.1rem;
  background: linear-gradient(135deg, #70abd8, #ffcc00);
  -webkit-background-clip: text;
  color: transparent;
  opacity: 0;
  animation: fadeInSlideUp 1.5s ease-out forwards;
  margin-top: 0;
}

.sub-title {
  margin-top: 0;
  font-size: 1.2rem;
  color: #666;
  margin-bottom: 1rem;
  opacity: 0;
  animation: fadeInSlideUp 2s ease-out forwards;
}

.search-container {
  position: relative;
  width: 100%;
  max-width: 600px;
  border-radius: 8px;
  opacity: 0;
  animation: fadeInSlideUp 2.5s ease-out forwards;
}

.search-box-result {
  margin-top: 1rem;
}

/* 애니메이션 효과 */
@keyframes fadeInSlideUp {
  0% {
    opacity: 0;
    transform: translateY(30px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 개별 요소에 애니메이션 적용 */
.animated-title {
  animation-delay: 0s;
}

.animated-subtitle {
  animation-delay: 0.5s;
}

.animated-search {
  animation-delay: 1s;
}

.auth-links {
  margin-top: 1em;
  opacity: 0;
  animation: fadeInSlideUp 2.5s ease-out forwards;
  animation-delay: 1.5s; /* 딜레이 주기 */
}

.auth-links a {
  color: #70abd8; /* 링크 색상 */
  text-decoration: none;
  font-size: 1rem;
  font-weight: 600;
  opacity: 0;
  animation: fadeInSlideUp 2.5s ease-out forwards; /* 애니메이션 추가 */
  animation-delay: 1.5s; /* 딜레이 주기 */
}

.auth-links a:hover {
  color: #0056b3;
  text-decoration: underline;
}
</style>

