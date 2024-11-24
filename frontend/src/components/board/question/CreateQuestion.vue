<template>
  <div class="create-question">
    <h2>새 질문 등록</h2>
    <form @submit.prevent="submitQuestion">
      <input v-model="title" placeholder="제목" required class="input"/>
      <textarea v-model="content" placeholder="내용" required class="input"></textarea>
      <button type="submit" class="btn">질문 등록</button>
    </form>
  </div>
</template>

<script>
import {createQuestion} from "@/api/board";
import {toast} from "vue3-toastify";

export default {
  name: "CreateQuestion",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    async submitQuestion() {
      const body = {title: this.title, content: this.content};
      try {
        await createQuestion(body);

        toast.success("질문이 등록되었습니다.", {autoClose: false});
        setTimeout(() => {
          this.$router.push({name: "BoardList"});
        }, 1000);
      } catch (error) {
        toast.error("질문 등록 중 오류 발생:", error);
      }
    },
  },
};
</script>

<style scoped>
.create-question {
  max-width: 600px;
  margin: 20px auto;
}

h2 {
  font-size: 1.5rem;
  text-align: center;
  margin-bottom: 20px;
}

.input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.btn {
  width: 106%;
  padding: 10px;
  background: #333;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover {
  background: #555;
}
</style>
