# login-starter

## Prepare
### h2 database
> dbname: practice-starter

## session-starter
### 실행
> 브라우저에 `localhost:8080` -> 로그인 버튼 클릭 -> ID: test / PW: test!

### 소스코드
> LoginController: 세션 생성 / 삭제   
> HomeController: 세션 확인(존재/미존재)   
> SessionInfoController: 세션 변수 정보 확인   

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

### 타임아웃
> Spring Session 의 Timeout 은 기본적으로 마지막 요청 발생 후 타임아웃에 설정된 시간 이후에 세션이 삭제되는 방식으로 동작한다.
