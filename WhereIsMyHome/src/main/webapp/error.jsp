<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Error Page" />
    <meta name="author" content="정현수" />
    <title>Error - 똑똑한 집 찾기</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      crossorigin="anonymous"
    />
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10 col-sm-12">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <div class="card-header">
                        <h2 class="text-center text-danger font-weight-bold my-4">에러 발생!!!</h2>
                    </div>
                    <div class="card-body">
                        <h4 class="text-center text-danger">처리 중 에러 발생</h4>
                        <p class="text-center text-secondary">
                            ${error}
                        </p>
                    </div>
                    <div class="d-flex justify-content-center mb-4">
                        <button type="button" id="btn-index" class="btn btn-outline-danger">메인 페이지 이동...</button>
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
      document.querySelector("#btn-index").addEventListener("click", function () {
        location.href = "<%=request.getContextPath()%>";
      });
    </script>
</body>
</html>
