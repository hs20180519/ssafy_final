import { defineStore } from "pinia";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    isLoggedIn: false,
    authToken: null,
    memberName: null,
    refreshToken: null,
  }),
  getters: {
    isAuthenticated: (state) => !!state.authToken,
    getAuthToken: (state) => state.authToken,
    getMember: (state) => state.memberName,
    getRefreshToken: (state) => state.refreshToken,
  },
  actions: {
    login(token, memberName, refreshToken) {
      this.isLoggedIn = true;
      this.authToken = token;
      this.memberName = memberName;
      this.refreshToken = refreshToken;
      this.saveToSessionStorage();
    },
    logout() {
      this.isLoggedIn = false;
      this.authToken = null;
      this.memberName = null;
      this.refreshToken = null;
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
          refreshToken: this.refreshToken,
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
        const { isLoggedIn, authToken, memberName, refreshToken } =
          JSON.parse(storedData);
        this.isLoggedIn = isLoggedIn;
        this.authToken = authToken;
        this.memberName = memberName;
        this.refreshToken = refreshToken;
      }
    },
  },
});
