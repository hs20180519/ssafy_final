# SSAFY12_광주5_관통_Vue.js_3팀_김자연_정현수

## :pushpin: 프로젝트 구성
### 프로젝트 설명
WhereIsMyHome 서비스는 사용자에게 편리하게 주택 매물 검색 기능을 기본으로 제공하는 서비스입니다. 본 프로젝트는 Vue.js를 활용하여 프론트엔드를 구현하고, 기존에 개발된 REST API와 연동하는 것을 목표로 합니다. <br>

### 팀 구성
|이름|역할|
|:---:|:---:|
|김자연(팀장)|매물 검색 페이지 구현|
|정현수|회원 관리(회원가입, 로그인/로그아웃), QnA 페이지 구현|

### Notion
https://twisty-stag-36c.notion.site/1309a41b7c4680f6b47ff0426dbb7f18

### 기술 스택

  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">
  <br>

### ERD
![image](/uploads/0152eee85de1869c3b9c142fbd1cbc99/image.png)

### Figma
![image](/uploads/b71e0bc4d746670b2a6e04031261ace5/image.png)
https://www.figma.com/design/pDmPt7Uku5j0OIBwLVzeJB/ssafy-project?node-id=0-1&m=dev&t=eETbAL6Y2r2h5Dcc-1

## :pushpin: API 목록
### 회원관리 API
- 회원 정보 조회
- 회원 정보 수정
- 회원 삭제
- 로그인
- 회원가입
- 회원 검색
- 전체 회원 조회

### 관심지역 API
- 회원별 관심지역 조회
- 관심지역 추가
- 관심지역 삭제

### 매물 검색 API
- 필터링 조건에 따른 주택 매물 목록 조회

### 필터링 조건 API
- 필터링 조건 조회

### QnA 게시판 API
- 모든 질문 조회
- 질문 상세 조회
- 질문 등록
- 질문 수정
- 특정 질문에 대한 모든 답변 조회
- 답변 등록
- 답변 수정
- 답변 삭제


## :pushpin: 화면 구성 (추후 gif 추가 예정)
### 메인페이지
![image](/uploads/73b7e8df166e442c9ebb4c2655d33cc3/image.png)

### 회원 관리
#### 회원가입
![image](/uploads/e8e11bca8eff0e1244da22bb70011040/image.png)

#### 로그인
![image](/uploads/25a4665d7f909dd102eca8755b984867/image.png)

### QnA 게시판
#### QnA 게시판 조회 - vue 수정 예정
![image](/uploads/ddff70be26a484f6dbc03c90d91f8a65/image.png)

#### QnA 게시글 등록
![image](/uploads/601bdc7d98fad9e5f20c2368ab9592a8/image.png)

#### QnA 게시글 조회 및 답변 등록
![image](/uploads/d22af8893810188018d25a5d398f700e/image.png)

### 매물 검색
#### 메인페이지에서 검색
![image](/uploads/9cefea09c9729a511e05f81f67bfd013/image.png)<br />
검색 결과를 클릭하면 해당 지역이나 매물에 해당하는 실거래가 페이지로 이동합니다.

#### 매물 실거래가 목록 페이지
![image](/uploads/1e8ad957cc9a3add10c2d12c038aca29/image.png)

#### 매물 실거래가 목록 페이지에서 재검색
![image](/uploads/c36b8ff782021f450e27e32ae97a75df/image.png)
![image](/uploads/607b96c7917f5fe9e2b95f6dfd5575d9/image.png)

#### 지역 필터링 검색
![image](/uploads/16fbae03401a0b14f51b5ab8b1219a6a/image.png)
![image](/uploads/453098e923e9d874ae406edeff40529a/image.png) <br />
\* 더 많은 목록이 보이도록 페이지 축소하였습니다.

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
