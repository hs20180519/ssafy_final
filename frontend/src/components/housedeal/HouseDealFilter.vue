<script setup>
import { onMounted, ref } from 'vue'
import SearchBox from "@/components/common/SearchBox.vue";
import VSelect from "@/components/common/VSelect.vue";
import { getFilter, searchKeyword } from "@/api/search";
import { useHouseDealStore } from '@/stores/houseDealStore';
import SearchBoxResult from '../common/SearchBoxResult.vue';

const props = defineProps({
    searchOption: Object
})

const houseDealStore = useHouseDealStore();

const sidoOptions = ref([]);
const gugunOptions = ref([]);
const dongOptions = ref([]);

const selectedSido = ref("");
const selectedGugun = ref("");
const selectedDong = ref("");

const isLoaded = ref(false);
const result = ref();

onMounted(() => {

    // 지역을 선택한 값이 있으면 해당 값으로 초기화
    getSidoFilter("").then(() => {
        if(houseDealStore.searchOption.dongCode) {
            const { dongCode } = houseDealStore.searchOption;
            selectedSido.value = dongCode.slice(0,2) + "00000000";
            selectedGugun.value = dongCode.slice(0,5) + "00000";
            selectedDong.value = dongCode;

            getGugunFilter(selectedSido.value).then(() => {
                getDongFilter(selectedGugun.value);
            })
        }
    })

    console.log(props.searchOption)
})

const onSearch = () => {
    console.log("onSearch")
    searchKeyword(
        {
            sidoCode: selectedSido.value.slice(0,2),
            gugunCode: selectedGugun.value.slice(0,5),
            dongCode: selectedDong.value.slice(5, 10),
            keyword: houseDealStore.keyword
        },
        ({ data }) => {
            result.value = {
                houses: data.houses,
                regions: data.regions
            };
            isLoaded.value = true;
        },
        (error) => {
            console.log(error)
        }
    )
}

const onSelectSido = (code) => {
    console.log("시도선택: ", code);
    selectedSido.value = code;
    getGugunFilter(code);
}

const onSelectGugun = (code) => {
    console.log("구군선택: ", code)
    selectedGugun.value = code;
    getDongFilter(code);
}

const onSelectDong = (code) => {
    selectedDong.value = code;
    console.log("동선택: ", code)
}

// then() 사용하기 위해 promise 반환
const getSidoFilter = (code) => {
    return new Promise((resolve, reject) => {
        getFilter(
            {
                param: code
            },
                ({ data }) => {
                console.log(data);
                sidoOptions.value = data.map(({ dongCode, sidoName }) => ({ value: dongCode, label: sidoName}));
                sidoOptions.value.unshift({ value: "", label: "전체" })
                resolve();
            },
            (error) => {
                    console.log(error);
                    reject(error);
            }
        );
    });
}

const getGugunFilter = (code) => {
    console.log("getGugunFilter", code);
    return new Promise((resolve, reject) => {
        getFilter(
            {
                param: code.slice(0, 2)
            },
            ({ data }) => {
                gugunOptions.value = data
                    .filter(item => item.gugunName !== null)
                    .map(({ dongCode, gugunName }) => ({ value: dongCode, label: gugunName }));
                gugunOptions.value.unshift({ value: "", label: "전체" })
                console.log(data);
                resolve();
            },
            (error) => {
                console.log(error);
                reject(error);
            }
        )

    })
}

const getDongFilter = (code) => {
    getFilter(
        {
            param: code.slice(0, 5)
        },
        ({ data }) => {
            dongOptions.value = data
                .filter(item => item.dongName !== null)
                .map(({ dongCode, dongName }) => ({ value: dongCode, label: dongName }));
            dongOptions.value.unshift({ value: "", label: "전체" });
            console.log(data);
        }
    )
}

</script>

<template>
    <div class='housedeal-filter'>
        <SearchBox @onSearch='onSearch'/>
        <VSelect :selectOption=sidoOptions :selectedOption='selectedSido' @onKeySelect='onSelectSido'/>
        <VSelect :selectOption=gugunOptions :selectedOption='selectedGugun' @onKeySelect='onSelectGugun' />
        <VSelect :selectOption=dongOptions :selectedOption='selectedDong' @onKeySelect='onSelectDong' />
        <SearchBoxResult v-if="isLoaded" :result="result" @onResultClicked="isLoaded = false" class='search-box-result'/>
    </div>
</template>

<style scoped>
.housedeal-filter {
    margin-left: 50px;
    position: relative; 
    width: 50%;
}

</style>