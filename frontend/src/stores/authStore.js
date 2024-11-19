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
    },
    logout() {
      this.isLoggedIn = false;
      this.authToken = null;
      this.memberName = null;
      this.refreshToken = null;
    },
  },
});
