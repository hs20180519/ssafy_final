<script setup>
import { ref, onMounted } from 'vue'
import HouseDealItem from "./item/HouseDealItem.vue";
import { defineProps, watch, defineEmits } from "vue";
import PageNavigation from "../common/PageNavigation.vue";

const props = defineProps({
    list : Array,
    total: Number
})

const emit = defineEmits([
    'onItemClick'
])

const result = ref([]);
const total = ref(props.total);

onMounted(() => {
    console.log(props.total);
})

watch(() => props.list,
    (newList) => {
        result.value = newList;
})

watch(
    () => props.total,
    (newVal) => {
        console.log(newVal);
        total.value = newVal;
    }
)

const onClick = (item) => {
    emit('onItemClick', item);
}

</script>

<template>
    <div class='housedeal-list'>
        <HouseDealItem @click="onClick(item)" v-for='item in result' :key='item.no' :item='item'/>
        <PageNavigation :total="total"/>
    </div>
</template>

<style scoped>
.housedeal-list {
    margin-left: 50px;
    width: 30%;
}
</style>