<script setup>
import { TabsPaneContext } from 'element-plus';
import { ref } from 'vue'

const activeName = ref("findId")
</script>

<template>
    <div>
        <!-- {{ activeName }} -->
        <el-tabs v-model="activeName">
            <el-tab-pane label="findId" name="findId">아이디 찾기</el-tab-pane>
            <el-tab-pane label="findPassword" name="findPassword">비밀번호 찾기</el-tab-pane>
        </el-tabs>
    </div>
</template>

<style scoped>
</style>