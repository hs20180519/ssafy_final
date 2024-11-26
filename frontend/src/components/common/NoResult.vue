<template>
    <div class="no-results-container">
      <!-- 메시지 -->
      <p class="message">
        {{ message }}
      </p>
    </div>
  </template>
  
  <script setup>
  defineProps({
    message: {
      type: String,
      required: true,
    },
  });
  </script>
  
  <style scoped>
.no-results-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: #666666; /* 메시지 색상 */
}
  
  
  .message {
    font-size: 1rem; /* 메시지 크기 */
    font-weight: 500;
    color: #333333; /* 메시지 색상 */
  }
  </style>
  