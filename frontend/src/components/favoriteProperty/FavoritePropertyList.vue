<template>
  <div class="favorite-property-list">
    <div class="property-cards-container">
      <FavoritePropertyItem
        v-for="(property, index) in favoriteProperties"
        :key="property.id"
        :property="property"
        @remove="removeFromList"
      />
    </div>
  </div>
</template>

<script>
import {getFavoriteProperties} from "@/api/favoriteProperty.js";
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
          this.favoriteProperties = response.data; // 매물 정보 로드
          console.log(response.data);
        },
        (error) => {
          console.error("Error fetching favorite properties", error);
        }
      );
    },
    removeFromList(favoriteId) {
      // 관심 매물 목록에서 삭제된 항목을 제거
      this.favoriteProperties = this.favoriteProperties.filter(
        (property) => property.id !== favoriteId
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
</style>
