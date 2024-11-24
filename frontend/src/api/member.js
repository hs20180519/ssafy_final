import {authApi} from "./Instance/authInstance";

const memberApi = authApi();

export async function checkPassword(body, success, fail) {
  return memberApi
    .post("/member/verify-password", body)
    .then(success)
    .catch(fail);
}

export async function getMemberInfo() {
  return memberApi.get("/member");
}

export async function updateMemberInfo(body) {
  return memberApi.put("/member", body);
}

export async function deleteMember() {
  return memberApi.delete("/member");
}
