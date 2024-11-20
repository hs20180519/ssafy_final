import { defineStore } from "pinia";
import { jwtDecode } from "jwt-decode";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    isLoggedIn: false,
    authToken: null,
    memberName: null,
    tokenExpiry: null, // 만료 시간 추가
  }),
  getters: {
    isAuthenticated: (state) => !!state.authToken,
    getAuthToken: (state) => state.authToken,
    getMember: (state) => state.memberName,
  },
  actions: {
    login(token, memberName) {
      this.isLoggedIn = true;
      this.authToken = token;
      this.memberName = memberName;

      try {
        const { exp } = jwtDecode(token); // 만료 시간 설정
        this.tokenExpiry = exp * 1000;
      } catch (error) {
        console.error("Invalid token:", error);
        this.logout();
      }

      this.saveToSessionStorage();
    },
    logout() {
      this.isLoggedIn = false;
      this.authToken = null;
      this.memberName = null;
      this.tokenExpiry = null;
      this.removeFromSessionStorage();
    },
    // 세션 스토리지에 상태 저장
    saveToSessionStorage() {
      sessionStorage.setItem(
        "auth",
        JSON.stringify({
          isLoggedIn: this.isLoggedIn,
          authToken: this.authToken,
          memberName: this.memberName,
          tokenExpiry: this.tokenExpiry,
        })
      );
    },
    // 세션 스토리지에서 상태 제거
    removeFromSessionStorage() {
      sessionStorage.removeItem("auth");
    },
    // 세션 스토리지에서 상태 복원
    restoreFromSessionStorage() {
      const storedData = sessionStorage.getItem("auth");
      if (storedData) {
        const { isLoggedIn, authToken, memberName, tokenExpiry } =
          JSON.parse(storedData);
        this.isLoggedIn = isLoggedIn;
        this.authToken = authToken;
        this.memberName = memberName;
        this.tokenExpiry = tokenExpiry;
      }
    },
  },
});
