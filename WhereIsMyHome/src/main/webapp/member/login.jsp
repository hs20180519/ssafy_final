<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Login page" />
    <meta name="author" content="정현수" />
    <title>Login - 똑똑한 집 찾기</title>
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
                        <h3 class="text-center font-weight-light my-4">로그인</h3>
                    </div>
                    <div class="card-body">
                        <form id="loginForm" action="login.jsp" method="post">
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="inputEmail"
                                  type="email"
                                  name="mail"
                                  placeholder="name@example.com"
                                  required
                                />
                                <label for="inputEmail">아이디(이메일)</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="inputPassword"
                                  type="password"
                                  name="password"
                                  placeholder="비밀번호"
                                  required
                                />
                                <label for="inputPassword">비밀번호</label>
                            </div>
                            <div class="text-danger">
                            	<%
							    if (request.getAttribute("msg") != null) {
							    %>
							        <p><%= request.getAttribute("msg") %></p>
							    <%
							    }
							    %>
                            </div>
                            <div class="d-flex justify-content-end mt-4 mb-0">
                                <button type="submit" class="btn btn-success">로그인</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center py-3">
                        <div class="small">
                            <a href="signup.jsp" class="text-decoration-none text-success">회원가입</a>
                            <span> / </span>
                            <a href="#" class="text-decoration-none text-success">비밀번호 찾기</a>
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
        document.getElementById("loginForm").addEventListener("submit", function (event) {
            let form = document.querySelector("#loginForm");
        	form.setAttribute("action", "<%=request.getContextPath()%>/member?action=login");
        	form.submit();
        });
    </script>
</body>
</html>
