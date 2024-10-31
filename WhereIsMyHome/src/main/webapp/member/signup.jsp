<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Sign Up page" />
    <meta name="author" content="정현수" />
    <title>Sign Up - 똑똑한 집 찾기</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      crossorigin="anonymous"
    />
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <div class="card-header">
                        <h3 class="text-center font-weight-light my-4">회원가입</h3>
                    </div>
                    <div class="card-body">
                        <form id="signupForm" action="" method="POST">
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="inputName"
                                  type="text"
                                  name="name"
                                  placeholder="이름"
                                  required
                                />
                                <label for="inputName">이름</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="inputEmail"
                                  type="email"
                                  name="email"
                                  placeholder="name@example.com"
                                  required
                                />
                                <label for="inputEmail">아이디 (이메일)</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="inputPassword"
                                  type="password"
                                  name="password"
                                  placeholder="비밀번호"
                                  minlength="4"
                                  required
                                />
                                <label for="inputPassword">비밀번호</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="inputConfirmPassword"
                                  type="password"
                                  name="confirmPassword"
                                  placeholder="비밀번호 확인"
                                  minlength="4"
                                  required
                                />
                                <label for="inputConfirmPassword">비밀번호 확인</label>
                            </div>
                            <div class="d-flex justify-content-end mt-4 mb-0">
                                <button type="submit" class="btn btn-success">회원가입</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center py-3">
                        <div class="small">
                            <a href="login.jsp" class="text-decoration-none text-success">로그인으로 돌아가기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script>
        document.getElementById("signupForm").addEventListener("submit", function (event) {
            event.preventDefault();

	        const name = document.getElementById("inputName").value;
            const email = document.getElementById("inputEmail").value;
            const password = document.getElementById("inputPassword").value;
            const confirmPassword = document.getElementById("inputConfirmPassword").value;
            
         	// 이메일 형식 검증 (기본 검사)
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
         
            if (!emailPattern.test(email)) {
                alert("올바른 이메일 주소를 입력해 주세요.");
            } else if (password.length < 4) {
                alert("비밀번호는 최소 4자 이상이어야 합니다.");
            } else if (password !== confirmPassword) {
                alert("비밀번호가 일치하지 않습니다.");
            } else {
            	let form = document.querySelector("#signupForm");
            	form.setAttribute("action", "<%=request.getContextPath()%>/member?action=signup");
            	form.submit();
            }
        });
    </script>
</body>
</html>
