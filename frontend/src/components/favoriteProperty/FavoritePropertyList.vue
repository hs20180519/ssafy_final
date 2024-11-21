<template>
  <div class="favorite-property-list">
    <div class="property-cards-container">
      <FavoritePropertyItem
        v-for="(property, index) in favoriteProperties"
        :key="index"
        :property="property"
        @remove="removePropertyFromList"
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
      favoriteProperties: [],
    };
  },
  mounted() {
    this.fetchFavoriteProperties();
  },
  methods: {
    fetchFavoriteProperties() {
      getFavoriteProperties(
        (response) => {
          this.favoriteProperties = response.data;
        },
        (error) => {
          console.error("Error fetching favorite properties", error);
        }
      );
    },
    removePropertyFromList(propertyId) {
      this.favoriteProperties = this.favoriteProperties.filter(
        (property) => property.id !== propertyId
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
