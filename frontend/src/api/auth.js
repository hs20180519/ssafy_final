import {api} from "./Instance/axiosInstance";
import {authApi} from "@/api/Instance/authInstance.js";

const noAuth = api();
const auth = authApi();

export function login(body) {
  return noAuth.post("/auth/login", body);
}

export async function logout() {
  return auth.post("/auth/logout")
}

function findId(param, success, fail) {
  noAuth.get("/auth/id", {
    params: {
      name: param.name,
      email: param.email
    },
  })
  .then(success)
  .catch(fail);
}

function sendPasswordResetEmail(param, success, fail) {
  noAuth.post('/auth/password-reset-email', null, {
    params: {
      email: param.email
    }
  })
  .then(success)
  .catch(fail)
}

export {findId, sendPasswordResetEmail}
