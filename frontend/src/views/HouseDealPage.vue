<script setup>
import HouseDealList from "@/components/housedeal/HouseDealList.vue";
import { ref, watch } from 'vue'
import { getHouseDealByKeyword, getCountHouseDealByKeyword } from '@/api/houseDeal';
import { onMounted } from 'vue';
import { onBeforeRouteUpdate, useRoute } from 'vue-router'
import { useHouseDealStore } from "@/stores/houseDealStore";
import MapResult from "@/components/common/MapResult.vue";
import HouseDealFilter from "@/components/housedeal/HouseDealFilter.vue";

const route = useRoute();
const houseDealStore = useHouseDealStore();

const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;

const type = route.query.type;
const keyword = route.query.keyword;

const clickedItem = ref({});

const result = ref([]);
const total = ref();

onMounted(() => {
    console.log("새로고침됨");
    getResultList(type, keyword, houseDealStore.pgno);
    getCountResult(type, keyword);
})

// query나 params가 변경되었을 때 실행(e.g. houseDealPage에서 재검색)
onBeforeRouteUpdate((to, from, next) => {
    console.log("route updated!", from.query, " to " , to.query)

    getResultList(to.query.type, to.query.keyword);
    getCountResult(to.query.type, to.query.keyword);

    next();
})

watch(
    () => houseDealStore.pgno,
    (newVal) => {
        console.log("pgno changed");
        getResultList(type, keyword, newVal);
    }
)

const getResultList = (type, keyword, pgno = houseDealStore.pgno) => {
    getHouseDealByKeyword(
        {
            type: type,
            keyword: keyword,
            pgSize: VITE_ARTICLE_LIST_SIZE,
            pgno: pgno
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

const getCountResult = (type, keyword) => {
    getCountHouseDealByKeyword(
        {
            type: type,
            keyword: keyword
        },
        ({ data }) => {
            console.log("result count: ", data);
            total.value = data;
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
    <div class="main-container">
        <HouseDealFilter class='filter-container'/>
        <div class="container">
            <div class="list-container">
            <HouseDealList :list="result" @onItemClick="onItemClick" :total="total"/>
            </div>
    
            <MapResult class="map-container" :clickedItem="clickedItem" />
        </div>
    </div>
</template>

<style scoped>

.filter-container {
    width: 30%;
}

.container {
  display: flex;
  width: 100%; /* 부모 컨테이너가 전체 너비를 차지 */
  height: 100vh; /* 화면 높이를 모두 사용 (선택 사항) */
}

.list-container {
  width: 30%;
}

.map-container {
  flex-grow: 1; 
  /* max-width: 500px;  */
}
</style>
