# Keycloak 사용법 정리

## User API 호출을 위한 - 클라이언트 권한 설정 방법
keycloak 접속 > Realm선택 > client 선택 > client role 선택 > Service accounts role 메뉴 이동 > Assign role 클릭
![1.png](keycloak%2FAPI%20%ED%98%B8%EC%B6%9C%EC%9A%A9%20%EA%B6%8C%ED%95%9C%20%EC%84%A4%EC%A0%95%20%EB%B0%A9%EB%B2%95%2F1.png)
Filter by Clients를 선택하여 manage-users 권한을 부여
![2.png](keycloak%2FAPI%20%ED%98%B8%EC%B6%9C%EC%9A%A9%20%EA%B6%8C%ED%95%9C%20%EC%84%A4%EC%A0%95%20%EB%B0%A9%EB%B2%95%2F2.png)

그렇게 해야만 UserApi 를 호출 할 수 있는 Client 권한을 갖게됨