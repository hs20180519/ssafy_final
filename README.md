# SSAFY12_광주5_관통_Framework_3팀_김자연_정현수

## :pushpin: 프로젝트 구성
### 프로젝트 설명
WhereIsMyHome 서비스는 사용자에게 편리하게 주택 매물 검색 기능을 기본으로 제공하는 서비스입니다. 본 프로젝트는 Java Servlet 기반 웹 프로젝트를 spring boot 프레임워크로 리팩토링하고, REST API 아키텍처를 적용하는 것을 목표로 합니다. <br>

### 팀 구성
|이름|역할|
|:---:|:---:|
|김자연(팀장)|매물 검색 및 검색 필터링 조건 api 개발|
|정현수|회원 관리 및 관심지역 api 개발|

### 기술 스택

  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <br>

### ERD
![erd](/uploads/74cddb5ac4e4bbb69e465cd441632a79/erd.png)

### 프로젝트 구조
#### Member: 회원 도메인
- MemberDto
- MemberMapper
- MemberService
- MemberController
#### HouseDeal: 매물 검색 도메인
- HouseDealDto
- HouseDealMapper
- HouseDealService
- HouseDealController
#### FavoriteArea: 관심지역 도메인
- FavoriteAreaDto
- FavoriteAreaMapper
- FavoriteAreaService
- FavoriteAreaController
#### DongCode: 지역 분류 코드 도메인
- DongCodeDto
- DongCodeMapper
- DongCodeService
- DongCodeController

## :pushpin: API 목록
### 회원관리 API
- 회원 정보 조회
- 회원 정보 수정
- 회원 삭제
- 로그인
- 회원가입
- 회원 검색
- 전체 회원 조회

![memberSwagger](/uploads/6cef4399fc1d1448ae05829213526f4e/memberSwagger.png)

### 관심지역 API
- 회원별 관심지역 조회
- 관심지역 추가
- 관심지역 삭제

![favoriteAreaSwagger](/uploads/7bb34f91df76ae9db6b759b8867e1f93/favoriteAreaSwagger.png)

### 매물 검색 API
- 필터링 조건에 따른 주택 매물 목록 조회

![house-deal-swagger](/uploads/130a810692b7b95164b8951a95ef99b4/localhost_swagger-ui_index.html_urls.primaryName_house-deal.png)

### 필터링 조건 API
- 필터링 조건 조회
![dong-code-swagger](/uploads/15ea83279b2c8d0ef61cc92c1e0a961f/localhost_swagger-ui_index.html_urls.primaryName_dong-code.png)

# :pushpin: Commit Convention

feat : 기능 (새로운 기능)

fix : 버그 (버그 수정)

refactor : 리팩토링

design : CSS 등 사용자 UI 디자인 변경

comment : 필요한 주석 추가 및 변경

style : 스타일 (코드 형식, 세미콜론 추가: 비즈니스 로직에 변경 없음)

docs : 문서 수정 (문서 추가, 수정, 삭제, README)

test : 테스트 (테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경 없음)

chore : 기타 변경사항 (빌드 스크립트 수정, assets, 패키지 매니저 등)

init : 초기 생성

rename : 파일 혹은 폴더명을 수정하거나 옮기는 작업만 한 경우

remove : 파일을 삭제하는 작업만 수행한 경우

# :pushpin: Git Branch 전략 - GitHub Flow

1. master
2. feature
3. release / hotfix → 필요 시

1) master branch, develop branch

2) feature branch: 이슈추적 방식 사용
<br><br>feature/{issue-number}-{feature-name} 

3) release branch
<br><br>release/{version}

4) hotfix branch
<br><br>hotfix/{issue-number}