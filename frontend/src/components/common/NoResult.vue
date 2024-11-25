<template>
    <div class="no-results-container">
      <!-- 이모티콘 -->
      <div class="emoji" v-if="emoji">
        {{ emoji }}
      </div>
      <!-- 메시지 -->
      <p class="message">
        {{ message }}
      </p>
    </div>
  </template>
  
  <script setup>
  defineProps({
    emoji: {
      type: String,
      required: true, // 이모티콘은 필수값
    },
    message: {
      type: String,
      required: true, // 메시지도 필수값
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
  
  .emoji {
    font-size: 3rem; /* 이모티콘 크기 */
  }
  
  .message {
    font-size: 1.2rem; /* 메시지 크기 */
    font-weight: 500;
    color: #333333; /* 메시지 색상 */
  }
  </style>
  