<script setup>
import { onMounted, ref, watch } from "vue";
const props = defineProps({ selectOption: Array, selectedOption: String });
const emit = defineEmits(["onKeySelect"]);

const key = ref(props.selectedOption);

watch(
  () => props.selectedOption,
  (newVal) => {
      key.value = newVal;
      console.log("Vselect:" , key.value);
    }
);

const onSelect = () => {
  console.log(key.value + "선택");
  emit("onKeySelect", key.value);
};
</script>

<template>
  <select v-model="key" class="form-select form-select-sm ms-5 me-1 w-50" @change="onSelect">
    <option
      v-for="option in selectOption"
      :key="option.value"
      :value="option.value"
      :disabled="option.value === '' ? true : false"
    >
      {{ option.text }}
    </option>
  </select>
</template>

<style scoped></style>
