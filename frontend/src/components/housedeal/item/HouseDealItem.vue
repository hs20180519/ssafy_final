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
        {{ item }}
    </div>
</template>

<style scoped>

</style>