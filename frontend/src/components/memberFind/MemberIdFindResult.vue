<script setup>
import { defineProps, ref, onMounted } from 'vue'
import router from '@/router';

const props = defineProps({
    foundId: String
})


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

</script>

<template>
    <div v-if='found'>
        <h2>고객님의 아이디를 찾았습니다.</h2>
        {{ foundId }}
        <div>
            <button @click='goLogin'>로그인하러 가기</button>
        </div>
    </div>
</template>

<style scoped>
button {
  margin-top: 10px;
  padding: 10px;
  background-color: #4e4e4e;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #333;
}
</style>