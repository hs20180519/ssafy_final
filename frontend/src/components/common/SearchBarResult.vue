<script setup>
import router from '@/router';
import { defineProps, onMounted, onUpdated, ref } from 'vue';

const props = defineProps({
    result: Object
})

const regions = ref();
const houses = ref();

onUpdated(() => {
    console.log(props.result);
    regions.value = props.result.regions;
    houses.value = props.result.houses;
    
})

onMounted(() => {
    console.log(props.result);
    regions.value = props.result.regions;
    houses.value = props.result.houses;
})

function goDetail(type, data) {
    console.log(data, type);
    router.push({
        name: "HouseDeal",
        query: {
            type: type,
            keyword: data
        }
    })
}
</script>

<template>
    <h2>지역</h2>
    <div v-for="region in regions" :key="region.dongCode" @click="goDetail('region', region.dongCode)">
        {{ region.sidoName }} {{ region.gugunName }} {{ region.dongName }}
    </div>

    <h2>매물</h2>
    <div v-for="house in houses" :key="house.aptSeq" @click="goDetail('house', house.aptSeq)">
        {{ house.aptNm }}: {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
    </div>
</template>

<style scoped>

</style>