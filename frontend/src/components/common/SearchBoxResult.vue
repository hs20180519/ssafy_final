<script setup>
import router from '@/router';
import { defineProps, defineEmits, watch, ref } from 'vue';
import { useHouseDealStore } from '@/stores/houseDealStore';

const houseDealStore = useHouseDealStore();

const props = defineProps({
    result: Object
})

const emit = defineEmits(['onResultClicked']);

const regions = ref();
const houses = ref();

watch(
    () => props.result,
    ((newVal) => {
        regions.value = newVal.regions;
        houses.value = newVal.houses;
        console.log("변경 감지: ", houses.value);
    }),
    {
        immediate: true
    }
)


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
    <div>
        <h2>지역</h2>
        <div v-for="region in regions" :key="region.dongCode" @click="goDetail('region', region)">
            {{ region.sidoName }} {{ region.gugunName }} {{ region.dongName }}
        </div>

        <h2>매물</h2>
        <div v-for="house in houses" :key="house.aptSeq" @click="goDetail('house', house.aptSeq)">
            {{ house.aptNm }}: {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
        </div>
    </div>
</template>


<style scoped>

</style>