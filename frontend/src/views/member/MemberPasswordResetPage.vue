<script setup>
import MemberPasswordResetForm from '@/components/memberFind/MemberPasswordResetForm.vue'

</script>

<template>
    <div class="reset-container">

        <MemberPasswordResetForm/>
    </div>
    

</template>

<style scoped>
.reset-container {
    display: flex; /* Flexbox 활성화 */
    justify-content: center; /* 가로축 중앙 정렬 */
    align-items: center; /* 세로축 중앙 정렬 */
    height: 100vh; /* 화면 전체 높이 */
}
</style>