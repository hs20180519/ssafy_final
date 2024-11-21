<script setup>
import { ref } from 'vue'
import HouseDealItem from "./item/HouseDealItem.vue";
import { defineProps, watch, defineEmits } from "vue";
import PageNavigation from "../common/PageNavigation.vue";

const props = defineProps({
    list : Array
})

const emit = defineEmits([
    'onItemClick'
])

const result = ref([]);

watch(() => props.list,
    (newList) => {
        result.value = newList;
})

const onClick = (item) => {
    emit('onItemClick', item);
}

</script>

<template>
    <div class='housedeal-list'>
        <HouseDealItem @click="onClick(item)" v-for='item in result' :key='item.no' :item='item'/>
        <PageNavigation />
    </div>
</template>

<style scoped>
.housedeal-list {
    margin-left: 50px;
    width: 30%;
}
</style>