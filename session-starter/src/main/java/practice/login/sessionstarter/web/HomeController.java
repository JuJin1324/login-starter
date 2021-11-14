package practice.login.sessionstarter.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import practice.login.sessionstarter.dtos.MemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }
        MemberDto loginMember = (MemberDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
