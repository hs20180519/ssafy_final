<script setup>
import { ref } from 'vue'
import HouseDealItem from "./item/HouseDealItem.vue";
import { defineProps, watch } from "vue";

const props = defineProps({
    list : Array
})

const result = ref([]);

watch(() => props.list,
    (newList) => {
        result.value = newList;
})


</script>

<template>
    <div class='housedeal-list'>
        <HouseDealItem v-for='item in result' :key='item.no' :item='item'/>
    </div>
</template>

<style scoped>
.housedeal-list {
    margin-left: 50px;
}
</style>