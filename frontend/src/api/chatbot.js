import { authApi } from "./Instance/authInstance";

const chatbotApi = authApi();

export async function sendMessage(message, favoriteProperties, success, fail) {
  // 서버로 전송할 데이터에 `message`와 `favoriteProperties` 추가
  return chatbotApi
    .post("/chat", { message, favoriteProperties }) // favoriteProperties도 함께 전달
    .then(success)
    .catch(fail);
}
