import { api } from "./Instance/axiosInstance";

const memberApi = api();

export function login(body) {
  return memberApi.post("/auth/login", body, {
    withCredentials: true,
  });
}
