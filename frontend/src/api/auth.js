import { api } from "./Instance/noAuthInstance";
import { authApi } from "@/api/Instance/authInstance.js";

const noAuth = api();
const auth = authApi();

export function login(body) {
  return noAuth.post("/auth/login", body);
}

export async function logout() {
  return auth.post("/auth/logout");
}

function findId(param, success, fail) {
  noAuth
    .get("/auth/id", {
      params: {
        name: param.name,
        email: param.email,
      },
    })
    .then(success)
    .catch(fail);
}

function sendPasswordResetEmail(body, success, fail) {
  noAuth.post("/auth/password-reset-email", body).then(success).catch(fail);
}

function verifyResetUUID(param, success, fail) {
  noAuth
    .post("/auth/password-reset-verification", null, {
      params: {
        uuid: param.uuid,
      },
    })
    .then(success)
    .catch(fail);
}

function resetPassword(body, success, fail) {
  noAuth.put("/auth/password", body, null).then(success).catch(fail);
}

function sendSignUpEmail(email, success, fail) {
  noAuth
    .post("/auth/signup-email", null, {
      params: {
        email: email,
      },
    })
    .then(success)
    .catch(fail);
}

function verifySignUpCode(param, success, fail) {
  noAuth
    .post("/auth/signup-verification", {
      email: param.email,
      code: param.code,
    })
    .then(success)
    .catch(fail);
}

export {
  findId,
  sendPasswordResetEmail,
  verifyResetUUID,
  resetPassword,
  sendSignUpEmail,
  verifySignUpCode,
};

// 인증 필요 없는 api 사용
export async function signup(memberData) {
  return noAuth.post("/auth/signup", memberData);
}
