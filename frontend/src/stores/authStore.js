import {defineStore} from "pinia";
import {jwtDecode} from "jwt-decode";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    isLoggedIn: false,
    authToken: null,
    memberName: null,
    memberId: null, // 사용자 아이디 추가
    memberRole: null, // TODO
    tokenExpiry: null,
  }),
  getters: {
    isAuthenticated: (state) => !!state.authToken,
    getAuthToken: (state) => state.authToken,
    getMember: (state) => state.memberName,
    getMemberId: (state) => state.memberId,
    getMemberRole: (state) => state.memberRole,

  },
  actions: {
    login(token, memberName, memberId, memberRole) {
      this.isLoggedIn = true;
      this.authToken = token;
      this.memberName = memberName;
      this.memberId = memberId;
      this.memberRole = memberRole;

      try {
        const {exp} = jwtDecode(token);
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
      this.memberId = null;
      this.memberRole = null;
      this.tokenExpiry = null;
      this.removeFromSessionStorage();
    },
    saveToSessionStorage() {
      sessionStorage.setItem(
        "auth",
        JSON.stringify({
          isLoggedIn: this.isLoggedIn,
          authToken: this.authToken,
          memberName: this.memberName,
          memberId: this.memberId,
          memberRole: this.memberRole,
          tokenExpiry: this.tokenExpiry,
        })
      );
    },
    removeFromSessionStorage() {
      sessionStorage.removeItem("auth");
    },
    restoreFromSessionStorage() {
      const storedData = sessionStorage.getItem("auth");
      if (storedData) {
        const {isLoggedIn, authToken, memberName, memberId, memberRole, tokenExpiry} =
          JSON.parse(storedData);
        this.isLoggedIn = isLoggedIn;
        this.authToken = authToken;
        this.memberName = memberName;
        this.memberId = memberId;
        this.memberRole = memberRole;
        this.tokenExpiry = tokenExpiry;
      }
    },
  },
});
