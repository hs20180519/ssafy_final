import {api} from "./Instance/axiosInstance";
import {authApi} from "@/api/Instance/authInstance.js";

const memberApi = api();
const boardApi = authApi();

export function login(body) {
  return memberApi.post("/auth/login", body);
}

export async function logout() {
  return boardApi.post("/auth/logout")
}
