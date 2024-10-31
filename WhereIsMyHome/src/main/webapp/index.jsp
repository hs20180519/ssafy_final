<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Static Navigation - SB Admin</title>
<link href="/assets/css/style.css" rel="stylesheet" />
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />

<!-- Bootstrap Bundle JS (JavaScript 포함) -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<script src="./assets/js/key.js"></script>
</head>

<body>
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-success">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="index.jsp">똑똑한 집 찾기</a>
		<!-- Sidebar Toggle-->

		<!--narbar-->

		<!-- 로그인 전-->
		<%
		// 예시: 세션에 'user' 속성이 없으면 로그인 전 상태 
		boolean isLoggedIn = session.getAttribute("userInfo") != null;
		String root = request.getContextPath();
		%>

		<div class="d-flex ms-auto me-3">
			<c:if test="<%=!isLoggedIn %>">
				<a class="btn btn-outline-light me-3" id="login"
					href="<%=root%>/member/login.jsp">로그인</a>
				<!-- <a class="btn btn-outline-light me-3" id="find-password" href="#">비밀번호
					찾기</a> -->
				<a class="btn btn-outline-light" id="signup"
					href="<%=root%>/member/signup.jsp">회원가입</a>
				
			</c:if>
		</div>

		<!-- 로그인 후-->
		<div class="d-flex me-3">
			<c:if test="<%=isLoggedIn %>">
			      
				<a class="btn btn-outline-light me-3" id="add-favorite-location"
					href="/add-favorite-location.jsp"> 관심지역추가 </a>
				<a class="btn btn-outline-light me-3" id="favorite-locations"
					href="/favorite-locations.jsp"> 관심지역 </a>
				<a class="btn btn-outline-light me-3" id="logout" href="<%= root %>/member?action=logout">
				  로그아웃
				</a>
				<a class="btn btn-outline-light me-3" href="<%=root %>/member/profile.jsp">
					프로필 조회
				</a>
				<a class="btn btn-outline-light me-3" href="<%=root %>/member/edit-profile.jsp">
				   프로필 수정
				</a>
				<form class="d-flex">
		        	<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
		        	<button class="btn btn-outline-light" type="submit">Search</button>
		      	</form>

				<!-- <div class="dropdown" id="profile">
					<button
						class="btn btn-secondary dropdown-toggle bg-success border-white"
						type="button" data-bs-toggle="dropdown" aria-expanded="false">
						<i class="fa-regular fa-user me-5"></i>
					</button>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" id="logout" href="/logout.jsp">로그아웃</a></li>
						<li><a class="dropdown-item" href="/profile.jsp">프로필 조회</a></li>
						<li><a class="dropdown-item" href="/edit-profile.jsp">프로필
								수정</a></li>
						<li><a class="dropdown-item" href="#" id="delete-user">회원
								탈퇴</a></li>
					</ul>
				</div> -->
			</c:if>
		</div>
	</nav>
	<div class="map-container">
		<!-- 중앙 content start -->
		<div class="container">
			<div style="height: 70px"></div>
			<form class="d-flex">
				<div class="form-group col-md-2">
						<select class="form-select bg-success text-light" id="search-year"></select>
				</div>
		        <input class="form-control me-3" type="search" placeholder="Search" aria-label="Search" id="search-text">
		        <button class="btn btn-outline-success" type="button" id="search-btn">Search</button>
	        </form>
			<!-- 중앙 left content  start -->

			<div class="">
				<div class="alert alert-success mt-3 text-center fw-bold"
					role="alert">아파트 정보</div>
				<!-- 아파트 매매 정보 검색 start -->
				<div class="row col-md-12 justify-content-center mb-2">
					<div class="form-group col-md-2">
						<select class="form-select bg-secondary text-light" id="sido">
							<option value="">시도선택</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<select class="form-select bg-secondary text-light" id="gugun">
							<option value="">구군선택</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<select class="form-select bg-secondary text-light" id="dong">
							<option value="">동선택</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<select class="form-select bg-dark text-light" id="year"></select>
					</div>
					<div class="form-group col-md-2">
						<select class="form-select bg-dark text-light" id="month">
							<option value="">매매월선택</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<button type="button" id="list-btn"
							class="btn btn-outline-primary">아파트매매정보</button>
					</div>
				</div>
				<!-- kakao map start -->
				<div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
				<!-- kakao map end -->
				<table class="table table-hover text-center" style="display: none">
					<tr>
						<th>아파트이름</th>
						<th>층</th>
						<th>면적</th>
						<th>거래일</th>
						<th>거래금액</th>
					</tr>
					<tbody id="aptlist"></tbody>
				</table>
				<!-- 아파트 매매 정보 검색 end -->
			</div>
		</div>
	</div>
	<!-- 중앙 content end -->

	<!-- 하단 footer start -->
	<footer
		class="navbar navbar-expand-lg navbar-light bg-light container justify-content-end">
		<div class="row">
			<ul class="navbar-nav">
				<li><a href="#" class="nav-link text-secondary">카페소개</a></li>
				<li><a href="#" class="nav-link text-secondary">개인정보처리방침</a></li>
				<li><a href="#" class="nav-link text-secondary">이용약관</a></li>
				<li><a href="#" class="nav-link text-secondary">오시는길</a></li>
				<li><label class="nav-link text-secondary">&copy; SSAFY
						Corp.</label></li>
			</ul>
		</div>
	</footer>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=97f31e57896f9e2e1365fc9d8ab26972&libraries=services,clusterer,drawing"></script>
	<script>
	
        ///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
        let date = new Date();

        let yearEl = document.querySelector("#year");
        let searchYearEl = document.querySelector("#search-year");
        let yearOpt = `<option value="">매매년도선택</option>`;
        let year = date.getFullYear();
        for (let i = year; i > year - 20; i--) {
          yearOpt += `<option value="\${i}">\${i}년</option>`;
        }
        yearEl.innerHTML = yearOpt;
        searchYearEl.innerHTML = yearOpt;

        // 브라우저가 열리면 시도정보 얻기.
        sendRequest("sido", "*00000000");

        document.querySelector("#year").addEventListener("change", function () {
          let month = date.getMonth() + 1;
          let monthEl = document.querySelector("#month");
          let monthOpt = `<option value="">매매월선택</option>`;
          let yearSel = document.querySelector("#year");
          let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
          for (let i = 1; i < m; i++) {
            monthOpt += `<option value="\${i < 10 ? "0" + i : i}">\${i}월</option>`;
          }
          monthEl.innerHTML = monthOpt;
        });

        // https://juso.dev/docs/reg-code-api/
        // let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
        // let regcode = "*00000000";
        // 전국 특별/광역시, 도
        // https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000

        // 시도가 바뀌면 구군정보 얻기.
        document.querySelector("#sido").addEventListener("change", function () {
          if (this[this.selectedIndex].value) {
            let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
            sendRequest("gugun", regcode);
          } else {
            initOption("gugun");
            initOption("dong");
          }
        });

        // 구군이 바뀌면 동정보 얻기.
        document.querySelector("#gugun").addEventListener("change", function () {
          if (this[this.selectedIndex].value) {
            let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
            sendRequest("dong", regcode);
          } else {
            initOption("dong");
          }
        });

        function sendRequest(selid, regcode) {
          const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
          let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
          fetch(`\${url}?\${params}`)
            .then((response) => response.json())
            .then((data) => {
              console.log("Received Data: ", data); // 데이터 확인
              addOption(selid, data);
            })
        }

        function addOption(selid, data) {
          let opt = ``;
          initOption(selid);
          switch (selid) {
            case "sido":
              opt += `<option value="">시도선택</option>`;
              data.regcodes.forEach(function (regcode) {
                opt += `
                <option value="\${regcode.code}">\${regcode.name}</option>
                `;
              });
              break;
            case "gugun":
              opt += `<option value="">구군선택</option>`;
              for (let i = 0; i < data.regcodes.length; i++) {
                if (i != data.regcodes.length - 1) {
                  if (
                    data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
                    data.regcodes[i].name.split(" ").length !=
                    data.regcodes[i + 1].name.split(" ").length
                  ) {
                    data.regcodes.splice(i, 1);
                    i--;
                  }
                }
              }
              let name = "";
              data.regcodes.forEach(function (regcode) {
                if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
                else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
                opt += `
                <option value="\${regcode.code}">\${name}</option>
                `;
              });
              break;
            case "dong":
              opt += `<option value="">동선택</option>`;
              let idx = 2;
              data.regcodes.forEach(function (regcode) {
                if (regcode.name.split(" ").length != 3) idx = 3;
                opt += `
                <option value="\${regcode.code}">\${regcode.name.split(" ")[idx]}</option>
                `;
              });
          }
          document.querySelector(`#\${selid}`).innerHTML = opt;
        }

        function initOption(selid) {
          let options = document.querySelector(`#\${selid}`);
          options.length = 0;
        }
        
        ///////////////////////// 아파트 매매 정보 검색 /////////////////////////
        document.querySelector("#search-btn").addEventListener("click", function () {
         // let url =
         //   "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
          let searchKeywordSel = document.querySelector("#search-text");
          let searchKeyword = searchKeywordSel.value;
          let yearSel = document.querySelector("#search-year");
          let searchYear = yearSel[yearSel.selectedIndex].value;
          
          console.log(searchKeyword);
          
          fetch(`/WhereIsMyHome/house/search?keyword=\${searchKeyword}&searchYear=\${searchYear}`)
          .then((response) => {
              if (!response.ok) {
                  throw new Error('Network response was not ok');
              }
              return response.json();
          })
          .then((data) => {
              // 데이터 처리 (makeList 함수 호출)
              makeList2(data);
          })
          .catch((error) => {
              console.error('Error:', error);
          });
  			});
        
        function makeList2(data) {
            document.querySelector("table").setAttribute("style", "display: ;");
            let tbody = document.querySelector("#aptlist");
            console.log(data);
            initTable(); // 테이블 초기화

            data.forEach((apt) => {
                let tr = document.createElement("tr");

                let nameTd = document.createElement("td");
                nameTd.appendChild(document.createTextNode(apt.aptNm)); // 아파트 이름
                tr.appendChild(nameTd);

                let floorTd = document.createElement("td");
                floorTd.appendChild(document.createTextNode(apt.floor)); // 층
                tr.appendChild(floorTd);

                let areaTd = document.createElement("td");
                areaTd.appendChild(document.createTextNode(apt.excluUseAr)); // 전용 면적
                tr.appendChild(areaTd);

                let dongTd = document.createElement("td");
				let aptDealDate = apt.dealYear + "년 " + apt.dealMonth + "월 " + apt.dealDay +"일";
                dongTd.appendChild(document.createTextNode(aptDealDate)); // 거래 년 월 일
                tr.appendChild(dongTd);

                let priceTd = document.createElement("td");
                priceTd.appendChild(document.createTextNode(apt.dealAmount + "만원")); // 거래금액
                priceTd.classList.add("text-end");
                tr.appendChild(priceTd);

				// 도로명 주소 생성
				let roadAddress = apt.roadNm+apt.roadNmBonbun;
				if (apt.roadNmBubun && apt.roadNmBubun !== "0") {
					roadAddress += "-" + apt.roadNmBubun; // 부번이 있을 경우 추가
				}
                tr.addEventListener("click", () => viewMap(apt.aptNm, roadAddress)); // 클릭 이벤트
                tbody.appendChild(tr);



            });
        }


        function makeList(data) {
          document.querySelector("table").setAttribute("style", "display: ;");
          let tbody = document.querySelector("#aptlist");
          let parser = new DOMParser();
          const xml = parser.parseFromString(data, "application/xml");
          console.log(xml);
          initTable();
          let apts = xml.querySelectorAll("item");
          apts.forEach((apt) => {
            let tr = document.createElement("tr");

            let nameTd = document.createElement("td");
            nameTd.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
            tr.appendChild(nameTd);

            let floorTd = document.createElement("td");
            floorTd.appendChild(document.createTextNode(apt.querySelector("층").textContent));
            tr.appendChild(floorTd);

            let areaTd = document.createElement("td");
            areaTd.appendChild(document.createTextNode(apt.querySelector("전용면적").textContent));
            tr.appendChild(areaTd);

            let dongTd = document.createElement("td");
            dongTd.appendChild(document.createTextNode(apt.querySelector("법정동").textContent));
            tr.appendChild(dongTd);

            let priceTd = document.createElement("td");
            priceTd.appendChild(
              document.createTextNode(apt.querySelector("거래금액").textContent + "만원")
            );
            priceTd.classList.add("text-end");
            tr.appendChild(priceTd);

            let address =
              apt.querySelector("중개사소재지").textContent +
              " " +
              apt.querySelector("법정동").textContent +
              " " +
              apt.querySelector("지번").textContent;
            tr.addEventListener("click", () =>
              viewMap(apt.querySelector("아파트").textContent, address)
            );

            tbody.appendChild(tr);
          });
        }
        
        ///////////////////////// 아파트 매매 정보 /////////////////////////
        document.querySelector("#list-btn").addEventListener("click", function () {
         // let url =
         //   "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
          let gugunSel = document.querySelector("#gugun");
          let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0,5);
          let yearSel = document.querySelector("#year");
          let year = yearSel[yearSel.selectedIndex].value;
          let monthSel = document.querySelector("#month");
          let month = monthSel[monthSel.selectedIndex].value;
          let dealYM = year + month;
          
          let dongSel = document.querySelector("#dong");
     
          
          
         
          let sidoCode =  regCode.substr(0, 2); // 시도 코드
	      let gugunCode = regCode.substr(2, 3); // 구 코드
	      let dongCode = dongSel[dongSel.selectedIndex].value.substr(5,5);// 동 
         
          
          fetch(`/WhereIsMyHome/house?sidoCode=\${sidoCode}&gugunCode=\${gugunCode}&dongCode=\${dongCode}`)
          .then((response) => {
              if (!response.ok) {
                  throw new Error('Network response was not ok');
              }
              return response.json();
          })
          .then((data) => {
              // 데이터 처리 (makeList 함수 호출)
              makeList2(data);
          })
          .catch((error) => {
              console.error('Error:', error);
          });
  			});
        
        function makeList2(data) {
            document.querySelector("table").setAttribute("style", "display: ;");
            let tbody = document.querySelector("#aptlist");
            console.log(data);
            initTable(); // 테이블 초기화

            data.forEach((apt) => {
                let tr = document.createElement("tr");

                let nameTd = document.createElement("td");
                nameTd.appendChild(document.createTextNode(apt.aptNm)); // 아파트 이름
                tr.appendChild(nameTd);

                let floorTd = document.createElement("td");
                floorTd.appendChild(document.createTextNode(apt.floor)); // 층
                tr.appendChild(floorTd);

                let areaTd = document.createElement("td");
                areaTd.appendChild(document.createTextNode(apt.excluUseAr)); // 전용 면적
                tr.appendChild(areaTd);

                let dongTd = document.createElement("td");
				let aptDealDate = apt.dealYear + "년 " + apt.dealMonth + "월 " + apt.dealDay +"일";
                dongTd.appendChild(document.createTextNode(aptDealDate)); // 거래 년 월 일
                tr.appendChild(dongTd);

                let priceTd = document.createElement("td");
                priceTd.appendChild(document.createTextNode(apt.dealAmount + "만원")); // 거래금액
                priceTd.classList.add("text-end");
                tr.appendChild(priceTd);

				// 도로명 주소 생성
				let roadAddress = apt.roadNm+apt.roadNmBonbun;
				if (apt.roadNmBubun && apt.roadNmBubun !== "0") {
					roadAddress += "-" + apt.roadNmBubun; // 부번이 있을 경우 추가
				}
                tr.addEventListener("click", () => viewMap(apt.aptNm, roadAddress)); // 클릭 이벤트
                tbody.appendChild(tr);



            });
        }


        function makeList(data) {
          document.querySelector("table").setAttribute("style", "display: ;");
          let tbody = document.querySelector("#aptlist");
          let parser = new DOMParser();
          const xml = parser.parseFromString(data, "application/xml");
          console.log(xml);
          initTable();
          let apts = xml.querySelectorAll("item");
          apts.forEach((apt) => {
            let tr = document.createElement("tr");

            let nameTd = document.createElement("td");
            nameTd.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
            tr.appendChild(nameTd);

            let floorTd = document.createElement("td");
            floorTd.appendChild(document.createTextNode(apt.querySelector("층").textContent));
            tr.appendChild(floorTd);

            let areaTd = document.createElement("td");
            areaTd.appendChild(document.createTextNode(apt.querySelector("전용면적").textContent));
            tr.appendChild(areaTd);

            let dongTd = document.createElement("td");
            dongTd.appendChild(document.createTextNode(apt.querySelector("법정동").textContent));
            tr.appendChild(dongTd);

            let priceTd = document.createElement("td");
            priceTd.appendChild(
              document.createTextNode(apt.querySelector("거래금액").textContent + "만원")
            );
            priceTd.classList.add("text-end");
            tr.appendChild(priceTd);

            let address =
              apt.querySelector("중개사소재지").textContent +
              " " +
              apt.querySelector("법정동").textContent +
              " " +
              apt.querySelector("지번").textContent;
            tr.addEventListener("click", () =>
              viewMap(apt.querySelector("아파트").textContent, address)
            );

            tbody.appendChild(tr);
          });
        }

        // 카카오지도
        var mapContainer = document.getElementById("map"), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
          };

        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        var map = new kakao.maps.Map(mapContainer, mapOption);

        var marker, infowindow;
        function viewMap(apt, address) {
          if (marker != null && infowindow != null) {
            marker.setMap(null);
            infowindow.close();
          }
          // 주소-좌표 변환 객체를 생성합니다
          var geocoder = new kakao.maps.services.Geocoder();

          // 주소로 좌표를 검색합니다
          geocoder.addressSearch(address, function (result, status) {
        	  console.log(status);
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              // 결과값으로 받은 위치를 마커로 표시합니다
              marker = new kakao.maps.Marker({
                map: map,
                position: coords,
              });

              // 인포윈도우로 장소에 대한 설명을 표시합니다
              infowindow = new kakao.maps.InfoWindow({
                content: `<div style="width:150px;text-align:center;padding:6px 0;">\${apt}</div>`,
              });
              infowindow.open(map, marker);

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
            }
          });
        }

        function initTable() {
          let tbody = document.querySelector("#aptlist");
          let len = tbody.rows.length;
          for (let i = len - 1; i >= 0; i--) {
            tbody.deleteRow(i);
          }
        }
      </script>


	<script src="/assets/js/home.js"></script>
</body>

</html>