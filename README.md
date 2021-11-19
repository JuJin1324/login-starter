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

## filter-starter
### 데이터 흐름
> HTTP 요청 -> WAS -> 필터 -> 서블릿 -> 컨트롤러

### 실행
> 브라우저에 `localhost:8080` -> 로그인 버튼 클릭 -> ID: test / PW: test!

### servlet filter 설명
> 로그인한 사용자가 아닌 사용자가 로그인이 필요한 페이지를 URL 로 직접 요청 시 막아주는 역할   
> filter 가 아닌 Spring AOP 로도 해당 기능 구현이 가능하지만 Filter 가 제공하는 HttpServletRequest 같은 기능들이 더 풍부하기 때문에 
> filter 사용을 권장한다.
> 
> Filter 는 java 의 servlet 기술이기 때문에 package 가 javax.servlet 하위에 있다. 

### filter 등록 과정
> 1.web.filter 패키지에 LogFilter.java 파일 생성  
> 2.javax.servlet.Filter 인터페이스 구현  
> 3.핵심 메서드는 doFilter 임으로 해당 메서드만 오버라이딩
> 4.config 패키지에서 WebConfig.java 생성   
> 6.FilterRegistrationBean<Filter> 을 반환하는 @Bean 등록 메서드를 생성해서 1에서 생성했던 LogFilter 등록

## interceptor-starter
### 데이터 흐름
> HTTP 요청 -> WAS -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러

### 실행
> 브라우저에 `localhost:8080` -> 로그인 버튼 클릭 -> ID: test / PW: test!

### spring interceptor 설명
> preHandle: request 가 컨트롤러 메서드를 호출하기 전에 호출되는 메서드  
> handler 변수를 통해서 request 가 어느 컨트롤러의 어느 메서드로 가는지 등을 확인할 수 있다. 또한 static resource 로 가는지도 확인 가능
> 
> postHandle: request 가 정상처리 되서 컨트롤러 메서드를 빠져나온 경우 호출되는 메서드
> 
> afterCompletion: request 가 정상처리 되면 postHandler 호출 이후에 호출되며, 비정상 처리되면 바로 호출되는 메서드   
> 비정상 처리시 호출이 될 수 있음으로 매개변수에 Exception 이 있다.

### interceptor 등록 과정
> 1.web.interceptor 패키지에 LogInterceptor.java 파일 생성  
> 2.HandlerInterceptor 인터페이스 구현  
> 3.핵심 메서드 preHandle/postHandle/afterCompletion 오버라이딩
> 4.config 패키지에서 WebConfig.java 생성  
> 5.WebMvcConfigurer 인터페이스 구현  
> 6.addInterceptors 메서드 오버라이드를 통해서 1에서 생성했던 LogInterceptor 등록

