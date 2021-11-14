package practice.login.sessionstarter.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/14
 */

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name = {}, value = {}", name, session.getAttribute(name)));

        log.info("sessionId = {}", session.getId());
        /* 세션 유효 시간(초) - default: 1800초(30분)
         * 설정: application.yml: server.servlet.session.timeout */
        log.info("getMaxInactiveInterval = {}", session.getMaxInactiveInterval());

        LocalDateTime creationTime = Instant.ofEpochMilli(session.getCreationTime())
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
        log.info("getCreationTime = {}", creationTime);

        LocalDateTime lastAccessedTime = Instant.ofEpochMilli(session.getCreationTime())
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
        log.info("getLastAccessedTime = {}", lastAccessedTime);

        log.info("isNew = {}", session.isNew());

        return "세션 출력";
    }
}
