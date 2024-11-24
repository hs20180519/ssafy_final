<script setup>
import HouseDealList from "@/components/housedeal/HouseDealList.vue";
import { ref, watch } from 'vue'
import { getHouseDealByKeyword, getCountHouseDealByKeyword } from '@/api/houseDeal';
import { onMounted } from 'vue';
import { onBeforeRouteUpdate, useRoute } from 'vue-router'
import { useHouseDealStore } from "@/stores/houseDealStore";
import MapResult from "@/components/common/MapResult.vue";
import HouseDealFilter from "@/components/housedeal/HouseDealFilter.vue";
import HouseDealDetail from "@/components/housedeal/HouseDealDetail.vue";

const route = useRoute();
const houseDealStore = useHouseDealStore();

const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;

const type = ref(route.query.type);
const keyword = ref(route.query.keyword);

const clickedItem = ref({});

const result = ref([]);
const total = ref();

// detail
const isDetailVisible = ref(false);

onMounted(() => {
    getResultList(type.value, keyword.value, houseDealStore.pgno);
    getCountResult(type.value, keyword.value);
})

// query나 params가 변경되었을 때 실행(e.g. houseDealPage에서 재검색)
onBeforeRouteUpdate((to, from, next) => {
    console.log("route updated!", from.query, " to " , to.query)

    getResultList(to.query.type, to.query.keyword);
    getCountResult(to.query.type, to.query.keyword);

    type.value = to.query.type;
    keyword.value = to.query.keyword;
    isDetailVisible.value = false;

    next();
})

watch(
    () => houseDealStore.pgno,
    (newVal) => {
        console.log("pgno changed,", type, keyword);
        getResultList(type.value, keyword.value, newVal);
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
    isDetailVisible.value = true;
}

const closeDetail = () => {
    isDetailVisible.value = false;
}

</script>

<template>
    <div class="main-container">
        <HouseDealFilter class='filter-container'/>
        <div class="list-overlay">
            <HouseDealList :list="result" @onItemClick="onItemClick" :total="total"/>
        </div>
        <div class="map-container">
            <MapResult :clickedItem="clickedItem" />
            <div class="detail-overlay">
                <HouseDealDetail
                    class="sidebar"
                    :isVisible="isDetailVisible"
                    @closeDetail="closeDetail"
                    :clickedItem='clickedItem'
                />
            </div>
        </div>
    </div>
</template>

<style scoped>

.main-container {
    position: relative; /* 자식 요소의 위치 기준 */
    width: 100%;
    height: 100vh;
    overflow: hidden;
}

.filter-container {
    position: relative;
    z-index: 15;
    margin-left: 50px;
    width: 50%; /* 너비 조정 */
}

.list-overlay {
    position: absolute;
    top: 130px; /* Filter 높이 아래로 배치 */
    margin-left: 50px; /* Filter 왼쪽 위치와 맞춤 */
    left: 20px;
    width: 40vh; /* 고정된 너비 */
    height: calc(100vh - 150px); /* Filter 높이를 뺀 공간 사용 */
    background-color: rgba(255, 255, 255, 0.9); /* 투명한 배경 */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    z-index: 10;
    overflow-y: auto; /* 스크롤 추가 */
    /* border: 1px solid rgb(204, 204, 204); */
    border-radius: 8px;
}

.map-container {
    flex-grow: 1;
    position: relative;
    height: 100%;
}

.map-container > MapResult {
    width: 100%;
    height: 100%;
}

.detail-overlay {
    position: absolute;
    top: 30px; /* Filter 높이 아래로 배치 */
    left: calc(20px + 40vh + 55px); /* HouseDealList의 너비(40vh)와 간격(10px) 만큼 오른쪽으로 배치 */
    width: 300px;
    height: calc(100vh - 150px); /* Filter 높이를 뺀 공간 사용 */
    z-index: 20;
    overflow-y: auto; /* 스크롤 추가 */
    /* border-left: 1px solid rgb(204, 204, 204); */
    border-radius: 8px;
}

.sidebar {
    width: 100%;
    height: 100%;
}

</style>
