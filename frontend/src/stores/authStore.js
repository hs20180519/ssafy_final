import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isLoggedIn: false,
    authToken: null,
    memberName: null,
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
    },
    logout() {
      this.isLoggedIn = false;
      this.authToken = null;
      this.memberName = null;
    },
  },
});
