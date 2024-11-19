import { api } from "./Instance/authInstance";

const boardApi = api();

export function createQuestion(body, success, fail) {
  return boardApi.post("/board/questions", body).then(success).catch(fail);
}

export async function getQuestionDetails(questionId) {
  return boardApi.get(`/board/questions/${questionId}`).then().catch();
}

export async function createAnswer(questionId, content, success, fail) {
  return boardApi
    .post(`/board/questions/${questionId}/answers`, { content })
    .then(success)
    .catch(fail);
}

export async function getAnswers(questionId) {
  return boardApi.get(`/board/questions/${questionId}/answers`).then().catch();
}

export async function getQuestions() {
  return boardApi.get("/board/questions").then().catch();
}

export async function updateQuestion(questionId, question, success, fail) {
  return boardApi
    .put(`/board/questions/${questionId}`, question)
    .then(success)
    .catch(fail);
}

export async function deleteQuestion(questionId, success, fail) {
  return boardApi
    .delete(`/board/questions/${questionId}`)
    .then(success)
    .catch(fail);
}

export async function updateAnswer(answerId, content, success, fail) {
  return boardApi
    .put(`/board/answers/${answerId}`, { content })
    .then(success)
    .catch(fail);
}

export async function deleteAnswer(answerId, success, fail) {
  return boardApi
    .delete(`/board/answers/${answerId}`)
    .then(success)
    .catch(fail);
}
