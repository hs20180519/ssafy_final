import { authApi } from "./Instance/authInstance";

const chatbotApi = authApi();

export async function sendMessage(message, success, fail) {
  return chatbotApi.post("/chat", { message }).then(success).catch(fail);
}
