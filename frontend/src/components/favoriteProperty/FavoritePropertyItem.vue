<template>
  <div class="favorite-property-card">
    <!-- 이미지가 없는 경우를 대비한 기본 이미지 설정 -->
    <img :src="property.image || '/images/default-property-image.jpg'" alt="Property Image" class="property-image"/>
    <div class="property-details">
      <h3>{{ property.aptNm }}</h3>
      <p>{{ property.roadAddress }}</p>
      <p>최근 거래 금액 : {{ property.dealAmount }} (만원)</p>
      <p>최근 거래일 : {{ property.dealYear }}년 {{ property.dealMonth }}월 {{ property.dealDay }}일</p>
      <button @click="removeFavorite">Remove</button>
    </div>
  </div>
</template>

<script>
import {deleteFavoriteProperty} from "@/api/favoriteProperty.js";

export default {
  name: "FavoritePropertyItem",
  props: {
    property: Object, // Property 정보를 받아오는 prop
  },
  methods: {
    removeFavorite() {
      // 관심 매물 삭제 API 호출
      deleteFavoriteProperty(this.property.id,
        (response) => {
          // 삭제 후 부모 컴포넌트에 이벤트 전달
          this.$emit("remove", this.property.id);
        },
        (error) => {
          console.error("Error removing property", error);
        }
      );
    },
  },
};
</script>

<style scoped>
.favorite-property-card {
  width: 300px;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
}

.property-image {
  width: 100%;
  height: 180px;
  object-fit: cover;
  border-radius: 4px;
}

.property-details {
  margin-top: 10px;
}

.property-details h3 {
  font-size: 1.2rem;
  margin: 0;
}

.property-details p {
  font-size: 1rem;
  color: #777;
}

.property-details button {
  margin-top: 10px;
  padding: 5px 10px;
  background-color: #d87070;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.property-details button:hover {
  background-color: #c75a5a;
}
</style>
