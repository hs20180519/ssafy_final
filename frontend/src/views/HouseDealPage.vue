<script setup>
// import SideNavbar from '@/components/layout/SideNavbar.vue';
import HouseDealList from "@/components/housedeal/HouseDealList.vue";
import { ref } from 'vue'
import { getHomeDealByKeyword } from '@/api/houseDeal';
import { onMounted } from 'vue';
import { useRoute } from 'vue-router'
import MapResult from "@/components/common/MapResult.vue";
import HouseDealFilter from "@/components/housedeal/HouseDealFilter.vue";

const route = useRoute();

const type = route.query.type;
const keyword = route.query.keyword;

const searchOption = ref({
    type: type,
    keyword: keyword
})

const result = ref([]);

onMounted(() => {
    getResultList(type, keyword);
})

const getResultList = (type, keyword) => {
    getHomeDealByKeyword(
        {
            type: type,
            keyword: keyword
        },
        ({ data }) => {
            result.value = data;
        },
        (error) => {
            console.log(error);
        }
    )
}


// const getResultList = async (type, keyword) => {
//     try {
//         const {data} = await getHomeDealByKeyword({ type, keyword });
//         console.log(data);
//         result.value = data;
//     } catch (error) {
//         console.error("데이터 로드 실패:", error);
//     }
// };



</script>

<template>
    <HouseDealFilter :searchOption='searchOption'/>
    <HouseDealList :list="result" />
    <MapResult />
</template>

<style scoped>

</style>