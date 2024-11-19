import { api } from "./Instance/axiosInstance";

const memberApi = api();

export function login(body) {
  return memberApi.post("/auth/login", body);
}

export function refreshAccessToken(refreshToken) {
  return memberApi.post("/auth/refresh", { refreshToken });
}
