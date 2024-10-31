<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.model.dto.UserInfo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Edit Profile page" />
    <meta name="author" content="정현수" />
    <title>Edit Profile - 똑똑한 집 찾기</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      crossorigin="anonymous"
    />
</head>

<body>
	<% 
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		
	%>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <div class="card-header">
                        <h3 class="text-center font-weight-light my-4">프로필 수정</h3>
                    </div>
                    <div class="card-body">
                        <form id="editProfileForm" action="editProfile.jsp" method="post">
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="editName"
                                  type="text"
                                  name="name"
                                  placeholder="이름"
                                  value="<%= userInfo.getUserName() %>"
                                  required
                                />
                                <label for="editName">이름</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="editEmail"
                                  type="email"
                                  name="email"
                                  placeholder="name@example.com"
                                  value="<%= userInfo.getUserEmail() %>"
                                  required
                                />
                                <label for="editEmail">아이디 (이메일)</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="editPassword"
                                  type="password"
                                  name="password"
                                  placeholder="비밀번호"
                                  minlength="4"
                                />
                                <label for="editPassword">비밀번호 (변경 시 입력)</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="editConfirmPassword"
                                  type="password"
                                  name="confirmPassword"
                                  placeholder="비밀번호 확인"
                                  minlength="4"
                                />
                                <label for="editConfirmPassword">비밀번호 확인</label>
                            </div>
                            <div class="d-flex justify-content-end mt-4 mb-0">
                                <button type="submit" class="btn btn-success">저장하기</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center py-3">
                        <div class="small">
                            <a href="profile.jsp" class="text-decoration-none text-success">프로필로 돌아가기</a>
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
        document.getElementById("editProfileForm").addEventListener("submit", function (event) {
            event.preventDefault();
            // 이메일 형식 검증 (기본 검사)
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            const name = document.getElementById("editName").value;
            const email = document.getElementById("editEmail").value;
            const password = document.getElementById("editPassword").value;
            const confirmPassword = document.getElementById("editConfirmPassword").value;
            
            if (!emailPattern.test(email)) {
                alert("올바른 이메일 주소를 입력해 주세요.");
            } else if (password.length != 0 && password.length < 4) {
                alert("비밀번호는 최소 4자 이상이어야 합니다.");
            } else if (password !== confirmPassword) {
                alert("비밀번호가 일치하지 않습니다.");
            } else {
            	let form = document.querySelector("#editProfileForm");
            	form.setAttribute("action", "<%=request.getContextPath()%>/member?action=edit");
            	form.submit();
            }
        });
    </script>
</body>
</html>
