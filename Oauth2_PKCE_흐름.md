# OAuth 2.0 PKCE Flow

## 1. 코드 Verifier와 Challenge 생성하기

1. 코드 검증기를 생성합니다.
    - 사용자를 인증 서버로 리디렉션하기 전, 클라이언트는 먼저 Verifier와 challenge을 생성합니다.
    - 코드 검증기는 AZ, az, 0-9 문자와 구두점 문자 -._~(하이픈, 마침표, 밑줄 및 물결표)를 사용하는 43~128자 길이의 암호화된 무작위 문자열입니다.

    ```
    VLD57ITWhCL2wH5DMyfn4AvqWV22dX-Uf3VJmNPq0PFMsDns
    ```

2. Verifier를 사용하여 코드 Challenge를 생성합니다.
    - Verifier의 SHA256 해시에 대한 BASE64-URL 인코딩 문자열입니다.
    - base64url(sha256(code_verifier))

    ```
    UzyOi293tlbIR6NHirnu53v28MYmBKBe9ky9gbRVKq4
    ```

    - 클라이언트는 code_verifier 나중에 사용하기 위해 쿠키에 저장해야 합니다.

## 2. 인증코드 요청

아래 URL을 통해 인증 코드를 요청할 수 있습니다.

```
https://authorization-server.com/authorize?
  response_type=code
  &client_id=YBovVspd1e7idGNjeNIWZU3j
  &redirect_uri=https://oauth.net/playground/authorization-code-with-pkce.html
  &scope=photo+offline_access
  &state=7asTrke1FFuWcm0X
  &code_challenge=FHMENFQARcTdd0VScEEjOFOG9u1r8ZFT1EttUdmCLrI
  &code_challenge_method=S256
```

## 3. 리디렉션된 클라이언트 페이지에서 state값이 리디렉션된 state와 일치하는지 확인
```
```
[참고 URL](https://oauth.net/playground)