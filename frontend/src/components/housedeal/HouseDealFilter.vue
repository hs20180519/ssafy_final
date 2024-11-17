<script setup>
import { onMounted, ref } from 'vue'
import SearchBox from "@/components/common/SearchBox.vue";
import VSelect from "@/components/common/VSelect.vue";
import { getFilter, searchKeyword } from "@/api/search"
import { useHouseDealStore } from '@/stores/houseDealStore';
import SearchBoxResult from '../common/SearchBoxResult.vue';

const props = defineProps({
    searchOption: Object
})

const houseDealStore = useHouseDealStore();

const sidoOptions = ref([]);
const gugunOptions = ref([]);
const dongOptions = ref([]);

const selectedSido = ref();
const selectedGugun = ref();
const selectedDong = ref();

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

            console.log("지역 선택해서 들어옴: ", selectedSido.value, selectedGugun.value, selectedDong.value)

            getGugunFilter(selectedSido.value).then(() => {
                getDongFilter(selectedGugun.value);
            })
        }
    })

    console.log(props.searchOption)
})

const onSearch = () => {
    searchKeyword(
      houseDealStore.keyword,
        ({ data }) => {
            result.value = data;
            isLoaded.value = true;
        },
        (error) => {
            console.log(error);
        }
    )
}

const onSelectSido = (code) => {
    console.log("시도선택: ", code);
    getGugunFilter(code);
}

const onSelectGugun = (code) => {
    console.log("구군선택: ", code)
    getDongFilter(code);
}

const onSelectDong = (code) => {
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
                sidoOptions.value = data.map(({ dongCode, sidoName }) => ({ value: dongCode, text: sidoName}));
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
                    .map(({ dongCode, gugunName }) => ({ value: dongCode, text: gugunName}))
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
                .map(({ dongCode, dongName }) => ({value: dongCode, text: dongName}))
            console.log(data);
        }
    )
}

</script>

<template>
    <div class='housedeal-filter'>
        <SearchBox @onSearch='onSearch'/>
        <SearchBoxResult v-if="isLoaded" :result="result" @onResultClicked="isLoaded = false"/>
        <VSelect :selectOption=sidoOptions :selectedOption='selectedSido' @onKeySelect='onSelectSido'/>
        <VSelect :selectOption=gugunOptions :selectedOption='selectedGugun' @onKeySelect='onSelectGugun' />
        <VSelect :selectOption=dongOptions :selectedOption='selectedDong' @onKeySelect='onSelectDong' />
    </div>
</template>

<style scoped>
.housedeal-filter {
    margin-left: 50px;
}
</style>