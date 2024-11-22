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

    houseDealStore.setPgno(1);
    console.log("goDetail 호출", code, type, houseDealStore.pgno);
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
    <div class='search-box-result'>
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
.search-box-result {
  margin-top: 10px;
  background-color: white;
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  width: 100%;
  z-index: 10; /* auth-links보다 더 높은 레이어 */
  position: absolute; /* 겹치기 위해 절대 위치 설정 */
  top: 80%; /* SearchBox 바로 아래 */
  left: 0;
}
</style>