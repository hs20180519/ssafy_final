<script setup>
import router from '@/router';
import { defineProps, onMounted, onUpdated, ref } from 'vue';

const props = defineProps({
    result: Object
})

const regions = ref();
const houses = ref();

onMounted(() => {
    console.log(props.result);
    regions.value = props.result.regions;
    houses.value = props.result.houses;
    
})

function goDetail(data) {
    console.log(data);
    router.push({
        name: "HouseDeal"
    })
}
</script>

<template>
    <h2>지역</h2>
    <div v-for="region in regions" :key="region.dongCode" @click="goDetail(region)">
        {{ region.sidoName }} {{ region.gugunName }} {{ region.dongName }}
    </div>

    <h2>매물</h2>
    <div v-for="house in houses" :key="house.aptSeq" @click="goDetail(house)">
        {{ house.aptNm }}: {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
    </div>
</template>

<style scoped>

</style>