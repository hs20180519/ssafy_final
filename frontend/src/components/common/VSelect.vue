<script setup>
import { ref, watch } from "vue";
import { ElSelectV2 } from "element-plus";
const props = defineProps({ selectOption: Array, selectedOption: String });
const emit = defineEmits(["onKeySelect"]);

const key = ref(props.selectedOption);

watch(
  () => props.selectedOption,
  (newVal) => {
    key.value = newVal;
    emit("onKeySelect", key.value);
    console.log("Vselect:" , key.value);
  }
);

</script>

<template>
  <el-select-v2
    v-model='key'
    :options='selectOption'
    placeholder='선택해주세요'
    style='width: 30%; padding: 5px'
    @change='onSelect'
  />

</template>

<style scoped></style>
