<script setup>
import { ref, defineProps, watch } from 'vue';
import { useHouseDealStore } from "@/stores/houseDealStore";

const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const houseDealStore = useHouseDealStore();

const props = defineProps({
    total: Number
})

watch(() => props.total,
(newVal) => {
    total.value = newVal;
    console.log(newVal);
})

const total = ref(props.total); // 전체 item 개수
const pageSize = Number(VITE_ARTICLE_LIST_SIZE); // 한 페이지당 element 개수

const currentChange = (num) => {
    houseDealStore.setPgno(num);
}

</script>

<template>
    <div>
        <el-pagination :page-size="pageSize" layout='prev, pager, next' :total="total" :current-page="houseDealStore.pgno" @current-change="currentChange"/>
    </div>
</template>

<style scoped>

</style>