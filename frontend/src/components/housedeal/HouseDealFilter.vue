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

const isLoaded = ref(false);
const result = ref();

onMounted(() => {
    // console.log(houseDealStore.searchOption);
    if(houseDealStore.searchOption.sidoName) {
        // console.log()
        getDongFilter(houseDealStore.searchOption.dongCode);
    } else {
        getSidoFilter("");
    }

    
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

const getSidoFilter = (code) => {
    getFilter(
    {
        param: code
    },
        ({ data }) => {
        console.log(data);
        sidoOptions.value = data.map(({ dongCode, sidoName }) => ({ value: dongCode, text: sidoName}))
    },
    (error) => {
            console.log(error);
    })
}

const getGugunFilter = (code) => {
    console.log("getGugunFilter", code);
    getFilter(
        {
            param: code.slice(0, 2)
        },
        ({ data }) => {
            gugunOptions.value = data
                .filter(item => item.gugunName !== null)
                .map(({ dongCode, gugunName }) => ({ value: dongCode, text: gugunName}))
            console.log(data);
        },
        (error) => {
            console.log(error);
        }
    )
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
        <VSelect :selectOption=sidoOptions @onKeySelect='onSelectSido'/>
        <VSelect :selectOption=gugunOptions @onKeySelect='onSelectGugun' />
        <VSelect :selectOption=dongOptions @onKeySelect='onSelectDong' />
    </div>
</template>

<style scoped>
.housedeal-filter {
    margin-left: 50px;
}
</style>