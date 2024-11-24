<script setup>
import { defineProps, defineEmits, ref, onMounted } from 'vue'
import router from '@/router';

const props = defineProps({
    foundId: String
})

const emit = defineEmits([
    'goBack'
])

onMounted(() => {
    if (props.foundId == "") {
        found.value = false;
    }
})

const found = ref(true);

const goLogin = () => {
    router.push({
        name: 'Login'
    })
}

const goBack = () => {
    emit('goBack')
}

</script>

<template>
    <div v-if='found'>
        <h2>고객님의 아이디를 찾았습니다.</h2>
        {{ foundId }}
        <div>
            <button @click='goLogin'>로그인하러 가기</button>
        </div>
    </div>
    <div v-if='!found'>
        <h2>정보와 일치하는 아이디가 없습니다.</h2>
        <button @click='goBack'>뒤로가기</button>
    </div>
</template>

<style scoped>

</style>