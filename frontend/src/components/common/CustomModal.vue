<template>
  <div class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <h3>{{ title }}</h3>
      <p v-html="message"></p>
      <div class="button-group">
        <button class="confirm-button" @click="onConfirm">확인</button>
        <button class="cancel-button" @click="closeModal">취소</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: "알림",
    },
    message: {
      type: String,
      default: "이 작업을 진행하시겠습니까?",
    },
    onConfirm: {
      type: Function,
      required: true,
    },
  },
  methods: {
    closeModal() {
      this.$emit("close");
    },
  },
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.button-group {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.confirm-button {
  background-color: #d26464;
  color: white;
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}

.cancel-button {
  background-color: #aaa;
  color: white;
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}

.confirm-button:hover {
  background-color: #cc0000;
}

.cancel-button:hover {
  background-color: #888;
}
</style>
