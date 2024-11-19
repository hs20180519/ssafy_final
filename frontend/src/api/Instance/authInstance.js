import axios from "axios";

const { VITE_VUE_API_URL } = import.meta.env;
import { useAuthStore } from "@/stores/authStore";
import { refreshAccessToken } from "../member";

export const api = () => {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });

  // 요청 인터셉터
  instance.interceptors.request.use(
    (config) => {
      const authStore = useAuthStore();
      const token = authStore.getAuthToken;

      if (token) {
        config.headers["Authorization"] = `Bearer ${token}`;
      }

      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  // 응답 인터셉터
  instance.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      const originalRequest = error.config;
      const authStore = useAuthStore();

      // 401 오류 발생 시, 리프레시 토큰을 사용하여 액세스 토큰 갱신 시도
      if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;

        try {
          const refreshToken = authStore.getRefreshToken; // Pinia에서 리프레시 토큰 가져오기
          const response = await refreshAccessToken(refreshToken); // 리프레시 토큰으로 새로운 액세스 토큰 요청

          const newAccessToken = response.headers["authorization"].replace(
            "Bearer ",
            ""
          );
          authStore.login(newAccessToken, authStore.getMember); // 새로운 액세스 토큰으로 로그인 상태 갱신

          // 새로운 액세스 토큰을 기존 요청에 추가하고 재시도
          originalRequest.headers["Authorization"] = `Bearer ${newAccessToken}`;
          return axios(originalRequest);
        } catch (refreshError) {
          console.error("리프레시 토큰 갱신 실패", refreshError);
          // 리프레시 토큰 갱신 실패 시 로그아웃 처리 등
        }
      }

      return Promise.reject(error);
    }
  );

  return instance;
};
