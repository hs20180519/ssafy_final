<template>
  <div class="favorite-property-card">
    <!-- 이미지가 없는 경우를 대비한 기본 이미지 설정 -->
    <img
      :src="property.image || '/images/default-property-image.jpg'"
      alt="Property Image"
      class="property-image"
    />
    <div class="property-details">
      <h3>{{ property.aptNm }}</h3>
      <p>{{ property.roadAddress }}</p>
      <p>최근 거래 금액 : {{ property.dealAmount }} (만원)</p>
      <p>
        최근 거래일 : {{ property.dealYear }}년 {{ property.dealMonth }}월
        {{ property.dealDay }}일
      </p>

      <div
        v-if="property.distances && property.distances.length > 0"
        class="distances-container"
      >
        <!-- 거리 정보 제목과 바로가기 링크 추가 -->
        <div class="distances-header">
          <h3>거리 정보</h3>
          <router-link :to="{ name: 'MyPage', query: { tab: 'editAddress' } }">
            주소지 관리
          </router-link>
        </div>

        <div class="distances-list">
          <ul>
            <li v-for="(distance, index) in property.distances" :key="index">
              {{ distance }}
            </li>
          </ul>
        </div>
      </div>

      <!-- 삭제 버튼을 오른쪽에 배치 -->
      <button @click="removeFavorite" class="remove-button">삭제</button>
    </div>
  </div>
</template>

<script>
import { deleteFavoriteProperty } from "@/api/favoriteProperty.js";

export default {
  name: "FavoritePropertyItem",
  props: {
    property: Object, // Property 정보를 받아오는 prop
  },
  methods: {
    removeFavorite() {
      // 관심 매물 삭제 API 호출
      deleteFavoriteProperty(
        this.property.id,
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
  display: flex;
  flex-direction: column;
  /* align-items: center; */
}

.property-details h3 {
  font-size: 1.2rem;
  margin: 0;
}

.property-details p {
  font-size: 1rem;
  color: #777;
}

.distances-container {
  margin-top: 10px;
}

.distances-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.distances-list {
  padding-left: 20px;
  font-size: 1rem;
  color: #555;
}

.address-management-link {
  color: #d87070;
  text-decoration: none;
  cursor: pointer;
}

.address-management-link:hover {
  background-color: beige;
}

/* 삭제 버튼 스타일링 */
.remove-button {
  margin-top: 10px;
  padding: 5px 10px;
  background-color: #d87070;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  align-self: flex-end; /* 버튼을 오른쪽에 정렬 */
}

.remove-button:hover {
  background-color: #c75a5a;
}
</style>
