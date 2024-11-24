import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useHouseDealStore = defineStore('housedeal', () => {
    const keyword = ref("") // 검색어
    const sidoName = ref("") // 시도
    const gugunName = ref("")
    const dongName = ref("")
    const dongCode = ref("")

    const pgno = ref(1);

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

    return {
        keyword,
        sidoName,
        gugunName,
        dongName,
        searchOption,
        pgno,
        setKeyword,
        setRegion,
        setPgno
    }
})