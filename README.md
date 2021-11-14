# login-starter

## Prepare
### h2 database
> dbname: practice-starter

## session-starter
### 실행
> 브라우저에 `localhost:8080` -> 로그인 버튼 클릭 -> ID: test / PW: test!

### 소스코드
> LoginController

### 옵션
> 로그인 시에 웹 브라우저가 쿠키를 지원하지 않을 수 있음으로 JSESSION ID 를 URL 뒤에 붙여주는게 default 이다.  
> URL 뒤에 붙이지 않고 무조건 쿠키를 통해 전달하도록 변경하는 옵션      
> application.yml
> ```yaml
> server:
>   servlet:
>     session:
>       tracking-modes: cookie
> ```
