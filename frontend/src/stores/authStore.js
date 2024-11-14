import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    authToken: null,
    user: null,
  }),
  actions: {
    login(token, user) {
      this.authToken = token;
      this.user = user;
    },
    logout() {
      this.authToken = null;
      this.user = null;
    },
  },
  getters: {
    isAuthenticated: (state) => !!state.authToken,
    getAuthToken: (state) => state.authToken,
    getUser: (state) => state.user,
  },
});
