<template>
  <div>
    <!-- Chatbot Trigger Button -->
    <button class="open-chatbot-btn" @click="toggleModal">
      <font-awesome-icon :icon="['fas', 'headset']" />
      <!-- <font-awesome-icon :icon="['fas', 'comment']" /> -->
    </button>

    <!-- Chatbot Modal -->
    <div v-if="isModalOpen" class="chatbot-modal">
      <div class="modal-overlay" @click="toggleModal"></div>
      <div class="modal-content">
        <div class="modal-header">
          <h3>Chatbot</h3>
          <button class="close-btn" @click="toggleModal">✖</button>
        </div>
        <div class="chat-display">
          <div
            v-for="(chat, index) in chatHistory"
            :key="index"
            class="chat-message"
            :class="{ user: chat.role === 'user', bot: chat.role === 'bot' }"
          >
            {{ chat.content }}
          </div>
        </div>
        <form @submit.prevent="handleSendMessage" class="chat-input">
          <input
            v-model="userMessage"
            placeholder="(예: 신혼부부인데 어떤 집이 가장 좋을까 ?)"
            required
          />
          <button type="submit">전송</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { sendMessage } from "@/api/chatbot";

export default {
  data() {
    return {
      isModalOpen: false,
      userMessage: "",
      chatHistory: [], // 대화 기록
    };
  },
  methods: {
    toggleModal() {
      this.isModalOpen = !this.isModalOpen;
      if (this.isModalOpen && this.chatHistory.length === 0) {
        // 챗봇 예시 메시지 추가
        this.chatHistory.push({
          role: "bot",
          content:
            "안녕하세요! 저는 여러분의 고민 해결을 돕는 챗봇이에요. 어떤 질문이든 편하게 물어보세요! 😊",
        });
      }
    },
    async handleSendMessage() {
      if (!this.userMessage.trim()) return;

      this.chatHistory.push({ role: "user", content: this.userMessage });

      try {
        const response = await sendMessage(this.userMessage);
        this.chatHistory.push({ role: "bot", content: response.data.response });
      } catch (error) {
        console.error("Error sending message:", error);
        this.chatHistory.push({
          role: "bot",
          content: "서버에 문제가 발생했습니다. 다시 시도해주세요.",
        });
      } finally {
        this.userMessage = "";
      }
    },
  },
};
</script>

<style scoped>
/* Chatbot Trigger Button */
.open-chatbot-btn {
  position: fixed;
  bottom: 20px;
  right: 20px;
  background: rgb(154, 177, 207);
  color: white;
  border: none;
  padding: 18px;
  border-radius: 50%;
  cursor: pointer;
  font-size: 20px;
  z-index: 1000;
  transition: background-color 0.3s ease;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
}

.open-chatbot-btn:hover {
  background: #0056b3;
  animation: shake 0.5s ease-in-out;
}

/* 떨리는 효과 */
@keyframes shake {
  0% {
    transform: translateX(0);
  }
  25% {
    transform: translateX(-5px);
  }
  50% {
    transform: translateX(5px);
  }
  75% {
    transform: translateX(-5px);
  }
  100% {
    transform: translateX(0);
  }
}

/* Modal Styles */
.chatbot-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000;
}

.modal-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  border-radius: 8px;
}

.modal-content {
  position: relative;
  width: 400px;
  background: #ffffff;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.modal-header {
  background: #70abd8;
  color: white;
  padding: 20px;
  font-size: 18px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px solid #e1e1e1;
}

.close-btn {
  background: none;
  border: none;
  color: white;
  font-size: 18px;
  cursor: pointer;
  transition: color 0.3s;
}

.close-btn:hover {
  color: #bbb;
}

.chat-display {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
  max-height: 400px;
  background: #f9f9f9;
  color: #333;
}

.chat-message {
  padding: 12px;
  margin: 12px 0;
  border-radius: 8px;
  font-size: 14px;
  line-height: 1.5;
}

.chat-message.user {
  background: #e0e0e0;
  color: #333;
  align-self: flex-end;
}

.chat-message.bot {
  background: #70abd8;
  color: white;
  align-self: flex-start;
}

.chat-input {
  display: flex;
  padding: 10px;
  gap: 12px;
  border-top: 1px solid #e1e1e1;
  background: #ffffff;
}

.chat-input input {
  flex: 1;
  padding: 12px;
  border: 1px solid #e1e1e1;
  border-radius: 5px;
  background: #fafafa;
  font-size: 14px;
  color: #333;
}

.chat-input input::placeholder {
  color: #aaa;
}

.chat-input button {
  background: #70abd8;
  color: white;
  border: none;
  padding: 12px 18px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s;
}

.chat-input button:hover {
  background: #0056b3;
}
</style>
