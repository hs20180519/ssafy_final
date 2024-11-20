import { authApi } from "./Instance/authInstance";

const memberApi = authApi();

export async function checkPassword(body, success, fail) {
  return memberApi
    .post("/member/verify-password", body)
    .then(success)
    .catch(fail);
}

//   export async function getQuestionDetails(questionId) {
//     return boardApi.get(`/board/questions/${questionId}`).then().catch();
//   }

//   export async function createAnswer(questionId, content, success, fail) {
//     return boardApi
//       .post(`/board/questions/${questionId}/answers`, { content })
//       .then(success)
//       .catch(fail);
//   }
