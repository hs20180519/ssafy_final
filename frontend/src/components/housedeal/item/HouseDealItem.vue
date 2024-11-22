<script setup>
import { defineProps, onUpdated } from 'vue'

const props = defineProps({
    item: Object
})

onUpdated(() => {
    console.log(props.item);
})
</script>

<template>
    <div class="main-container">
        <h3>{{ item.aptNm }}</h3>
        최근거래일시: {{ item.latestDealYear }}.{{ item.latestDealMonth }}.{{ item.latestDealDay }} <br>
        최근거래금액: {{ item.latestDealAmount }} (만원)
    </div>
</template>

<style scoped>
.main-container {
    width: 100%;
}
</style>