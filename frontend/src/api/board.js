import { api } from "./axiosInstance";

const boardApi = api();

export function createQuestion(body, token, success, fail) {
  return boardApi
    .post("/board/questions", body, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then(success)
    .catch(fail);
}

export async function getQuestionDetails(questionId, token) {
  return boardApi
    .get(`/board/questions/${questionId}`, {
      headers: { Authorization: `Bearer ${token}` },
    })
    .then()
    .catch();
}

export async function createAnswer(questionId, content, token, success, fail) {
  return boardApi
    .post(
      `/board/questions/${questionId}/answers`,
      { content },
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    )
    .then(success)
    .catch(fail);
}

export async function getAnswers(questionId, token) {
  return boardApi
    .get(`/board/questions/${questionId}/answers`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then()
    .catch();
}

export async function getQuestions(token) {
  return boardApi
    .get("/board/questions", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then()
    .catch();
}
export async function updateQuestion(
  questionId,
  question,
  token,
  success,
  fail
) {
  return boardApi
    .put(`/board/questions/${questionId}`, question, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then(success)
    .catch(fail);
}

export async function deleteQuestion(questionId, token, success, fail) {
  return boardApi
    .delete(`/board/questions/${questionId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then(success)
    .catch(fail);
}

export async function updateAnswer(answerId, content, token, success, fail) {
  return boardApi
    .put(
      `/board/answers/${answerId}`,
      { content },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    )
    .then(success)
    .catch(fail);
}

export async function deleteAnswer(answerId, token, success, fail) {
  return boardApi
    .delete(`/board/answers/${answerId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then(success)
    .catch(fail);
}
