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
    <div>
        <h3>{{ item.aptNm }}</h3>
        거래일시: {{ item.dealYear }}.{{ item.dealMonth }}.{{ item.dealDay }} <br>
        거래금액: {{ item.dealAmount }}
    </div>
</template>

<style scoped>

</style>