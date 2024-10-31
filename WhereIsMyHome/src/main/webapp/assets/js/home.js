document.addEventListener("DOMContentLoaded", function () {
  const login = document.getElementById("login");
  const signup = document.getElementById("signup");
  const findPassword = document.getElementById("find-password");

  const logout = document.getElementById("logout");

  const profile = document.getElementById("profile");
  const addFavorite = document.getElementById("add-favorite-location");
  const favoriteLocations = document.getElementById("favorite-locations");

  const loginState = localStorage.getItem("loginState");

  const deleteUser = document.getElementById("delete-user");
  if (loginState === "true") {
    // 로그인됐을 때
    logout.style.display = "inline";
    profile.style.display = "inline";
    addFavorite.style.display = "inline";
    favoriteLocations.style.display = "inline";
    login.style.display = "none";
    signup.style.display = "none";
    findPassword.style.display = "none";
  } else {
    login.style.display = "flex";
    signup.style.display = "flex";
    findPassword.style.display = "flex";
  }

  logout.addEventListener("click", function () {
    alert("로그아웃 되었습니다.");
    localStorage.removeItem("loginState");
    window.location.reload();
  });

  deleteUser.addEventListener("click", function () {
    if (confirm("회원 탈퇴가 완료되었습니다. 페이지를 새로고침합니다.")) {
      localStorage.removeItem("loginState");
      window.location.reload();
    }
  });

  findPassword.addEventListener("click", function () {
    alert("비밀번호 찾기 ..");
  });
});
