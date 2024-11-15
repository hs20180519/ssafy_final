<script setup>
import { onMounted, ref } from 'vue'
import SearchBox from "@/components/common/SearchBox.vue";
import VSelect from "@/components/common/VSelect.vue";
import { getFilter } from "@/api/search"

const props = defineProps({
    searchOption: Object
})

const sidoOptions = ref([]);
const gugunOptions = ref([]);
const dongOptions = ref([]);

const isHouse = ref(false);

onMounted(() => {
    getSidoFilter("");
    isHouse.value = props.searchOption.type == "house" ? true : false;
})

defineEmits([
    'onKeySelect'
])

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
        <SearchBox :text='isHouse ? searchOption.keyword : ""'/>
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