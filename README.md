# commit convention

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

### Gitmoji

https://inpa.tistory.com/entry/GIT-%E2%9A%A1%EF%B8%8F-Gitmoji-%EC%82%AC%EC%9A%A9%EB%B2%95-Gitmoji-cli

# Git Branch 전략 - Git Flow

1. master
2. feature
3. release / hotfix → 필요 시

https://devocean.sk.com/blog/techBoardDetail.do?ID=165571&boardType=techBlog

branch 네이밍 규칙
어떤 방식으로 브랜치의 이름을 정하는지 브랜치 종류에 따라 살펴보자.

1) master branch, develop branch

master와 develop 브랜치는 본래 이름 그대로 사용하는 경우가 일반적이다.

2) feature branch

어떤 이름도 가능하다. 단, master, develop, release-..., hotfix-... 같은 이름은 사용할 수 없다.

feature/기능요약 형식을 추천한다. ex) feature/login

feature/{issue-number}-{feature-name} 이슈추적을 사용한다면 이와 같은 형식을 따른다.
ex) feature/1-init-project, feature/2-build-gradle-script-write

3) release branch

release-RB_... 또는 release-... 또는 release/...같은 이름이 일반적이다.

release-... 형식을 추천한다. ex) release-1.2

4) hotfix branch

hotfix-... 형식을 추천한다. ex) hotfix-1.2.1
