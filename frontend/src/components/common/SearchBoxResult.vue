<script setup>
import router from '@/router';
import { defineProps, defineEmits, onMounted, onUpdated, ref } from 'vue';
import { useHouseDealStore } from '@/stores/houseDealStore';

const houseDealStore = useHouseDealStore();

const props = defineProps({
    result: Object
})

const emit = defineEmits(['onResultClicked']);

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
    let code = data;
    emit('onResultClicked');
    if(type == 'region') {
        code = data.dongCode;
        houseDealStore.setRegion(data.sidoName, data.gugunName, data.dongName, data.dongCode)
    }

    console.log("goDetail 호출", code, type);
    router.push({
        name: "HouseDeal",
        query: {
            type: type,
            keyword: code
        }
    })
    
}

</script>

<template>
    <h2>지역</h2>
    <div v-for="region in regions" :key="region.dongCode" @click="goDetail('region', region)">
        {{ region.sidoName }} {{ region.gugunName }} {{ region.dongName }}
    </div>

    <h2>매물</h2>
    <div v-for="house in houses" :key="house.aptSeq" @click="goDetail('house', house.aptSeq)">
        {{ house.aptNm }}: {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
    </div>
</template>


<style scoped>

</style>