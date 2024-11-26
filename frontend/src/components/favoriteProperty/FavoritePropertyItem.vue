<template>
  <div class="favorite-property-card">
    <img
      :src="property.image || '/images/default-property-image.jpg'"
      alt="Property Image"
      class="property-image"
    />
    <div class="property-details">
      <div class="property-info">
        <h3>{{ property.aptNm }}</h3>
        <p>{{ property.roadAddress }}</p>
        <p>최근 거래 금액 : {{ property.dealAmount }} (만원)</p>
        <p>
          최근 거래일 : {{ property.dealYear }}년 {{ property.dealMonth }}월
          {{ property.dealDay }}일
        </p>
      </div>

      <div class="distances-container">
        <!-- 거리 정보 제목과 바로가기 링크 추가 -->
        <div class="distances-header">
          <h3>거리 정보</h3>
          <router-link :to="{ name: 'MyPage', query: { tab: 'editAddress' } }">
            주소지 관리
          </router-link>
        </div>

        <!-- 거리 정보 목록 -->
        <div class="distances-list">
          <ul>
            <li
              v-for="(distance, index) in property.distances"
              :key="index"
              class="distance-item"
            >
              <!-- 이름은 강조, 거리 정보는 동적 애니메이션 추가 -->
              <div class="distance-address animated-fade-in">
                {{ distance.addressName }}
              </div>
              <div
                class="distance-value animated-slide-in"
                :style="{ animationDelay: `${index * 0.3}s` }"
              >
                {{ distance.distance }}
              </div>
            </li>
          </ul>
        </div>
      </div>

      <!-- 삭제 버튼 -->
      <button @click="removeFavorite" class="remove-button">
        <font-awesome-icon :icon="['fas', 'trash']"/>
      </button>
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
      deleteFavoriteProperty(
        this.property.aptSeq,
        (response) => {
          // 삭제 후 부모 컴포넌트에 이벤트 전달
          this.$emit("remove", this.property.aptSeq);
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
}

.property-info {
  background-color: #ecebeb; /* 첫 번째 섹션 배경색 */
  padding: 15px;
  border-radius: 5px;
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
  margin-top: 15px;
  background-color: #ecebeb; /* 두 번째 섹션 배경색 */
  padding: 15px;
  border-radius: 5px;
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

/* 거리 아이템 스타일 */
.distance-item {
  margin-bottom: 10px;
}

.distance-address {
  font-size: 1.2rem;
  font-weight: bold;
  color: #333;
  text-align: left;
  margin-bottom: 5px;
}

.distance-value {
  font-size: 1rem;
  color: #70abd8;
  text-align: left;
  font-weight: 600;
}

/* 삭제 버튼 스타일링 */
.remove-button {
  margin-top: 10px;
  padding: 5px 10px;
  background-color: #fbe9e7;
  color: #d84315;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  align-self: flex-end;
}

.remove-button:hover {
  background-color: #ffccbc;
}

/* 애니메이션 효과 */
@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

@keyframes slideIn {
  0% {
    transform: translateX(-30px);
    opacity: 0;
  }
  100% {
    transform: translateX(0);
    opacity: 1;
  }
}

/* 애니메이션 클래스 */
.animated-fade-in {
  animation: fadeIn 1s ease-in-out;
}

.animated-slide-in {
  animation: slideIn 1s ease-in-out forwards;
}
</style>
