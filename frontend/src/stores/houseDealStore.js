import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useHouseDealStore = defineStore('housedeal', () => {
    const keyword = ref("") // 검색어
    const sidoName = ref("") // 시도
    const gugunName = ref("")
    const dongName = ref("")
    const dongCode = ref("")

    const pgno = ref(1);
    const sort = ref("date-new");

    const selectedDeparture = ref();

    const isGraphModalVisible = ref(false);

    const searchOption = computed(() => {
        return {
            keyword: keyword.value,
            sidoName: sidoName.value,
            gugunName: gugunName.value,
            dongName: dongName.value,
            dongCode: dongCode.value
        }
    })

    const setKeyword = (input) => {
        keyword.value = input;
        console.log("store keyword updated: ", keyword.value)
    }

    const setRegion = (sido, gugun, dong, code) => {
        sidoName.value = sido;
        gugunName.value = gugun;
        dongName.value = dong;
        dongCode.value = code;
    }

    const setPgno = (num) => {
        pgno.value = num;
    }

    const setSelectedDeparture = (newObj) => {
        selectedDeparture.value = newObj;
    }

    const setSort = (newVal) => {
        console.log(newVal);
        sort.value = newVal;
    }

    const setIsGraphModalVisible = (newVal) => {
        isGraphModalVisible.value = newVal;
    }

    return {
        keyword,
        sidoName,
        gugunName,
        dongName,
        searchOption,
        pgno,
        selectedDeparture,
        sort,
        isGraphModalVisible,
        setKeyword,
        setRegion,
        setPgno,
        setSelectedDeparture,
        setSort,
        setIsGraphModalVisible
    }
})