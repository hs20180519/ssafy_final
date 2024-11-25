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
    <div class="search-box-result">
        <!-- 지역 결과 -->
        <div class="search-column">
            <h2>지역</h2>
            <div
                v-for="region in regions"
                :key="region.dongCode"
                class="search-item"
                @click="goDetail('region', region)"
            >
                {{ region.sidoName }} {{ region.gugunName }} {{ region.dongName }}
            </div>
        </div>

        <!-- 매물 결과 -->
        <div class="search-column">
            <h2>매물</h2>
            <div
                v-for="house in houses"
                :key="house.aptSeq"
                class="search-item"
                @click="goDetail('house', house.aptSeq)"
            >
                <p class="house-title">{{ house.aptNm }}</p>
                <p class="house-location">{{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}</p>
            </div>
        </div>
    </div>
</template>

<style scoped>
.search-box-result {
    display: flex; /* 두 칸으로 나누기 위해 flex 사용 */
    margin-top: 10px;
    background-color: white;
    padding: 0; /* 패딩 제거 */
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    width: 100%; /* 검색창과 동일한 너비 */
    z-index: 30; /* HouseDealDetail보다 높은 z-index 값 */
    position: absolute;
    top: 100%; /* 검색창 바로 아래 */
    left: 0;

    /* 전체 높이 제한 */
    max-height: 300px; /* 검색 결과 전체 높이 고정 */
}

.search-column {
    flex: 1; /* 두 칸을 균등 분배 */
    display: flex;
    flex-direction: column; /* 수직 정렬 */
    overflow-y: auto; /* 세로 스크롤 활성화 */
    max-height: 300px; /* 개별 칸 높이 고정 */
    position: relative; /* 상대적 위치 */
}

.search-column + .search-column {
    border-left: 1px solid rgba(200, 200, 200, 0.5); /* 두 칸 사이 구분선 */
    padding-left: 15px; /* 선과 내용 간 여백 */
}

h2 {
    font-size: 1.2rem; /* 제목 크기 */
    font-weight: bold; /* 굵게 */
    margin: 0; /* 여백 제거 */
    padding: 10px; /* 여백 추가 */
    color: #222; /* 어두운 색상으로 강조 */
    border-bottom: 1px solid #ddd; /* 제목 하단에 선 추가 */
    position: sticky; /* 고정 위치 설정 */
    top: 0; /* 부모의 상단에 고정 */
    background-color: white; /* 배경 흰색 */
    z-index: 1; /* 스크롤 시 내용 위에 나타나도록 설정 */
}

.search-item {
    padding: 10px 12px;
    margin-bottom: 8px;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.2s ease-in-out;
    background-color: #fff; /* 기본 배경색 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05); /* 미세한 그림자 */
}

.search-item:hover {
    background-color: #f4f4f4; /* hover 효과 */
}

.house-title {
    font-size: 1rem;
    font-weight: bold;
    color: #333;
    margin: 0;
}

.house-location {
    font-size: 0.85rem;
    color: #666;
    margin: 4px 0 0;
}
</style>
