<script setup>
import HouseDealList from "@/components/housedeal/HouseDealList.vue";
import { ref } from 'vue'
import { getHomeDealByKeyword } from '@/api/houseDeal';
import { onMounted } from 'vue';
import { onBeforeRouteUpdate, useRoute } from 'vue-router'
import MapResult from "@/components/common/MapResult.vue";
import HouseDealFilter from "@/components/housedeal/HouseDealFilter.vue";

const route = useRoute();

const type = route.query.type;
const keyword = route.query.keyword;

const clickedItem = ref({});


const result = ref([]);

onMounted(() => {
    getResultList(type, keyword);
})

// query나 params가 변경되었을 때 실행(e.g. houseDealPage에서 재검색)
onBeforeRouteUpdate((to, from, next) => {
    console.log("route updated!", from.query, " to " , to.query)

    getResultList(to.query.type, to.query.keyword);

    next();
})

const getResultList = (type, keyword) => {
    getHomeDealByKeyword(
        {
            type: type,
            keyword: keyword
        },
        ({ data }) => {
            console.log("getResultList: ", data);
            result.value = data;
        },
        (error) => {
            console.log(error);
        }
    )
}

const onItemClick = (item) => {
    clickedItem.value = item
}

</script>

<template>
    <HouseDealFilter />
    <div class="container">
        <div class="list-container">
        <HouseDealList :list="result" @onItemClick="onItemClick" />
        </div>

        <div class="map-container">
        <MapResult :clickedItem="clickedItem" />
        </div>
    </div>
</template>

<style scoped>
.container {
  display: flex; 
  gap: 20px; 
}

.list-container {
  flex: 1; 
}

.map-container {
  flex: 1; 
  /* max-width: 500px;  */
}
</style>
