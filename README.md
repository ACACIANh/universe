# TODO list

1. 미팅 (정모) 참여 로직 및 api - 참여인원, 참여자, 인원수에 따른 참여 시도시 처리 등등 -> 누군가 취소시 갱신해주는 메소드 추가
2. 출석체크 기능 api 추가 ( 로그 추가 ) -> 간단하게 추가함
3. ~~참여 로직 에 시간 추가~~
4. release 브랜치 - in memory db -> 외부 Db 사용
5. ~~카카오 소셜로그인 public ip 등록해서 이미지 빌드하고 올리기~~
6. 정모 참여 취소시 처리 추가필요 ( 삭제, 혹은 취소상태 고려 )
7. role 구분해서 출석하면 다른벙 참여할수있게?
8. 매주 월요일 정모추가 확인
9. ~~스웨거 디폴트 key, value 변경한지 확인하고 추가하기 ex) isGuest, localtime **~~ 각 필드에서 붙여줘야함
10. ~~UserContext.getCurrentUser() 개선하기~~
11. 정모 참여후 출석체크시 연동할것
12. ~~jackson isXXX 설정 찾아보기~~
13. ~~meetingUsers 일급컬렉션으로 변경하기~~
14. 테스트코드 만들기
15. ~~swagger code 인터페이스로 분리~~
16. restdocs 추가
17. 로그 남기는 방법 -> 파일 or 다른방법?
18. ~~엔티티와 도메인 분리~~
19. 디렉토리 구조 고찰
20. ~~port, usecase 인터페이스 정의 및 구현~~
21. ~~naming - jpaentity~~
22. ~~security 토큰으로 요청들어올때 db 로직타는부분 제거하는방향으로 수정~~
23. ~~id 를 도메인에서 생성해주고 UUID.randomUUID()~~
24. web dto 분리

# 개인적인 프로젝트 추가사항

1. ~~request, response 로깅~~ -> 일단 추가했는데 보완
2. exception 공통화 및 advice 적용 -> 진행중인데 좀더 예쁜거 찾아보고 변경하기 -> exceptionStrategy 참고 -> Enum 으로 Code, 메세지 미리
   정의 //https://cheese10yun.github.io/spring-guide-exception/
3. ~~swagger -> 추가하고 security 예외처리 -> 토큰헤더 간편화 -> 시도했는데 보류~~ ->
   적용완료 https://stackoverflow.com/questions/76734836/how-to-add-header-authorization-for-swagger-3-spring-boot
4. queryDsl 설정 추가
5. cache 적용할부분 다시 확인

# 개인적인 프로젝트 개선사항

1. ~~security WebSecurityConfigurerAdapter deprecated 개선 -> 빈 방식으로 교체~~
2. security 관련 개선사항
    1) ~~모든 요청이 filter 로직에 들어와서 필요없는 검증도 하게되어 error 로그~~
    2) ~~userDetails 를 필요이상으로 사용하는 느낌 -> userDetails, OAuth2User 두개 섞어서~~
    3) repository 에 넣는 데이터 확인, ex) provider token
    4) ~~controller 에서 user 정보 가져오는 부분 공통화 -> component 나 util 로~~
    5) ~~token 발급시 redirect 방식으로 변경~~
3. 카카오 정보 갱신 타이밍 확인 ***
4. ~~requestBody, pathVariable 개선 -> 하나로 통일 ( 둘다 애매함 )~~
5. ~~controller 에서 entity 바로 리턴 해주는데, controller dto 로 수정해서 반환~~ ***중요함***
7. ~~loadUserByUsername 이거 사용하는지 확인하고 제대로 수정~~ UserDetails 코드 제거
8. ~~zonedatetime -> localdatetime 으로 찾아서 수정~~

# 배포관련 체크리스트

1. 설정 yml 외부에서 읽고 변경되게 -> oauth 설정쪽 url 변경필요
2. ci/cd 툴 선택 -> gitlab vs jenkins vs github-action -> 아마 깃헙액션
3. ci/cd 설정 -> 공부해서 해야함
4. nas ssh 접속 설정
