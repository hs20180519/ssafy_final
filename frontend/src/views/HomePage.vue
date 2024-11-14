<script setup>
import { ref } from 'vue'

import SearchBar from "@/components/common/SearchBar.vue";
import SearchBarResult from "@/components/common/SearchBarResult.vue";
import { searchKeyword } from "@/api/search"

const isLoaded = ref(false);
const result = ref();

const onSearch = (keyword) => {
    console.log(keyword);
    searchKeyword(
        keyword,
        ({ data }) => {
            result.value = data;
            isLoaded.value = true;
        },
        (error) => {
            console.log(error);
        }
    )
}

</script>

<template>
    <div>
        Home
        <SearchBar @onSearch="onSearch"/>
        <SearchBarResult v-if="isLoaded" :result="result"/>
    </div>
</template>

<style scoped>

</style>