<template>
  <div class="create-question">
    <h2>새 질문 등록</h2>
    <form @submit.prevent="submitQuestion">
      <input v-model="title" placeholder="제목" required/>
      <textarea v-model="content" placeholder="내용" required></textarea>
      <button type="submit">질문 등록</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
import {useAuthStore} from '@/stores/authStore';

export default {
  name: 'CreateQuestion',
  data() {
    return {
      title: '',
      content: '',
    };
  },
  methods: {
    async submitQuestion() {
      const authStore = useAuthStore();  // Pinia 스토어에서 인증 정보를 가져옴
      const token = authStore.getAuthToken;
      console.log("토큰", token);
      try {
        console.log(this.title, this.content);
        const response = await axios.post('http://localhost:80/api/board/questions', {
            title: this.title,
            content: this.content,
          },
          {
            headers: {
              Authorization: `${token}`, // 토큰을 Authorization 헤더에 포함
            },
          });
        // console.log(response)
        this.$router.push('/board');
      } catch (error) {
        console.error('질문 등록 중 오류 발생:', error);
      }
    },
  },
};
</script>

<style scoped>
.create-question {
  padding: 20px;
}
</style>
