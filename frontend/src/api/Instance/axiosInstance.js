import axios from "axios";

const { VITE_VUE_API_URL } = import.meta.env;

// 로그인, 회원가입 + 인증 필요 없는 api
function api() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    withCredentials: true,
  });
  instance.interceptors.request.use((config) => {
    console.log(`${config.url}`);
    return config;
  });
  return instance;
}

export { api };
