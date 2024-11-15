<script setup>
import { ref } from 'vue'

const keyword = ref("");
</script>

<template>
    <div>
        <form @submit.prevent="$emit('onSearch', keyword)">
            <input type="text" v-model="keyword">
            <input type="submit">
        </form>
    </div>
</template>

<style scoped>

</style>