<script setup>
import router from '@/router';
import { defineProps, defineEmits, watch, ref } from 'vue';
import { useHouseDealStore } from '@/stores/houseDealStore';
import NoResult from './NoResult.vue';

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
            <template v-if="regions.length === 0">
                <div>
                    <NoResult emoji="👀" message="검색 결과가 없습니다." />
                </div>
            </template>
            <template v-else>
                <div
                    v-for="region in regions"
                    :key="region.dongCode"
                    class="search-item"
                    @click="goDetail('region', region)"
                >
                    {{ region.sidoName }} {{ region.gugunName }} {{ region.dongName }}
                </div>
            </template>
        </div>

        <!-- 매물 결과 -->
        <div class="search-column">
            <h2>매물</h2>
            <template v-if="houses.length === 0">
                <div>
                    <NoResult emoji="👀" message="검색 결과가 없습니다." />
                </div>
            </template>
            <template v-else>
                <div
                    v-for="house in houses"
                    :key="house.aptSeq"
                    class="search-item"
                    @click="goDetail('house', house.aptSeq)"
                >
                    <p class="house-title">{{ house.aptNm }}</p>
                    <p class="house-location">{{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}</p>
                </div>
            </template>
        </div>

        <!-- 닫기 버튼 -->
        <button class="close-button" @click="$emit('closeSearchBox')">닫기</button>
    </div>
</template>


<style scoped>
.search-box-result {
    /* 기존 스타일 유지 */
    display: flex;
    margin-top: 10px;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    width: 100%;
    z-index: 30;
    position: absolute;
    top: 100%;
    left: 0;
    max-height: 300px;

    /* 추가 스타일 */
    padding: 20px; /* 내부 여백 추가 */
    box-sizing: border-box; /* 패딩을 포함한 너비 계산 */
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

.close-button {
    position: absolute; /* 위치 고정 */
    bottom: 15px; /* 아래쪽 여백 */
    right: 15px; /* 오른쪽 여백 */
    font-size: 0.8rem; /* 작은 크기 */
    color: rgba(0, 0, 0, 0.5); /* 흐릿한 글자 색 */
    background: none; /* 배경 제거 */
    border: none; /* 테두리 제거 */
    cursor: pointer; /* 클릭 가능 표시 */
    transition: color 0.2s ease-in-out, transform 0.2s ease-in-out; /* 부드러운 효과 */
}

.close-button:hover {
    color: rgba(0, 0, 0, 0.8); /* hover 시 더 진한 색상 */
    /* transform: scale(1.1); hover 시 살짝 확대 */
}



</style>
