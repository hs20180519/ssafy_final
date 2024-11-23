import {defineStore} from "pinia";
import {jwtDecode} from "jwt-decode";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    isLoggedIn: false,
    authToken: null,
    memberName: null,
    memberId: null, // 사용자 아이디 추가
    tokenExpiry: null,
  }),
  getters: {
    isAuthenticated: (state) => !!state.authToken,
    getAuthToken: (state) => state.authToken,
    getMember: (state) => state.memberName,
    getMemberId: (state) => state.memberId, // 사용자 아이디 반환
  },
  actions: {
    login(token, memberName, memberId) {
      this.isLoggedIn = true;
      this.authToken = token;
      this.memberName = memberName;
      this.memberId = memberId; // 로그인 시 아이디 저장

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
      this.memberId = null; // 로그아웃 시 아이디도 초기화
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
          memberId: this.memberId, // 아이디도 세션에 저장
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
        const {isLoggedIn, authToken, memberName, memberId, tokenExpiry} =
          JSON.parse(storedData);
        this.isLoggedIn = isLoggedIn;
        this.authToken = authToken;
        this.memberName = memberName;
        this.memberId = memberId; // 아이디 복원
        this.tokenExpiry = tokenExpiry;
      }
    },
  },
});
