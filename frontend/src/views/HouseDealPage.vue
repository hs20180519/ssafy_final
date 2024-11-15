<script setup>
// import SideNavbar from '@/components/layout/SideNavbar.vue';
import HouseDealList from "@/components/housedeal/HouseDealList.vue";
import { ref, watch } from 'vue'
import { getHomeDealByKeyword } from '@/api/houseDeal';
import { onMounted } from 'vue';
import { useRoute } from 'vue-router'

const route = useRoute();

const type = route.query.type;
const keyword = route.query.keyword;

const result = ref([]);

watch(result, (newVal) => {
    
    result.value = newVal;
    console.log(newVal)
}, {
    deep: true,
    immediate: true
})

onMounted(() => {
    console.log(type);
    console.log(keyword);
    getResultList(type, keyword);
    console.log("왜안나와?????", result.value)
})

const getResultList = (type, keyword) => {
    getHomeDealByKeyword(
        {
            type: type,
            keyword: keyword
        },
        ({ data }) => {
            result.value = data;
            console.log(data);

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
    HouseDealPage
    <HouseDealList :list='result' />
</template>

<style scoped>

</style>