<template>
  <div class="favorite-property-list">
    <div v-if="favoriteProperties.length === 0" class="no-properties-message">
      <h3>즐겨찾기 매물이 없습니다.</h3>
      <p>
        <router-link :to="{ name: 'Home' }" class="link">
          매물 찾기
        </router-link>
        를 통해 즐겨찾기를 등록할 수 있어요
      </p>
    </div>
    <div v-else class="property-cards-container">
      <FavoritePropertyItem
        v-for="(property, index) in favoriteProperties"
        :key="property.aptSeq"
        :property="property"
        @remove="removeFromList"
      />
    </div>
  </div>
</template>

<script>
import { getFavoriteProperties } from "@/api/favoriteProperty.js";
import FavoritePropertyItem from "@/components/favoriteProperty/FavoritePropertyItem.vue";

export default {
  name: "FavoritePropertyList",
  components: {
    FavoritePropertyItem,
  },
  data() {
    return {
      favoriteProperties: [], // 관심 매물 목록
    };
  },
  mounted() {
    this.fetchFavoriteProperties(); // 컴포넌트가 마운트되면 데이터 로드
  },
  methods: {
    fetchFavoriteProperties() {
      getFavoriteProperties(
        (response) => {
          console.log(response.data);
          this.favoriteProperties = response.data; // 매물 정보 로드
        },
        (error) => {
          console.error("Error fetching favorite properties", error);
        }
      );
    },
    removeFromList(aptSeq) {
      // 관심 매물 목록에서 삭제된 항목을 제거
      this.favoriteProperties = this.favoriteProperties.filter(
        (property) => property.aptSeq !== aptSeq
      );
    },
  },
};
</script>

<style scoped>
.favorite-property-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding: 20px;
}

.property-cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: flex-start;
}

.link {
  color: #007bff;
  text-decoration: underline;
  cursor: pointer;
}

.link:hover {
  color: #0056b3;
}
</style>
