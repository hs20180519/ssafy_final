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
        <div class="container">
            <div class="list-container">
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
    </div>
</template>

<style scoped>

.container {
    display: flex;
    width: 100%;
    height: 100vh; /* 화면 전체 높이 */
}

.map-container {
    flex-grow: 1; /* 남은 공간 차지 */
    position: relative; /* HouseDealDetail의 위치 기준 */
    background-color: #f9f9f9;
}

.list-container {
    width: 40vh;
}

.detail-overlay {
    position: absolute;
    top: 0;
    height: 100%;
    width: 300px; /* HouseDealDetail의 고정 너비 */
    z-index: 10; /* MapResult 위에 표시 */
}

.sidebar {
    width: 100%; /* detail-overlay의 너비에 맞게 */
    height: 100%; /* 부모 높이를 모두 차지 */
    background-color: rgb(255, 255, 255);
    box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 6px;
    border-left: 1px solid rgb(204, 204, 204);
    z-index: 10;
}

</style>
