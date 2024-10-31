<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ssafy.model.dto.UserInfo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Profile page" />
    <meta name="author" content="정현수" />
    <title>Profile - 똑똑한 집 찾기</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      crossorigin="anonymous"
    />
</head>
<body>
	<% 
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (Objects.isNull(userInfo)) {
			userInfo = new UserInfo();
		}
	%>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <div class="card-header">
                        <h3 class="text-center font-weight-light my-4">프로필 조회</h3>
                    </div>
                    <div class="card-body">
                        <form id="profileForm">
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="profileName"
                                  type="text"
                                  placeholder="이름"
                                  value="<%= userInfo.getUserName() != null ? userInfo.getUserName() : "홍길동" %>"
                                  readonly
                                />
                                <label for="profileName">이름</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input
                                  class="form-control"
                                  id="profileEmail"
                                  type="email"
                                  placeholder="name@example.com"
                                  value="<%= userInfo.getUserEmail() != null ? userInfo.getUserEmail() : "hong@example.com" %>"
                                  readonly
                                />
                                <label for="profileEmail">아이디 (이메일)</label>
                            </div>
                            <!-- 비밀번호는 표시하지 않음 -->
                        </form>
                    </div>
                    <div class="card-footer text-center py-3">
                        <div class="d-flex justify-content-between">
                            <!-- 확인하기 버튼 -->
                            <a href="<%=request.getContextPath() %>/index.jsp" class="btn btn-secondary">확인하기</a>
                            <!-- 수정하기 버튼 -->
                            <a href="<%=request.getContextPath() %>/member/edit-profile.jsp" class="btn btn-primary">수정하기</a>
                            <!-- 탈퇴 버튼 -->
                            <a href="<%=request.getContextPath() %>/member?action=delete" class="btn btn-primary">탈퇴하기</a>
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
</body>
</html>
