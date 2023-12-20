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

## 2. 인증 URL 생성

- 클라이언트는 다음 단계에서 사용할 state 파라미터
- 무작위 문자열로 생성하고 저장해야 합니다.

```
https://authorization-server.com/authorize?
  response_type=code
  &client_id=YBovVspd1e7idGNjeNIWZU3j
  &redirect_uri=https://oauth.net/playground/authorization-code-with-pkce.html
  &scope=photo+offline_access
  &state=ZYtd8CNXy_vLhye_
  &code_challenge=UzyOi293tlbIR6NHirnu53v28MYmBKBe9ky9gbRVKq4
  &code_challenge_method=S256
```

- 무작위로 생성된 state 파라미터와, code_verifier를 쿠키에 저장

- 클라이언트는 이 요청에 code_challenge 파라미터를 포함시킵니다. 
- 이 파라미터는 인증 서버에서 나중에 코드 교환 단계에서 비교하여 저장합니다.

인증 서버로 이동합니다. 생성된 사용자 이름과 비밀번호를 입력해야 합니다.

## 3. state 파라미터 검증

추가 쿼리 파라미터가 있는 URL 클라이언트로 다시 리디렉션되었습니다:

```
?state=ZYtd8CNXy_vLhye_&code=AHo5_rkhtI99euL_ovVQqMX2MBjXNyvS8RngsM_-_pepkpqP
```

- 여기서 state 값은 PKCE 파라미터 자체가 CSRF 보호를 제공하기 때문에 엄격하게 필요하지는 않습니다. 
- 실제로 OAuth 서버가 PKCE를 지원하는지 확실하다면, 
- CSRF 보호를 위해 state 파라미터를 사용하는 대신 애플리케이션 상태를 위해 사용할 수 있습니다.

클라이언트에 저장된 state(ZYtd8CNXy_vLhye_)가 리디렉션된 state(ZYtd8CNXy_vLhye_)와 일치합니까?

## 4. 인증 코드 교환

이제 인증 코드를 액세스 토큰으로 교환할 준비가 되었습니다.

클라이언트는 다음 파라미터를 가진 토큰 엔드포인트로 POST 요청을 구성합니다:

```
POST https://authorization-server.com/token

grant_type=authorization_code
&client_id=YBovVspd1e7idGNjeNIWZU3j
&client_secret=7FgDffCGkXakqO_MjDbUdoNAS2hBTdYr8ohmBxnHMknUJQcl
&redirect_uri=https://oauth.net/playground/authorization-code-with-pkce.html
&code=AHo5_rkhtI99euL_ovVQqMX2MBjXNyvS8RngsM_-_pepkpqP
&code_verifier=VLD57ITWhCL2wH5DMyfn4AvqWV22dX-Uf3VJmNPq0PFMsDns
```

- code_verifier를 기억하십니까? 
- 토큰 요청과 함께 이를 보내야 합니다. 
- 인증 서버는 code_verifier가 인증 요청에서 사용된 Challenge와 일치하는지 확인합니다. 
- 이렇게 하면 인증 코드를 가로챈 악의적인 당사자가 이를 사용할 수 없게 됩니다.

## 5. 토큰 엔드포인트 응답

다음은 토큰 엔드포인트에서의 응답입니다! 응답에는 액세스 토큰과 리프레시 토큰이 포함되어 있습니다.

```json
{
  "token_type": "Bearer",
  "expires_in": 86400,
  "access_token": "6ZklhpWDz4oMcxbOQpf_tDUyqyVOYNupAPrGnyw_2Gg5C0JhXS5HbgdWli0hFabkkmMSTkfe",
  "scope": "photo offline_access",
  "refresh_token": "vT_24niLjWT5UUoQY1q_uHXI"
}
```

[참고 URL](https://oauth.net/playground)