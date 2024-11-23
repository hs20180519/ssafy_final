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
  </div>
</template>

<script>
import {ref, onMounted} from "vue";
import SearchBox from "@/components/common/SearchBox.vue";
import SearchBoxResult from "@/components/common/SearchBoxResult.vue";
import {searchKeyword} from "@/api/search";
import {useHouseDealStore} from "@/stores/houseDealStore";

export default {
  name: "MainContent",
  components: {
    SearchBox,
    SearchBoxResult,
  },
  setup() {
    const houseDealStore = useHouseDealStore();
    const isLoaded = ref(false);
    const result = ref();

    const onSearch = () => {
      searchKeyword(
        {
          sidoCode: "",
          gugunCode: "",
          dongCode: "",
          keyword: houseDealStore.keyword,
        },
        ({data}) => {
          result.value = data;
          isLoaded.value = true;
        },
        (error) => {
          console.error(error);
        }
      );
    };

    onMounted(() => {
      houseDealStore.setKeyword("");
    });

    return {
      isLoaded,
      result,
      onSearch,
    };
  },
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
  height: 100%; /* 화면에 가득 차도록 설정 */
  width: 100%; /* 가로 너비 100% */
  background-color: #f8f9fa; /* 메인 배경색 */
  color: #333;
}

/* 제목 스타일 */
.main-title {
  font-size: 3.5rem; /* 더 큰 크기 */
  font-weight: 800; /* 두껍게 */
  margin-bottom: 0.1rem;
  background: linear-gradient(135deg, #70abd8, #ffcc00); /* 그라디언트 색상 */
  -webkit-background-clip: text; /* 텍스트에 배경을 클리핑 */
  color: transparent; /* 기본 텍스트 색상은 투명 */
  opacity: 0; /* 초기 상태: 숨김 */
  animation: fadeInSlideUp 1.5s ease-out forwards;
}

.sub-title {
  font-size: 1.2rem; /* 부제목 크기 확대 */
  color: #666;
  margin-bottom: 2rem;
  opacity: 0; /* 초기 상태: 숨김 */
  animation: fadeInSlideUp 2s ease-out forwards; /* 약간 딜레이 */
}

.search-container {
  position: relative;
  width: 100%;
  max-width: 600px;
  //padding: 1rem;
  //background-color: #ffffff;
  border-radius: 8px;
  //box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  opacity: 0; /* 초기 상태: 숨김 */
  animation: fadeInSlideUp 2.5s ease-out forwards; /* 더 늦게 등장 */
}

.search-box-result {
  margin-top: 1rem;
}

/* 애니메이션 효과 */
@keyframes fadeInSlideUp {
  0% {
    opacity: 0;
    transform: translateY(30px); /* 아래에서 위로 이동 */
  }
  100% {
    opacity: 1;
    transform: translateY(0); /* 제자리 */
  }
}

/* 개별 요소에 애니메이션 적용 */
.animated-title {
  animation-delay: 0s; /* 즉시 */
}

.animated-subtitle {
  animation-delay: 0.5s; /* 0.5초 딜레이 */
}

.animated-search {
  animation-delay: 1s; /* 1초 딜레이 */
}
</style>
