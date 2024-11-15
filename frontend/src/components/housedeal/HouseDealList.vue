<script setup>
import HouseDealItem from "./item/HouseDealItem.vue";
import { defineProps, onMounted } from "vue";

const props = defineProps({
    list : Object
})

const result = props.list;

onMounted(() => {
    console.log(result);
})


</script>

<template>
    <HouseDealItem v-for='item in result' :key='item.no' :item='item'/>
</template>

<style scoped>

</style>