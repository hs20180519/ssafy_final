import {api} from "./Instance/axiosInstance";
import {authApi} from "@/api/Instance/authInstance.js";

const memberApi = api();
const boardApi = authApi();

export function login(body) {
  return memberApi.post("/auth/login", body);
}

export async function logout() {
  return boardApi.post("/auth/logout");
}

// 인증 필요 없는 api 사용
export async function signup(memberData) {
  return memberApi.post("/auth/signup", memberData);
}
