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

### filter 가 AOP 보다 나은 점
> servlet request/response 제공으로 인해서 request URI 및 Header 정보 접근이 용이하다.

### filter 생성
> 1.web.filter 패키지에 LogFilter.java 파일 생성  
> 2.javax.servlet.Filter 인터페이스 구현  
> 3.핵심 메서드는 doFilter 임으로 해당 메서드만 오버라이딩
> 4.외부 서비스 사용이 필요할 시에 LogFilter 위에 @Component 애노테이션을 통해 스프링 빈으로 생성가능   
> 5.doFilter 마지막에 chain.doFilter 메서드를 호출해서 요청을 컨트롤러로 넘긴다.(doFilter 를 호출하지 않으면 요청이 컨트롤러로 넘어가지 않는다.)

### filter 등록 
> 1.config 패키지에서 WebConfig.java 생성   
> 2.FilterRegistrationBean<Filter> 을 반환하는 @Bean 등록 메서드를 생성해서 1에서 생성했던 LogFilter 등록
> 3.LogFilter 의 경우 setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR) 로 정상/에러 요청에 모두 대응하고 
> LoginCheckFilter 의 경우 setDispatcherTypes(DispatcherType.REQUEST) 로 정상 요청의 경우에만 대응한다.

## interceptor-starter
### 데이터 흐름
> HTTP 요청 -> WAS -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러

### 실행
> 브라우저에 `localhost:8080` -> 로그인 버튼 클릭 -> ID: test / PW: test!

### interceptor 가 filter 보다 나은 점
> 1.filter 와는 달리 request,response 매개변수를 받을 때 부터 HttpServlet 으로 받는다.  
> 2.WebConfig 에서 interceptor 등록 시에 excludePathPatterns 을 지정할 수 있다.

### spring interceptor 설명
> preHandle: request 가 컨트롤러 메서드를 호출하기 전에 호출되는 메서드  
> handler 변수를 통해서 request 가 어느 컨트롤러의 어느 메서드로 가는지 등을 확인할 수 있다. 또한 static resource 로 가는지도 확인 가능
> 
> postHandle: request 가 정상처리 되서 컨트롤러 메서드를 빠져나온 경우 호출되는 메서드
> 
> afterCompletion: request 가 정상처리 되면 postHandler 호출 이후에 호출되며, 비정상 처리되면 바로 호출되는 메서드   
> 비정상 처리시 호출이 될 수 있음으로 매개변수에 Exception 이 있다.

### interceptor 생성
> 1.web.interceptor 패키지에 LogInterceptor.java 파일 생성  
> 2.HandlerInterceptor 인터페이스 구현  
> 3.핵심 메서드 preHandle/postHandle/afterCompletion 오버라이딩
> 4.외부 서비스 사용이 필요할 시에 LogInterceptor 위에 @Component 애노테이션을 통해 스프링 빈으로 생성가능

### interceptor 등록
> 1.config 패키지에서 WebConfig.java 생성  
> 2.WebMvcConfigurer 인터페이스 구현  
> 3.addInterceptors 메서드 오버라이드를 통해서 1에서 생성했던 LogInterceptor 등록

## Argument Resolver
### 사용처
> Interceptor 에서 로그인 사용자를 걸러낸 후 컨트롤러 메서드에 로그인 사용자 전달을 매개변수로 편하게 할 수 있음.

### 생성 및 등록 과정
> 1.web.argumentresolver 패키지에 Login.java annotation 생성  
> `@Target(ElementType.PARAMETER)`: 컨트롤러 메서드의 파라미터 앞에 붙일 것이기 때문  
> `@Retention(RetentionPolicy.RUNTIME)`: 런타임 설정을 해야 자바 리플렉션을 통해서 해당 매개변수에 값을 넣어줄 수 있음.    
> 2.web.argumentresolver 패키지에 LoginMemberArgumentResolver.java 생성  
> 3.HandlerMethodArgumentResolver 인터페이스 구현    
> 4.supportsParameter: 1에서 생성한 애노테이션이 매개변수에 붙었는지 확인 및 애노테이션이 붙은 매개변수 타입 중 어떤 타입을 지원할 것인지 여부 반환      
> 5.resolveArgument: request 객체에서 애노테이션을 붙인 객체를 추출해서 반환  
> 6.WebConfig 에서 addArgumentResolvers 메서드 오버라이드해서 2에서 생성한 LoginMemberArgumentResolver 등록  

